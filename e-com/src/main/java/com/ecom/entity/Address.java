/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecom.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author eldho
 */
@Entity
@Table(name = "address")
public class Address implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int address_id;
    private User userId; 
    private String name;
    private String place;
    private String locality;
    private String pin;
    
}
