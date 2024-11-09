/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.work.report_support_backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ferdi
 */
@Entity
public class User {

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uid;
    
    private String username;
    private String password;

}
