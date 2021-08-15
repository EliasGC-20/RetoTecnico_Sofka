
package com.sofka.entidades;


public class Podio {

    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugador3;

    public Podio(Jugador jugador1, Jugador jugador2, Jugador jugador3) {
        super();
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugador3 = jugador3;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public Jugador getJugador3() {
        return jugador3;
    }

    public void setJugador3(Jugador jugador3) {
        this.jugador3 = jugador3;
    }

    @Override
    public String toString() {
        return "Podio{" + "jugador1 = " + jugador1 + ", jugador2 = " + jugador2 + ", jugador3 = " + jugador3 + '}';
    }

}
