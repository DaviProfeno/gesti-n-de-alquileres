/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.alquileres;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author David
 */
public class clienteApp extends javax.swing.JFrame {

    private JLabel nombreLabel, dniLabel, miembrosFamiliaLabel, ingresosLabel;
    private JTextField nombreTextField, dniTextField, miembrosFamiliaTextField, ingresosTextField;
    JButton aplicarButton;
    JButton mostrarButton;
    private JButton volverButton, salirButton;
    JTextArea mostrarTextArea;
    //Variables para realizar la conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/bddalquiler";
    private static final String USER = "root";
    private static final String PASSWORD = "Soprano.71";
    /**
     * Creates new form clienteApp
     */
    public clienteApp() {

        initComponents();
        setTitle("Cliente");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        //Panel  para introducir su nombre
        JPanel nombrePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        nombreLabel = new JLabel("Nombre del cliente:");
        nombreTextField = new JTextField();
        nombrePanel.add(nombreLabel);
        nombrePanel.add(nombreTextField);

        //Panel para introducir el dni del cliente
        JPanel dniPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        dniLabel = new JLabel("DNI cliente:");
        dniTextField = new JTextField();
        dniPanel.add(dniLabel);
        dniPanel.add(dniTextField);

        //Panel para introducir los miembros de la familia
        JPanel miembrosFamiliaPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        miembrosFamiliaLabel = new JLabel("Miembros de la familia:");
        miembrosFamiliaTextField = new JTextField();
        miembrosFamiliaPanel.add(miembrosFamiliaLabel);
        miembrosFamiliaPanel.add(miembrosFamiliaTextField);

        //Panel para introducir los ingrasos del cliente
        JPanel ingresosPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        ingresosLabel = new JLabel("Ingresos:");
        ingresosTextField = new JTextField();
        ingresosPanel.add(ingresosLabel);
        ingresosPanel.add(ingresosTextField);

        //Botones para interactuar con la interfaz
        JPanel botonesPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        //Añadimos los datos del cliente a la base de datos
        aplicarButton = new JButton("Añadir cliente");
        //Mostramos todos los datos de los clientes en la base de datos
        mostrarButton = new JButton("Mostrar clientes");
        //Volvemos a la pantalla principal
        volverButton = new JButton("Volver");
        //Salimos de la aplicación
        salirButton = new JButton("Salir");
        botonesPanel.add(aplicarButton);
        botonesPanel.add(mostrarButton);
        botonesPanel.add(volverButton);
        botonesPanel.add(salirButton);

        //Panel para mostrar los datos de los clientes
        JPanel mostrarPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        mostrarTextArea = new JTextArea();
        mostrarTextArea.setEditable(false);
        mostrarTextArea.setRows(15);
        add(new JScrollPane(mostrarTextArea));

        add(nombrePanel);
        add(dniPanel);
        add(miembrosFamiliaPanel);
        add(ingresosPanel);
        add(botonesPanel);
        add(mostrarPanel);

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
        //Listener par mostrar los datos de los clientes
        mostrarButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Limpiamos el área de texto antes de mostrar los datos para que no se nos dupliquen
        mostrarTextArea.setText("");

        try {
            //Realizamos la conexión con la base de datos
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //Sentencia SQL para seleccionar los datos de los clientes
            String sql = "SELECT * FROM cliente";
            
            //Preparamos la sentencia y la almacenamos en  un ResultSet 
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 // Recorremos los resultados y agregamos cadacliente al área de texto
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                // Recorremos los resultados y agregamos cada cliente al área de texto
                while (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String dni = resultSet.getString("dni");
                    int miembrosFamilia = resultSet.getInt("miembrosFamilia");
                    int ingresos = resultSet.getInt("ingresos");

                    // Creamos un objeto cliente usando tu clase cliente
                    clientes cliente = new clientes(nombre, dni, miembrosFamilia, ingresos);

                    // Agregamos la representación de cadena del cliente al área de texto
                    mostrarTextArea.append(cliente.toString() + "\n");
                }
            }
            //Capturamos las excepciones tanto por consola como en la aplicación mostrando un mensaje
        } catch (SQLException ex) {
            System.err.println("Error al mostrar datos de clientes: " + ex.getMessage());
            mostrarTextArea.setText("Error al mostrar datos de clientes.");
        }
    }
});
        //Guarda los datos introducidos en los cuadros de texto en la base de datos
        aplicarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Establecermosla conexión a la base de datos
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

                // Creamos la consulta SQL para insertar datos en la tabla cliente
                String sql = "INSERT INTO cliente (nombre, dni, miembrosFamilia, ingresos) VALUES (?, ?, ?, ?)";

                // Creamos una sentencia preparada para evitar la inyección de SQL
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    // Establecemos los valores de los parámetros
                    preparedStatement.setString(1, nombreTextField.getText());
                    preparedStatement.setString(2, dniTextField.getText());
                    preparedStatement.setInt(3, Integer.parseInt(miembrosFamiliaTextField.getText()));
                    preparedStatement.setInt(4, Integer.parseInt(ingresosTextField.getText()));

                    // Ejecutamos la consulta SQL para insertar los datos
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Cliente registrado correctamente en la base de datos.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo registrar el cliente. Verifica los datos e intenta nuevamente.");
                    }
                }
                //Capturamos las excepciones tanto por consola como por un mensaje de error
            } catch (SQLException | NumberFormatException ex) {
                System.err.println("Error al insertar datos en la base de datos: " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al registrar el cliente. Consulta los registros para más detalles.");
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(clienteApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(clienteApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(clienteApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(clienteApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new clienteApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

 public void aplicarConsultaCliente() {
    // Limpiamos el área de texto antes de mostrar los resultados
   mostrarTextArea.setText("");

    try {
        // Establecemos la conexión con la base de datos
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Creamos la consulta SQL para obtener los datos de la tabla "alquiler"
            String sql = "SELECT * FROM cliente";

            // Preparamos la sentencia y la almacenamos en un ResultSet
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Recorremos los resultados y agregamos cada alquiler al área de texto
                while (resultSet.next()) {
                   clientes cliente = new clientes(
                            resultSet.getString("nombre"),
                            resultSet.getString("dni"),
                            resultSet.getInt("miembrosFamilia"),
                            resultSet.getInt("ingresos")
                    );
                    // Agregamos la representación de cadena del alquiler al área de texto
                    mostrarTextArea.append(cliente.toString() + "\n");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                mostrarTextArea.setText("Error al mostrar datos de clientes.");
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        Logger.getLogger(consultaAlquiler.class.getName()).log(Level.SEVERE, null, ex);
    }
 }
}