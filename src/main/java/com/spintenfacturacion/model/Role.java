package com.spintenfacturacion.model;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

@Entity
@Table(name="role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_ID_ROLE")
    @SequenceGenerator(name="SEQ_ID_ROLE",sequenceName="seq_idrole", allocationSize=1)
    private int id;
    @Column(name="nombrerole")
    private String nombrerole;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrerole() {
        return nombrerole;
    }

    public void setNombrerole(String nombrerole) {
        this.nombrerole = nombrerole;
    } 
    
}
