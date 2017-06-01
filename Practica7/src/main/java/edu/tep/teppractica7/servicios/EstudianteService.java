/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tep.teppractica7.servicios;

import edu.tep.teppractica7.encapsulaciones.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clariza
 */
public class EstudianteService {

    private static EstudianteService instancia;

    private EstudianteService() {

    }

    public static EstudianteService getInstancia() {
        if (instancia == null) {
            instancia = new EstudianteService();
        }
        return instancia;
    }

    public void insertarEstudiante(Estudiante estudiante) {
        try (Connection conexion = ConexionService.getInstancia().getConexion()) {
            //Estamos usadon sentencias preparadas. Trabaja con comodines
            String sql = "insert into estudiante (matricula, nombre, apellido, telefono)"
                    + " values(?,?,?,?)";
            PreparedStatement pre = conexion.prepareStatement(sql);
            //Inserto los parametros. Con la sentencia preparestament tengo como enviar la informacion
            pre.setString(1, estudiante.getMatricula());
            pre.setString(2, estudiante.getNombre());
            pre.setString(3, estudiante.getApellido());
            pre.setString(4, estudiante.getTelefono());
            //enviar la instruccion comando o sentencia
            int cantidad_filas_insertadas = pre.executeUpdate();
            System.out.println("Las filas inserttadas :" + cantidad_filas_insertadas);
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        //listar
    public List<Estudiante> listarEstudiante(){
        List<Estudiante> listar = new ArrayList<>();
        try (Connection conexion = ConexionService.getInstancia().getConexion()) {
            //La consulta
            String sql = "select * from estudiante";

            PreparedStatement pre = conexion.prepareStatement(sql);
            //Enviar los parametros...si aplica...
            ResultSet result = pre.executeQuery();
            while (result.next()) {//valida que exista inf e incrementa el cursor 
                //obtengo la inf
                Estudiante estu = new Estudiante();
                estu.setMatricula(result.getString("matricula"));
                estu.setNombre(result.getString("nombre"));
                estu.setApellido(result.getString("apellido"));
                estu.setTelefono(result.getString("telefono"));
//Agregando a la lista
                listar.add(estu);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EstudianteService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
        return listar;
    }

    public Estudiante getEstudiante(String matricula){
        Estudiante estu = null;
        try (Connection conexion = ConexionService.getInstancia().getConexion()) {
            //La consulta
            String sql = "select * from estudiante where matricula=?";

            PreparedStatement pre = conexion.prepareStatement(sql);
            //Enviar los parametros...
            pre.setString(1,matricula);
            ResultSet result = pre.executeQuery();
            while (result.next()) {//valida que exista inf e incrementa el cursor 
                //obtengo la inf
                estu = new Estudiante();
                estu.setMatricula(result.getString("matricula"));
                estu.setNombre(result.getString("nombre"));
                estu.setApellido(result.getString("apellido"));
                estu.setTelefono(result.getString("telefono"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EstudianteService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
        return estu;
    }
    
}

