/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.alquileres;

import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author David
 */
public class AlquilerApp extends javax.swing.JFrame {
    
    //Creación de las variables para la conexción de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/bddalquiler";
    private static final String USER = "root";
    private static final String PASSWORD = "Soprano.71";

    /**
     * Creates new form AlquilerApp
     */
    public AlquilerApp() {
        initComponents();
        setTitle("SmartOcupation - Consulta de Alquileres");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        //Panel para registrar el número de expediente 
        JPanel alquilerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        alquilerPanel.add(new JLabel("Número Expediente:"));
        JTextField numeroExpedienteField = new JTextField();
        alquilerPanel.add(numeroExpedienteField);
        add(alquilerPanel);

        //Panel para registrar la fecha de entrada
        JPanel fechaPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        fechaPanel.add(new JLabel("Fecha de Entrada:"));
        JDateChooser dateChooser = new JDateChooser();
        fechaPanel.add(dateChooser);
        add(fechaPanel);

        //Panel para registrar el tiempo estimado del alquiler
        JPanel tiempoEstimadoPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        tiempoEstimadoPanel.add(new JLabel("Tiempo Estimado del Alquiler:"));
        JTextField tiempoEstimadoField = new JTextField();
        tiempoEstimadoPanel.add(tiempoEstimadoField);
        add(tiempoEstimadoPanel);
        add(new JSeparator());

        add(new JLabel("DATOS CLIENTE"));
        
        //Panel para registrar el dno del cliente
        JPanel dniPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        dniPanel.add(new JLabel("DNI:"));
        JTextField dniField = new JTextField();
        dniPanel.add(dniField);
        add(dniPanel);
        
        //Panel que contiene el botón para buscar al cliente
        JPanel buscarClientePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton buscarClienteButton = new JButton("Buscar Cliente");
        buscarClientePanel.add(buscarClienteButton);
        add(buscarClientePanel);
        
        //Panel donde se  verán por pantalla los datos de los clientes para poder copiar el dni del cliente  elegido
        JPanel datosClientePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        TextArea datosClienteArea = new TextArea();
        datosClienteArea.setEditable(false);
        datosClienteArea.setRows(10);
        add(new JScrollPane(datosClienteArea));
        datosClientePanel.add(datosClienteArea);
        add(datosClientePanel);

        add(new JLabel("DATOS VIVIENDA"));

        //Panel donde se registra el id de la vivienda
        JPanel idViviendaPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        idViviendaPanel.add(new JLabel("ID:"));
        JTextField idViviendaField = new JTextField();
        idViviendaPanel.add(idViviendaField);
        add(idViviendaPanel);

        //Panel que contiene el botón para buscar las viviendas
        JPanel buscarViviendaPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton buscarViviendaButton = new JButton("Buscar Vivienda");
        buscarViviendaPanel.add(buscarViviendaButton);
        add(buscarViviendaPanel);

        //Panel donde se verán los datos de las viviendas para pode copiar el id de la vivienda elegida
        JPanel datosViviendaPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        TextArea buscarViviendaArea = new TextArea();
        buscarViviendaArea.setEditable(false);
        buscarViviendaArea.setRows(10);
        add(new JScrollPane(buscarViviendaArea));
        datosViviendaPanel.add(buscarViviendaArea);
        add(datosViviendaPanel);

        //Botones para interactuar con la interfaz
        JPanel botonesPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        //Aplicamos la creación del alquiler con los datos rellenos 
        JButton aplicarButton = new JButton("Aplicar alquiler");
        //Salimos de la aplicación
        JButton salirButton = new JButton("Salir");
        //Volvemos a la pantalla principal
        JButton volverButton = new JButton("Volver");
        botonesPanel.add(aplicarButton);
        botonesPanel.add(volverButton);
        botonesPanel.add(salirButton);
        add(botonesPanel);
        
        //Listener para poder buscar a los clientes en la base de datos
        buscarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                datosClienteArea.setText("");
                try {
                    //Realizamos la conexión a la base de datos con los datos de las variables añadidas
                    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    //Creamos la consulta a la base de datos "cliente"
                    String sql = "SELECT * FROM cliente";
                    
                    //Preparamos la sentencia y la almacenamos en  un ResultSet 
                    try ( PreparedStatement preparedStatement = connection.prepareStatement(sql);  ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Recorremos los resultados y agregamos cada cliente al área de texto
                        while (resultSet.next()) {
                            String nombre = resultSet.getString("nombre");
                            String dni = resultSet.getString("dni");
                            int miembrosFamilia = resultSet.getInt("miembrosFamilia");
                            int ingresos = resultSet.getInt("ingresos");

                            // Creamos un objeto cliente usando la clase cliente
                            clientes cliente = new clientes(nombre, dni, miembrosFamilia, ingresos);

                            // Agregamos la representación de cadena del cliente al área de texto
                            datosClienteArea.append(cliente.toString() + "\n");
                        }
                    }
                    //Recogemos las excepciones tanto por consola como en la propia aplicación
                } catch (SQLException ex) {
                    System.err.println("Error al mostrar datos de clientes: " + ex.getMessage());
                    datosClienteArea.setText("Error al mostrar datos de clientes.");
                }
            }
        });

        //Listener para revisar las viviendas introducidas en la base de datos
        buscarViviendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Limpiamos el área de texto para que no se dupliquen los datos
               buscarViviendaArea.setText("");
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
                                buscarViviendaArea.append(vivienda.toString() + "\n");
                            }
                        }
                        //Recogemos las excepciones
                    } catch (SQLException ex) {
                         ex.printStackTrace();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(viviendaApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        aplicarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Establecemos la conexión a la base de datos
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bddalquiler", "root", "Soprano.71");

                    // Creamos la consulta SQL para insertar datos en la tabla alquiler
                    String sql = "INSERT INTO alquiler (numeroExpediente, fechaEntrada, tiempoEstimado, dniClienteAsociado, idViviendaAsociada) VALUES (?, ?, ?, ?, ?)";

                    // Creamos una sentencia preparada para evitar la inyección de SQL
                    try ( PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        // Establecemos  los valores de los parámetros
                        preparedStatement.setInt(1, Integer.parseInt(numeroExpedienteField.getText()));
                        preparedStatement.setDate(2, new java.sql.Date(dateChooser.getDate().getTime()));
                        preparedStatement.setInt(3, Integer.parseInt(tiempoEstimadoField.getText()));
                        preparedStatement.setString(4, dniField.getText());
                        preparedStatement.setInt(5, Integer.parseInt(idViviendaField.getText()));

                        // Ejecutamos la consulta SQL para insertar los datos
                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Alquiler registrado correctamente en la base de datos.");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo registrar el alquiler. Verifica los datos e intenta nuevamente.");
                        }
                    }
                    //Manejamos las excepciones por pantalla y la confirmación con una ventana emergente
                } catch (SQLException | NumberFormatException ex) {
                    System.err.println("Error al insertar datos en la base de datos: " + ex.getMessage());
                    JOptionPane.showMessageDialog(null, "Error al registrar el alquiler. Consulta los registros para más detalles.");
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
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Alquiler Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new AlquilerApp());
            frame.pack();
            frame.setLocationRelativeTo(null); // Centrar la ventana
            frame.setVisible(true);
        });
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
            java.util.logging.Logger.getLogger(AlquilerApp.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlquilerApp.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlquilerApp.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlquilerApp.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AlquilerApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
