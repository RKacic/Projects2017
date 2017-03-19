/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.dao;

import com.sg.flooringmasters.service.*;

/**
 *
 * @author apprentice
 */
public class FlooringMasterOrderNotFoundException extends Exception {

    public FlooringMasterOrderNotFoundException(String message) {
        super(message);
    }

    public FlooringMasterOrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
