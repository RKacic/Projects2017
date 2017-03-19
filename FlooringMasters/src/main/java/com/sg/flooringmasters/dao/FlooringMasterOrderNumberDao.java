/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.dao;

/**
 *
 * @author apprentice
 */
public interface FlooringMasterOrderNumberDao {
    
    public int getNewOrderNumber()
            throws FlooringMasterPersistenceException;
    
}
