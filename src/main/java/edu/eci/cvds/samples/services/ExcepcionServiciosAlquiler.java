/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.samples.services;

import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author 2118887
 */
public class ExcepcionServiciosAlquiler extends Exception {
    
    public ExcepcionServiciosAlquiler (String message, PersistenceException e){
        super(message);
    }
    
}
