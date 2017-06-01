/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tep.teppractica7;

import edu.tep.teppractica7.encapsulaciones.Estudiante;
import edu.tep.teppractica7.servicios.ConexionService;
import edu.tep.teppractica7.servicios.EstudianteService;
import edu.tep.teppractica7.utilidades.JsonTransformer;
import java.util.List;
import static spark.Spark.*;

/**
 *
 * @author Clariza
 */
public class Main {

    //Teniendo la referencia de la ConexionService
    private static ConexionService conexionService = ConexionService.getInstancia();

    public static void main(String[] args) {
        get("/", (request, response) -> {
            //Probando la conexin
            conexionService.pruebaConexion();
            return "Practica #7";
        });

        get("/insertar", (request, response) -> {
            //Recuperando los parametros de la URL desde del ?
            //To do validaciones

            Estudiante est = new Estudiante();
            est.setMatricula(request.queryParams("matricula"));
            est.setNombre(request.queryParams("nombre"));
            est.setApellido(request.queryParams("apellido"));
            est.setTelefono(request.queryParams("telefono"));
            //Insertar en la base de datos
            EstudianteService.getInstancia().insertarEstudiante(est);
            return String.format("Insertando el estudiante %s - %s", est.getMatricula(), est.getNombre());
        });

        get("/listar", (request, response) -> {
            //obteniendo la lista
            List<Estudiante> listarEstudiante = EstudianteService.getInstancia().listarEstudiante();
            return listarEstudiante;
        }, new JsonTransformer());
        
       get("/estudiante/:matricula", (request, response) -> {
            //obteniendo la lista
            Estudiante estu= EstudianteService.getInstancia().getEstudiante(request.params("matricula"));
            return estu;
        }, new JsonTransformer()); 
    }

}
