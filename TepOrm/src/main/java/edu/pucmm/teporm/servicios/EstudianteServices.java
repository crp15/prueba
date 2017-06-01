/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pucmm.teporm.servicios;

import edu.pucmm.teporm.entidades.Curso;
import edu.pucmm.teporm.entidades.Estudiante;
import edu.pucmm.teporm.entidades.Profesor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Clariza
 */
public class EstudianteServices {

    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> lista = null;
        EntityManager em = UnidadPersistenciaService.getInstancia().getEntityManager();
        lista = em.createQuery("select e from Estudiante e").getResultList();
        em.close();
        return lista;
    }

    public Estudiante getEstudiantePorMatricula(String matricula) {
        //Consulta en HQl indicando un parametro
        String hql = "from Estudiante e where e.matricula=:matricula";
        //Abriendo la conexion
        EntityManager em = UnidadPersistenciaService.getInstancia().getEntityManager();
        //realizando la consulta y enviando el valor  de los parametros
        Estudiante estudiante = em.createQuery(hql, Estudiante.class).setParameter("matricula", matricula).getSingleResult();
        //Ceerrando el recurso
        em.close();
        return estudiante;
    }

    public Estudiante insertarEstudiante(Estudiante estudiante) {
        EntityManager em = UnidadPersistenciaService.getInstancia().getEntityManager();
        em.getTransaction().begin();
        em.persist(estudiante);
        em.getTransaction().commit();
        em.close();
        return estudiante;
    }
    public Curso insertarCurso(String nombreCurso){
        EntityManager em = UnidadPersistenciaService.getInstancia().getEntityManager();
        em.getTransaction().begin();//esta linea es obligatoria cuando voy a hacer inserciones
        //buscando profesor
        Profesor profesor= em.find(Profesor.class, "001-0001001-1");
        if(profesor==null){
            profesor = new Profesor("001-0001001-1", "Hilario Tavarez");
            em.persist(profesor);
        }
        Curso curso= new Curso();
        curso.setNombre(nombreCurso);
        curso.setProfesor(profesor);
               
        //agregando los estudiantes registrados.
        List<Estudiante> listarEstudiantes = listarEstudiantes();
        Set<Estudiante> listaSetEstudiante = new HashSet<>(listarEstudiantes);
        curso.setListaEstudiante(listaSetEstudiante);
        
        //Guardando la información...
        em.persist(curso);
        
        em.getTransaction().commit();
        em.close();
        
        return curso;
    }
    public void listarCursosProfesor(String cedula){
       EntityManager em = UnidadPersistenciaService.getInstancia().getEntityManager();
       //Buscando alprofesor dado la cedula (clave primaria)
       Profesor profesor =em.find(Profesor.class, cedula);
       //Listando los cursos que tiene el profesor
        System.out.println("Cursos asignado al profesor: "+profesor.getNombre());
        for(Curso curso: profesor.getListaCurso()){
            System.out.println("\t"+curso.getNombre());
        }
        //cerrando
        em.close();
    }
    public void actualizarProfesor(String cedula, String nombre){
     //Abriendo la conexion mediante Hiberbnate
     EntityManager em = UnidadPersistenciaService.getInstancia().getEntityManager();
     em.getTransaction().begin();
     //Buscando profesor dado la cedul
     Profesor profesor =em.find(Profesor.class, cedula);
     //cerrando
     em.close();
   
    }
    public Profesor getProfesorPorCedula(String cedula){
        EntityManager em = UnidadPersistenciaService.
                getInstancia().getEntityManager();
       
        //buscando la profesor dado la cedula (clave primaria)
        Profesor profesor = em.find(Profesor.class, cedula);             
        
        //cerrando
        em.close();
        //
        return profesor;
    }
    
    public void actualizarViaMerge(Profesor profesor){
         EntityManager em = UnidadPersistenciaService.getInstancia().getEntityManager();
     em.getTransaction().begin();
     em.merge(profesor);
     em.getTransaction().commit();
     em.close();
    }
    public void consultaNativaSql(){
        String sql="select nombre from estudiante";//usando Sql
        EntityManager em = UnidadPersistenciaService.getInstancia().getEntityManager();
        
        Query nativo = em.createNativeQuery(sql);//sql
        List<String> resultList=nativo.getResultList();
        for(String nombre:resultList){
            System.out.println("nombre:"+nombre);
        }
        em.close();
       
    }
    }
   

