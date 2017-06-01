/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pucmm.teporm.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author Clariza
 */
@Entity
public class Estudiante implements Serializable {//debe implementar la interfaz Serializable
  @Id
    private String matricula;
private String nombre;
// Constructor vacio obligatorio para las entidades

    public Estudiante() {
    }



    public Estudiante(String matricula, String nombre) {
        this.matricula = matricula;
        this.nombre = nombre;
    }
    @PrePersist
    @PreUpdate
 private void validarMatricula(){
     System.out.println("Verificando la maticul del estudiante");
     if(matricula.length()!=9){
         throw new RuntimeException("Matricula no valida..");
     }
 }
    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
