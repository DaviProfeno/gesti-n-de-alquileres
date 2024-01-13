/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.alquileres;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author David
 */
public class viviendaApp extends javax.swing.JFrame {

    private JLabel ubicacionLabel, metrosCuadradosLabel, habitacionesLabel, banosLabel, precioMensualLabel, codigoLabel;
    private JTextField ubicacionTextField, metrosCuadradosTextField, habitacionesTextField, banosTextField, precioMensualTextField, codigoTextField;
    private JButton aplicarButton, mostrarButton, volverButton, salirButton;
    private JTextArea mostrarTextArea;
    //Creación de las variables para la conexción de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/bddalquiler";
    private static final String USER = "root";
    private static final String PASSWORD = "Soprano.71";

    public viviendaApp() {
        initComponents();
        setTitle("SmartOcupation - Añadir Vivienda");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //Panel para registrar la ubicación
        JPanel ubicacionPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        ubicacionLabel = new JLabel("Ubicación:");
        ubicacionTextField = new JTextField();
        ubicacionPanel.add(ubicacionLabel);
        ubicacionPanel.add(ubicacionTextField);
        //Panel para registrar los metros cuadrados
        JPanel metrosPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        metrosCuadradosLabel = new JLabel("Metros Cuadrados:");
        metrosCuadradosTextField = new JTextField();
        metrosPanel.add(metrosCuadradosLabel);
        metrosPanel.add(metrosCuadradosTextField);
        //Panel para registrar las habitaciones
        JPanel habitacionesPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        habitacionesLabel = new JLabel("Habitaciones:");
        habitacionesTextField = new JTextField();
        habitacionesPanel.add(habitacionesLabel);
        habitacionesPanel.add(habitacionesTextField);
        //Panel para registrar los baños
        JPanel banosPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        banosLabel = new JLabel("Baños:");
        banosTextField = new JTextField();
        banosPanel.add(banosLabel);
        banosPanel.add(banosTextField);
        //Panel para registrar el precio mensual
        JPanel precioPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        precioMensualLabel = new JLabel("Precio Mensual:");
        precioMensualTextField = new JTextField();
        precioPanel.add(precioMensualLabel);
        precioPanel.add(precioMensualTextField);
        //Panel para registrar el código del alquiler
        JPanel codigoPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        codigoLabel = new JLabel("Código:");
        codigoTextField = new JTextField();
        codigoPanel.add(codigoLabel);
        codigoPanel.add(codigoTextField);
        //Pabel con botones para interactuar con la interfaz
        JPanel botonesPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        //Botón que añade la vivienda a la base de datos con los atributos intreoducidos
        aplicarButton = new JButton("Añadir vivienda");
        //Botón para mostrar todas las viviendas
        mostrarButton = new JButton("Mostrar viviendas");
        //Botón para volver a la pantalla principal
        volverButton = new JButton("Volver");
        //Botón para salir de la aplicación
        salirButton = new JButton("Salir");
        botonesPanel.add(aplicarButton);
        botonesPanel.add(mostrarButton);
        botonesPanel.add(volverButton);
        botonesPanel.add(salirButton);
        //Panel para mostar los datos de las viviendas
        JPanel mostrarPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        mostrarTextArea = new JTextArea();
        mostrarTextArea.setEditable(false);
        mostrarTextArea.setRows(15);
        add(new JScrollPane(mostrarTextArea));

        add(ubicacionPanel);
        add(metrosPanel);
        add(habitacionesPanel);
        add(banosPanel);
        add(precioPanel);
        add(codigoPanel);
        add(botonesPanel);
        add(mostrarPanel);
        //Listener para aplicar la adición de la vivienda a la base de datos
        aplicarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Obtener los valores de los campos
                String ubicacion = ubicacionTextField.getText();
                int metros = Integer.parseInt(metrosCuadradosTextField.getText());
                int habitaciones = Integer.parseInt(habitacionesTextField.getText());
                int banos = Integer.parseInt(banosTextField.getText());
                int precioAlquiler = Integer.parseInt(precioMensualTextField.getText());
                int codigo = Integer.parseInt(codigoTextField.getText());

                try {
                    //Realizamos la conexión a la base de datos con los datos de las variables añadidas
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    //Creamos la consulta a la base de datos "cliente"
                    String sql = "INSERT INTO vivienda (ubicacion, metrosCuadrados, habitaciones, banos, precioMensual, codigo) VALUES (?, ?, ?, ?, ?, ?)";

                    ///Preparamos la sentencia y la almacenamos en  un statement
                    try ( PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.setString(1, ubicacion);
                        preparedStatement.setInt(2, metros);
                        preparedStatement.setInt(3, habitaciones);
                        preparedStatement.setInt(4, banos);
                        preparedStatement.setInt(5, precioAlquiler);
                        preparedStatement.setInt(6, codigo);

                        // Ejecutamos la sentencia
                        preparedStatement.executeUpdate();
                    }
                    //Capturamos las excepciones
                    JOptionPane.showMessageDialog(null, "Vivienda registrada correctamente.");
                } catch (SQLException | NumberFormatException ex) {
                    ex.printStackTrace();
                }
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

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //Listener para mostrar todas las viviendas
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Limpiamos el área de texto para que no se dupliquen los datos
                mostrarTextArea.setText("");
                try {
                    //Realizamos la conexción a la base de datos en base a las variables proporcionadas
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

                    try {
                        //Lanzamos la consulta a la base de datos
                        String sql = "SELECT * FROM vivienda";

                       // Preparamos la sentencia y la almacenamos en un resulset
                        try ( PreparedStatement preparedStatement = connection.prepareStatement(sql);  ResultSet resultSet = preparedStatement.executeQuery()) {

                            // Recorremos los resultados y agregamos cada vivienda al área de texto
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
                                // Agregamos la representación de cadena de la vivienda al área de texto
                                mostrarTextArea.append(vivienda.toString() + "\n");
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
        viviendaApp viviendaApp = new viviendaApp();
        viviendaApp.setVisible(true);
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(viviendaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viviendaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viviendaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viviendaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new viviendaApp().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
