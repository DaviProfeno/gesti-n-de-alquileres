/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alquileres;


import java.util.Date;


/**
 *
 * @author David
 */
public class alquiler {
    
    private int idAlquiler;
    private int numeroExpediente;
    private Date fechaEntrada;
    private int tiempoEstimado;
    private String dniClienteAsociado;
    private int idViviendaAsociada;

    public alquiler(int idAlquiler, int numeroExpediente, Date fechaEntrada, int tiempoEstimado, String dniClienteAsociado, int idViviendaAsociada) {
        this.idAlquiler = idAlquiler;
        this.numeroExpediente = numeroExpediente;
        this.fechaEntrada = fechaEntrada;
        this.tiempoEstimado = tiempoEstimado;
        this.dniClienteAsociado = dniClienteAsociado;
        this.idViviendaAsociada = idViviendaAsociada;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    //String para mostrar los datos recogidos en la base de datos
    @Override
    public String toString() {
        return "id del Alquiler "+ idAlquiler +
                "\n Número de expediente: " + numeroExpediente +
           "\n Fecha de entrada: " + fechaEntrada +
           "\n Tiempo estimado: " + tiempoEstimado + " meses" +
           "\n DNI del cliente asociado: " + dniClienteAsociado +
           "\n ID de la vivienda asociada: " + idViviendaAsociada + "\n";
}

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public void setDniClienteAsociado(String dniClienteAsociado) {
        this.dniClienteAsociado = dniClienteAsociado;
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

    public void setNumeroExpediente(int numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public void setDniClienteAsocioado(String dniClienteAsociado) {
        this.dniClienteAsociado = dniClienteAsociado;
    }

    public void setIdViviendaAsociada(int idViviendaAsociada) {
        this.idViviendaAsociada = idViviendaAsociada;
    }

    public int getNumeroExpediente() {
        return numeroExpediente;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public String getDniClienteAsociado() {
        return dniClienteAsociado;
    }

    public int getIdViviendaAsociada() {
        return idViviendaAsociada;
    }
 
    
    
}
