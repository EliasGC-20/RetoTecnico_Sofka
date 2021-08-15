
package com.sofka.entidades;


public class Carro {

    private String idCarro; //Nombre del carro
    private Carril carril;  //Carril en el que corre el carro
    
    public Carro() {
        super();
    }

    public Carro(String idCarro) {
        super();
        this.idCarro = idCarro;
    }

    public String getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(String idCarro) {
        this.idCarro = idCarro;
    }

    public Carril getCarril() {
        return carril;
    }

    public void setCarril(Carril carril) {
        this.carril = carril;
    }

    @Override
    public String toString() {
        return "Carro{" + "idCarro = " + idCarro + ", carril = " + carril + '}';
    }

}
