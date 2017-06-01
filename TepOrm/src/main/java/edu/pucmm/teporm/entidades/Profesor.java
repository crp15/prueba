/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pucmm.teporm.entidades;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Clariza
 */
@Entity
public class Profesor implements Serializable {

    @Id
    private String cedula;
    private String nombre;
    @OneToMany(mappedBy = "profesor")//Aplicando l bidireccion
    private Set<Curso> listaCurso;

    public Profesor(){//es obligatoro el constructor vacio
    }
    public Profesor(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public Set<Curso> getListaCurso(){
        return listaCurso;
    }
    public void setListaCurso(Set<Curso> listaCurso){
        this.listaCurso=listaCurso;
    } 
    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
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
