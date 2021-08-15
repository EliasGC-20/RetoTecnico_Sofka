
package com.sofka.servicios;

import com.sofka.entidades.Carril;
import com.sofka.entidades.Carro;
import com.sofka.entidades.Jugador;
import com.sofka.entidades.Pista;
import com.sofka.entidades.Podio;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import javax.swing.JOptionPane;


public class Juego {

    private boolean listo;
    private LinkedHashMap<String, Jugador> jugadores;
    private LinkedHashSet<String> ganadores;
    private Pista pista;
    private Podio podio;

    public Juego() {
        this.jugadores = new LinkedHashMap<String, Jugador>();
        this.ganadores = new LinkedHashSet<String>();
        this.listo = false;
    }

    public void empezar() {
        JOptionPane.showMessageDialog(null, "Bienvenido a tu Juego de Carreras.\n\nPresiona OK para empezar.");
        entrada();
        configurar();
    }

    public void entrada() {
        this.pista = crearPista();
    }

    public void configurar() {
        if (iniciar(pista)) {
            listo = true;
        } else {
            listo = false;
        }

    }

    public Pista crearPista() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la pista: ");
        float distancia = Float.parseFloat(JOptionPane.showInputDialog("Ingrese la distancia total de la pista en Kilómetros: "));
        Pista pista = new Pista(nombre, distancia);
        return pista;
    }

    public Jugador crearJugador() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del jugador: ");
        Jugador jugador = new Jugador(nombre);
        return jugador;
    }

    public Carro elegirCarro() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del carro: ");
        Carro carro = new Carro(nombre);
        return carro;
    }

    public Carril elegirCarril(Pista pista) {
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número del carril: "));
        Carril carril = new Carril(numero, pista);
        return carril;
    }

    public boolean iniciar(Pista pista) {
        boolean banderaJugador = false;
        boolean banderaCarril = true;
        int opcion = 0;
        do {
            String menu = """
                      -----------------------------
                           Menú de Jugadores
                      -----------------------------
                      
                      Jugadores registrados: """ + " " + jugadores.size()
                    + """
                      \n
                      1. Agregar jugador.
                      2. Ver jugadores;
                      3. Borrar lista de jugadores.
                      4. Iniciar Carrera.
                      9. Reiniciar juego.
                      0. Salir del Juego.
                      
                      Ingresa tu opción: """;
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (opcion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Gracias por jugar. Vuelve pronto!");
                    return false;
                case 1:
                    if (jugadores.size() < 8) {
                        do {
                            Jugador jugador = crearJugador();
                            if (jugadores.containsKey(jugador.getNombreJugador())) {
                                JOptionPane.showMessageDialog(null, "El nombre de este jugador ya existe");
                            } else {
                                Carro carro = elegirCarro();
                                jugador.setCarro(carro);
                                do {
                                    Carril carril = elegirCarril(pista);
                                    for (Jugador iter : jugadores.values()) {
                                        if (iter.getCarro().getCarril().getIdCarril() == carril.getIdCarril()) {
                                            JOptionPane.showMessageDialog(null, "Elija otro carril.");
                                            banderaCarril = false;
                                        } else {
                                            banderaCarril = true;
                                        }
                                    }
                                    if (banderaCarril == true) {
                                        carro.setCarril(carril);
                                        jugadores.put(jugador.getNombreJugador(), jugador);
                                        banderaJugador = true;
                                    }
                                } while (banderaCarril == false);
                            }
                        } while (banderaJugador == false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya está llena la pista");
                    }
                    break;
                case 2:
                    String listos = "Jugadores listos para competir.\n\n";
                    for (Jugador iter : jugadores.values()) {
                        listos = listos + iter.getCarro().getCarril().getIdCarril() + ". carril: " + iter.getNombreJugador() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, listos);
                    break;
                case 3:
                    jugadores.clear();
                    break;
                case 4:
                    if (jugadores.size() < 3) {
                        JOptionPane.showMessageDialog(null, "Número de jugadores insuficiente. Asegurese de registrar mínimo 3 jugadores.");
                        opcion = 1;
                    } else {
                        listo = true;
                        inicio();
                    }
                    break;
                case 9:
                    jugadores.clear();
                    ganadores.clear();
                    empezar();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese una opción válida");
                    break;
            }
        } while (opcion != 4);
        return true;
    }

    public void inicio() {
        if (listo) {
            for (Jugador jugador : jugadores.values()) {
                Carril carril = jugador.getCarro().getCarril();
                carril.setDistanciaRecorrida(0);
            }
            ganadores.clear();
            do {
                avanceCarros();
                verificarGanadores();
                //System.out.println(ganadores);
            } while (ganadores.size() < 3);
            asignarPodio();
            persistenciaDatos();
            mostrarPodio();            
            configurar();
        } else {
            JOptionPane.showMessageDialog(null, "El juego no está configurado correctamente. Presiona OK para configurarlo.");
            configurar();
        }
    }

    public void avanceCarros() {
        float distanciaActual = 0, distanciaNueva;
        for (Jugador iterador : jugadores.values()) {
            distanciaActual = iterador.getCarro().getCarril().getDistanciaRecorrida();
            distanciaNueva = distanciaActual + ((int) (Math.random() * 6) + 1) * 100;
            Carril carril = iterador.getCarro().getCarril();
            carril.setDistanciaRecorrida(distanciaNueva);
            System.out.println(iterador.getCarro().getCarril().getDistanciaRecorrida() + " " + iterador.getNombreJugador());
        }
    }

    public void verificarGanadores() {
        TreeMap<Float, String> auxiliar = new TreeMap<>();
        for (Jugador iterador : jugadores.values()) {
            if (iterador.getCarro().getCarril().getDistanciaRecorrida() >= iterador.getCarro().getCarril().getPista().getDistanciaPista() * 1000) {
                auxiliar.put(iterador.getCarro().getCarril().getDistanciaRecorrida(), iterador.getNombreJugador());
            }
        }
        System.out.println(auxiliar.descendingMap());
        for (String iterador : auxiliar.descendingMap().values()) {
            if (ganadores.size() < 3) {
                ganadores.add(iterador);
            }
        }
    }

    public void asignarPodio() {
        Object[] array = ganadores.toArray();
        Jugador primero = jugadores.get(array[0].toString());
        Jugador segundo = jugadores.get(array[1].toString());
        Jugador tercero = jugadores.get(array[2].toString());
        podio = new Podio(primero, segundo, tercero);
        primero.setVecesGanadas(primero.getVecesGanadas() + 1);
    }

    public void mostrarPodio() {
        String resultado = "Podio de la pista " + pista.getNombrePista() + "\n";
        resultado = resultado + "\nPrimer puesto.\n" + podio.getJugador1().getCarro().getCarril().getIdCarril() + " Carril. " + podio.getJugador1().getNombreJugador() + ". Conduciendo el carro " + podio.getJugador1().getCarro().getIdCarro() + "\nVeces Ganadas: " + podio.getJugador1().getVecesGanadas();
        resultado = resultado + "\n\nSegundo puesto.\n" + podio.getJugador2().getCarro().getCarril().getIdCarril() + " Carril. " + podio.getJugador2().getNombreJugador() + ". Conduciendo el carro " + podio.getJugador2().getCarro().getIdCarro() + "\nVeces Ganadas: " + podio.getJugador2().getVecesGanadas();
        resultado = resultado + "\n\nTercer puesto.\n" + podio.getJugador3().getCarro().getCarril().getIdCarril() + " Carril. " + podio.getJugador3().getNombreJugador() + ". Conduciendo el carro " + podio.getJugador3().getCarro().getIdCarro() + "\nVeces Ganadas: " + podio.getJugador3().getVecesGanadas();
        JOptionPane.showMessageDialog(null, resultado);
    }

    public void persistenciaDatos() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        try {
            fichero = new FileWriter("Ganadores.txt", true);
            pw = new PrintWriter(fichero);
            pw.println("Nueva carrera - " + fecha.format(LocalDateTime.now()));
            pw.println("Pista: " + pista.getNombrePista());
            pw.println("1ra. Posición: " + podio.getJugador1().getNombreJugador() + ". Carreras ganadas: " + podio.getJugador1().getVecesGanadas());
            pw.println("2da. Posición: " + podio.getJugador2().getNombreJugador() + ". Carreras ganadas: " + podio.getJugador2().getVecesGanadas());
            pw.println("3ra. Posición: " + podio.getJugador3().getNombreJugador() + ". Carreras ganadas: " + podio.getJugador3().getVecesGanadas());
            pw.println();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }
}
