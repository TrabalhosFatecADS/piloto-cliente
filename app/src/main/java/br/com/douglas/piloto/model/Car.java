package br.com.douglas.piloto.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class Car {


    private String marca;
    private String modelo;
    private String id;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(marca, car.marca) &&
                Objects.equals(modelo, car.modelo) &&
                Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marca, modelo, id);
    }

    @Override
    public String toString() {
        return "Car{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
