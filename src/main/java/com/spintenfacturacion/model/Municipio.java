/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spintenfacturacion.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="municipio")
public class Municipio implements Serializable{
   @Id
   @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_MUNICIPIO")
   @SequenceGenerator(name="SEQ_ID_MUNICIPIO",sequenceName="seq_idmunicipio", allocationSize=1)
   private int id;
   @ManyToOne(cascade=CascadeType.PERSIST)
   @JoinColumn(name="iddepto")
   private Departamento departamento;
   @Column(name="nombremunicipio")
   private String nombremunicipio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getNombremunicipio() {
        return nombremunicipio;
    }

    public void setNombremunicipio(String nombremunicipio) {
        this.nombremunicipio = nombremunicipio;
    }
       
}
