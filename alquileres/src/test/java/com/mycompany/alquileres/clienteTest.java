/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alquileres;

/**
 *
 * @author David
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class clienteTest {

    private static final String URL = "jdbc:mysql://localhost:3306/bddalquiler";
    private static final String USER = "root";
    private static final String PASSWORD = "Soprano.71";
    private clienteApp clienteApp;

    @Before
    public void setUp() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        try ( Statement statement = connection.createStatement()) {
            // Verificar si la tabla ya existe antes de crearla
            ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE 'cliente'");
            if (!resultSet.next()) {
                // La tabla no existe, así que la creamos
                statement.execute("CREATE TABLE cliente (idCliente INT PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(255), dni VARCHAR(255), miembrosFamilia INT, ingresos INT)");
            }
        } finally {
            // Cerrar la conexión
            connection.close();
        }
        clienteApp = new clienteApp();
    }

    @After
    public void tearDown() throws SQLException {
        // Limpiar o cerrar recursos después de las pruebas
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        try ( Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS cliente");
        }
    }

    @Test
    public void testClienteConsulta() {
        // Configurar datos específicos de prueba
        insertarDatosPrueba();

        // Ejecutar el método bajo prueba
        clienteApp.aplicarConsultaCliente();

// Verificar los resultados utilizando Hamcrest
        assertThat(clienteApp.mostrarTextArea.getText(), allOf(
                containsString("Nombre : anonimo"),
                containsString("Número de documento: 123456789A"),
                containsString("Miembros de la familia: 2 miembros"),
                containsString("Ingresos del cliente: 1000 €")
        ));
    }

// Método de utilidad para insertar datos de prueba
    private void insertarDatosPrueba() {
        try ( Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);  Statement statement = connection.createStatement()) {
            // Insertar datos de prueba en la tabla cliente
            statement.execute("INSERT INTO cliente (nombre, dni, miembrosFamilia, ingresos) VALUES ('anonimo', '123456789A', 2, 1000)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
