package com.mycompany.alquileres;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


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

public class alquilerTest {

    private static final String URL = "jdbc:mysql://localhost:3306/bddalquiler";
    private static final String USER = "root";
    private static final String PASSWORD = "Soprano.71";

    private consultaAlquiler consultaAlquiler;

    @Before
    public void setUp() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        try ( Statement statement = connection.createStatement()) {
            // Verificar si la tabla ya existe antes de crearla
            ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE 'alquiler'");
            if (!resultSet.next()) {
                // La tabla no existe, así que la creamos
                statement.execute("CREATE TABLE alquiler (idAlquiler INT PRIMARY KEY, numeroExpediente INT, fechaEntrada DATE, tiempoEstimado INT, dniClienteAsociado VARCHAR(255), idViviendaAsociada INT)");
            }
        } finally {
            // Cerrar la conexión
            connection.close();
        }

        consultaAlquiler = new consultaAlquiler();
    }

    @After
    public void tearDown() throws SQLException {
        // Limpiar o cerrar recursos después de las pruebas
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        try ( Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS alquiler");
        }
    }

    @Test
    public void testAlquilerConsulta() {
        // Configurar datos específicos de prueba
        insertarDatosPrueba();

        // Ejecutar el método bajo prueba
        consultaAlquiler.aplicarConsultaAlquiler();

      // Verificar los resultados utilizando Hamcrest
    assertThat(consultaAlquiler.consultaAlquiler.getText(), allOf(
        containsString("Número de expediente: 123"),
        containsString("Fecha de entrada: 2024-01-13"),
        containsString("Tiempo estimado: 30 meses"),
        containsString("DNI del cliente asociado: 123456789A"),
        containsString("ID de la vivienda asociada: 1")
    ));
}
    


// Método de utilidad para insertar datos de prueba
    private void insertarDatosPrueba() {
        try ( Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);  Statement statement = connection.createStatement()) {
            // Insertar datos de prueba en la tabla alquiler
            statement.execute("INSERT INTO alquiler (idAlquiler, numeroExpediente, fechaEntrada, tiempoEstimado, dniClienteAsociado, idViviendaAsociada) VALUES (1, 123, '2024-01-13', 30, '123456789A', 1)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
