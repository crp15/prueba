/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tep.teppractica7.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase aplicando el patron singleton simplificado
 *
 * @author Clariza
 */
public class ConexionService {
    //Una propiedad privada pero estatica

    public static ConexionService instancia;

    private ConexionService() {//constructor es privado
        registrarDriver();
    }

    //Unico metodo para acceder a la instancia de la clase
    public static ConexionService getInstancia() {
        if (instancia == null) {
            instancia = new ConexionService();
        }
        return instancia;
    }

    //ahora daremos los pasos para conectarnos a la base de datos JDBC
    //Paso #1 conectarnos al driver
    private void registrarDriver() {
        try {
            Class.forName("org.h2.Driver");//Esto lo da el proveeedor, para eso fuimos a la pag de H2
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionService.class.getName()).log(Level.SEVERE, null, ex);
        }//Esto ultimo lo incorporamos al corregir el error, bombillito y segunda opcion Subround...
    }

    //Paso #2 Crear objeto de conexion
    public Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/practica7", "sa", "");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void pruebaConexion() {
        Connection conexion = getConexion();

//En este punto estoy conectado...
        System.out.println("Conectado ");
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
