/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alquileres;

/**
 *
 * @author David
 */
public class clientes {
    
    private String nombre;
    private String DNI;
    private int miembrosFamilia;
    private int ingresos;

    public clientes(String nombre, String DNI, int miembrosFamilia, int ingresos) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.miembrosFamilia = miembrosFamilia;
        this.ingresos = ingresos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public int getMiembrosFamilia() {
        return miembrosFamilia;
    }

    public int getIngresos() {
        return ingresos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setMiembrosFamilia(int miembrosFamilia) {
        this.miembrosFamilia = miembrosFamilia;
    }

    public void setIngresos(int ingresos) {
        this.ingresos = ingresos;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String toString() {
        return "Nombre : " + nombre +
           "\nNúmero de documento: " + DNI +
           "\nMiembros de la familia: " +miembrosFamilia + " miembros" +
           "\nIngresos del cliente: " + ingresos + " €" + "\n";
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
