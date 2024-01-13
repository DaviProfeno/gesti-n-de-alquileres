/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.alquileres;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author David
 */
public class Alquileres {
    //Invocamos  la interfaz principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            pantallaPrincipal pantalla = null;
            try {
                pantalla = new pantallaPrincipal();
            } catch (MalformedURLException ex) {
                Logger.getLogger(Alquileres.class.getName()).log(Level.SEVERE, null, ex);
            }
            pantalla.setVisible(true);
        });

    }
}
