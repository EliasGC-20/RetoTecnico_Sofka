
package com.sofka.entidades;

public class Jugador extends Conductor {

    private String nombreJugador;
    private int vecesGanadas;

    public Jugador(String nombreJugador) {
        super();
        this.nombreJugador = nombreJugador;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getVecesGanadas() {
        return vecesGanadas;
    }

    public void setVecesGanadas(int vecesGanadas) {
        this.vecesGanadas = vecesGanadas;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombreJugador = " + nombreJugador + ", vecesGanadas = " + vecesGanadas + '}';
    }
    
}
