
package com.sofka.entidades;


public class Pista {

    private String nombrePista;     //Nombre de la pista
    private float distanciaPista;   //Distancia en kilómetros

    public Pista(String nombrePista, float distanciaPista) {
        super();
        this.nombrePista = nombrePista;
        this.distanciaPista = distanciaPista;
    }

    public String getNombrePista() {
        return nombrePista;
    }

    public void setNombrePista(String nombrePista) {
        this.nombrePista = nombrePista;
    }

    public float getDistanciaPista() {
        return distanciaPista;
    }

    public void setDistanciaPista(float distanciaPista) {
        this.distanciaPista = distanciaPista;
    }

    @Override
    public String toString() {
        return "Pista{" + "nombrePista = " + nombrePista + ", distanciaPista = " + distanciaPista + '}';
    }

}
