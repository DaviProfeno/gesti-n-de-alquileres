package com.mycompany.alquileres;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author David
 */
public class vivienda {

    private int id;
    private String ubicacion;
    private int metros;
    private int habitaciones;
    private int banos;
    private int precioAlquiler;
    private int codigo;



    public void setId(int id) {
        this.id = id;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setMetros(int metros) {
        this.metros = metros;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void setBanos(int banos) {
        this.banos = banos;
    }

    public void setPrecioAlquiler(int precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getMetros() {
        return metros;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public int getBanos() {
        return banos;
    }

    public int getPrecioAlquiler() {
        return precioAlquiler;
    }

    public int getCodigo() {
        return codigo;
    }

    public vivienda(int id, String ubicacion, int metros, int habitaciones, int banos, int precioAlquiler, int codigo) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.metros = metros;
        this.habitaciones = habitaciones;
        this.banos = banos;
        this.precioAlquiler = precioAlquiler;
        this.codigo = codigo;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String toString() {
        return "ID : " + id
                + "\nUbicación: " + ubicacion
                + "\nMetros cuadrados: " + metros + " metros cuadrados"
                + "\nHabitaciones: " + habitaciones + " habitaciones"
                + "\nBaños: " + banos + " baños"
                + "\nPrecio del alquiler mensual: " + precioAlquiler + " €"
                + "\nCodigo: " + codigo + "\n";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public int hashCode() {
        return super.hashCode(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
