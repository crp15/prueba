/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tep.practicando.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clariza
 */
public class ConexionService {
    public static ConexionService instancia;
    private ConexionService(){
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
    //Paso #1, nos conectamos al driver
    private void registrarDriver(){
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionService.class.getName()).log(Level.SEVERE, null, ex);
        }
}
   
    //Paso #2- Creación Objeto de Conexión
public Connection getConexion(){
Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/practicando", "sa", "");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
}
  
//Podemos en este punto probar la coneccion
public void probandoConeccion(){
    Connection con = getConexion();
       System.out.println("Estoy conectada.Ok"); 
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionService.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}