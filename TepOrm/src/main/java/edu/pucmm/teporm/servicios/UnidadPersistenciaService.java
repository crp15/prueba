/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pucmm.teporm.servicios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Clariza
 */
public class UnidadPersistenciaService {
  private static UnidadPersistenciaService instancia;
    private EntityManagerFactory emf;
    
    private UnidadPersistenciaService(){
        emf = Persistence.
            createEntityManagerFactory("MiUnidadPersistencia");
    }
    
    public static UnidadPersistenciaService getInstancia(){
        if(instancia == null){
            instancia = new UnidadPersistenciaService();
        }
        return instancia;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
}
