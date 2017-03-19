/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.dao;

import com.sg.flooringmasters.dto.Product;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringMasterProductsDao {
    
    public List<Product> getProducts() 
            throws FlooringMasterPersistenceException;
    
}
