
package com.sofka.entidades;


public class Carril {

    private int idCarril;               //Número del carril
    private float distanciaRecorrida;   //Distancia recorrida por el carro en el carril
    private Pista pista;                //Pista a la que pertenece el carril

    public Carril(int idCarril, Pista pista) {
        super();
        this.idCarril = idCarril;
        this.pista = pista;
    }

    public int getIdCarril() {
        return idCarril;
    }

    public void setIdCarril(int idCarril) {
        this.idCarril = idCarril;
    }

    public float getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public void setDistanciaRecorrida(float distanciaRecorrida) {
        this.distanciaRecorrida = distanciaRecorrida;
    }

    public Pista getPista() {
        return pista;
    }

    public void setPista(Pista pista) {
        this.pista = pista;
    }

    @Override
    public String toString() {
        return "Carril{" + "idCarril = " + idCarril + ", distanciaRecorrida = " + distanciaRecorrida + ", pista = " + pista + '}';
    }

}
