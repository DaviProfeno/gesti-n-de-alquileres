/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.alquileres;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author David
 */
public class consultaAlquiler extends javax.swing.JFrame {

    JTextArea consultaAlquiler, consultaCliente, consultaVivienda;
    JButton aplicarConsulta, clientesConsulta, viviendasConsulta, salirButton, volverButton;
    //Creación de las variables para la conexción de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/bddalquiler";
    private static final String USER = "root";
    private static final String PASSWORD = "Soprano.71";

    public consultaAlquiler() {

        initComponents();
        setTitle("SmartOcupation - Consulta de alquileres");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        //Panel que copntiene los botones para interactuar con la interfaz
        JPanel consultasPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        aplicarConsulta = new JButton("Consulta alquileres");
        clientesConsulta = new JButton("Consulta clientes");
        viviendasConsulta = new JButton("Consulta viviendas");
        aplicarConsulta.setPreferredSize(new Dimension(150, 30));
        clientesConsulta.setPreferredSize(new Dimension(150, 30));
        viviendasConsulta.setPreferredSize(new Dimension(150, 30));
        consultasPanel.add(aplicarConsulta);
        consultasPanel.add(clientesConsulta);
        consultasPanel.add(viviendasConsulta);

        //Panel con los botones para volver a la pantalla principal y salir de la aplicación
        JPanel botonesPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        salirButton = new JButton("Salir");
        volverButton = new JButton("Volver");
        botonesPanel.add(salirButton);
        botonesPanel.add(volverButton);

        //Panel que contiene las áreas de texto donde se añadirán los datos de lo que se solicite
        JPanel textosPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        consultaAlquiler = new JTextArea();
        consultaAlquiler.setEditable(false);
        consultaAlquiler.setRows(10);
        consultaCliente = new JTextArea();
        consultaCliente.setEditable(false);
        consultaCliente.setRows(10);
        consultaVivienda = new JTextArea();
        consultaVivienda.setEditable(false);
        consultaVivienda.setRows(10);
        textosPanel.add(consultaAlquiler);
        add(new JScrollPane(consultaAlquiler));
        textosPanel.add(consultaCliente);
        add(new JScrollPane(consultaCliente));
        textosPanel.add(consultaVivienda);
        add(new JScrollPane(consultaVivienda));

        add(consultasPanel);
        add(textosPanel);
        add(botonesPanel);

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    pantallaPrincipal pantalla = null;
                    try {
                        pantalla = new pantallaPrincipal();
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Alquileres.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pantalla.setVisible(true);
                    dispose();
                });
            }
        });

        aplicarConsulta.addActionListener((ActionEvent evt) -> {
            // Limpiamos el área de texto antes de mostrar los resultados
            consultaAlquiler.setText("");

            try {
                // Establecemos la conexión con la base de datos
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                try {
                    // Creamos la consulta SQL para obtener los datos de la tabla "alquiler"
                    String sql = "SELECT * FROM alquiler";

                    //Preparamos la sentencia y la almacenamos en  un ResultSet
                    try ( PreparedStatement preparedStatement = connection.prepareStatement(sql);  ResultSet resultSet = preparedStatement.executeQuery()) {

                        // Recorremos los resultados y agregamos cada vivienda al área de texto
                            while (resultSet.next()) {
                                alquiler alquiler = new alquiler(
                                        resultSet.getInt("idAlquiler"),
                                         resultSet.getInt("numeroExpediente"),
                                        resultSet.getDate("fechaEntrada"),
                                        resultSet.getInt("tiempoEstimado"),
                                        resultSet.getString("dniClienteAsociado"),
                                        resultSet.getInt("idViviendaAsociada")
                                );
                                // Agregamos la representación de cadena de la vivienda al área de texto
                               consultaAlquiler.append(alquiler.toString() + "\n");
                            }
                    }
                    //Capturamos las excepciones y las mostramos en la aplicación y por consola trackeandola
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    consultaAlquiler.setText("Error al mostrar datos de clientes.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(consultaAlquiler.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        clientesConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Limpiamos el área de texto antes de mostrar los resultados
                consultaCliente.setText("");

                try {
                    // Establecemos la conexión con la base de datos
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                     // Creamos la consulta SQL para obtener los datos de la tabla "cliente"
                    String sql = "SELECT * FROM cliente";
                    //Preparamos la sentencia y la almacenamos en  un ResultSet
                    try ( PreparedStatement preparedStatement = connection.prepareStatement(sql);  ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Recorremos los resultados y agregamos cada alquiler al área de texto
                        while (resultSet.next()) {
                            String nombre = resultSet.getString("nombre");
                            String dni = resultSet.getString("dni");
                            int miembrosFamilia = resultSet.getInt("miembrosFamilia");
                            int ingresos = resultSet.getInt("ingresos");

                            // Creamos un objeto cliente usando la clase cliente
                            clientes cliente = new clientes(nombre, dni, miembrosFamilia, ingresos);

                            // Agregamos la representación de cadena del cliente al área de texto
                            consultaCliente.append(cliente.toString() + "\n");
                        }
                    }
                    //Capturamos las excepciones por pantalla y por consola
                } catch (SQLException ex) {
                    System.err.println("Error al mostrar datos de clientes: " + ex.getMessage());
                    consultaCliente.setText("Error al mostrar datos de clientes.");
                }
            }
        });

        viviendasConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Limpiamos el área de texto antes de mostrar los resultados
                consultaVivienda.setText("");

                try {
                    //Establecemos la conexión con la base de datos
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

                    try {
                         // Creamos la consulta SQL para obtener los datos de la tabla "vivienda"
                        String sql = "SELECT * FROM vivienda";

                        //Preparamos la sentencia y la almacenamos en  un ResultSet
                        try ( PreparedStatement preparedStatement = connection.prepareStatement(sql);  ResultSet resultSet = preparedStatement.executeQuery()) {

                            // Recorremos los resultados y agregamos cada alquiler al área de texto
                            while (resultSet.next()) {
                                vivienda vivienda = new vivienda(
                                        resultSet.getInt("idVivienda"),
                                        resultSet.getString("ubicacion"),
                                        resultSet.getInt("metrosCuadrados"),
                                        resultSet.getInt("habitaciones"),
                                        resultSet.getInt("banos"),
                                        resultSet.getInt("precioMensual"),
                                        resultSet.getInt("codigo")
                                );
                                // Agregamos la representación de cadena del cliente al área de texto
                                consultaVivienda.append(vivienda.toString() + "\n");
                            }
                        }
                        //Capturamos las excepciones 
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(viviendaApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consultaAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new consultaAlquiler().setVisible(true);
        });
    }

   public void aplicarConsultaAlquiler() {
    // Limpiamos el área de texto antes de mostrar los resultados
    consultaAlquiler.setText("");

    try {
        // Establecemos la conexión con la base de datos
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Creamos la consulta SQL para obtener los datos de la tabla "alquiler"
            String sql = "SELECT * FROM alquiler";

            // Preparamos la sentencia y la almacenamos en un ResultSet
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Recorremos los resultados y agregamos cada alquiler al área de texto
                while (resultSet.next()) {
                    alquiler alquiler = new alquiler(
                            resultSet.getInt("idAlquiler"),
                            resultSet.getInt("numeroExpediente"),
                            resultSet.getDate("fechaEntrada"),
                            resultSet.getInt("tiempoEstimado"),
                            resultSet.getString("dniClienteAsociado"),
                            resultSet.getInt("idViviendaAsociada")
                    );
                    // Agregamos la representación de cadena del alquiler al área de texto
                    consultaAlquiler.append(alquiler.toString() + "\n");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                consultaAlquiler.setText("Error al mostrar datos de alquileres.");
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(consultaAlquiler.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
