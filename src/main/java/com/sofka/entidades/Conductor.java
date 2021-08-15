
package com.sofka.entidades;


public class Conductor {

    protected Carro carro;

    public Conductor() {
        super();
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    @Override
    public String toString() {
        return "Conductor{" + "carro = " + carro + '}';
    }

}
