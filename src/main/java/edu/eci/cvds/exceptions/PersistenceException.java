/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.exceptions;

/**
 *
 * @author 2118887
 */

public class PersistenceException extends Exception {

    

    public PersistenceException(String message, Throwable e) {
        super(message);
    }
}       
