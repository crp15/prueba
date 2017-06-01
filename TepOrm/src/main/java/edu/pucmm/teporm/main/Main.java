package edu.pucmm.teporm.main;

import edu.pucmm.teporm.entidades.Estudiante;
import edu.pucmm.teporm.entidades.Profesor;
import edu.pucmm.teporm.servicios.EstudianteServices;
import edu.pucmm.teporm.servicios.UnidadPersistenciaService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EstudianteServices estudianteServices = new EstudianteServices();
        List<Estudiante> listarEstudiantes = new EstudianteServices().listarEstudiantes();

        for (Estudiante estudiante : listarEstudiantes) {
            System.out.printf("%s -%s\n", estudiante.getMatricula(), estudiante.getNombre());
        }
        //
        Estudiante estudiante = estudianteServices.getEstudiantePorMatricula("1981-0151");
        System.out.printf("Consultando estudiante : %s - %s\n", estudiante.getMatricula(), estudiante.getNombre());
//
        SimpleDateFormat formatoFecha = new SimpleDateFormat("mmss");
        String secuenciaMatricula = formatoFecha.format(new Date());
//
        Estudiante estudianteNuevo = new Estudiante("2017-" + secuenciaMatricula, "Joan Rosario");
        estudianteServices.insertarEstudiante(estudianteNuevo);
        estudianteServices.insertarCurso("Mi Curso");
        estudianteServices.listarCursosProfesor("001-0001001-1");
        Profesor profesor = estudianteServices.getProfesorPorCedula("001-0001001-1");
        System.out.println("Nombre del profesor: " + profesor.getNombre());
        profesor.setNombre("Carlos Camacho");
        System.out.println("Nombre del profesor actualizado objeto: " + profesor.getNombre());
        estudianteServices.actualizarViaMerge(profesor);
        estudianteServices.consultaNativaSql();
    }
}
