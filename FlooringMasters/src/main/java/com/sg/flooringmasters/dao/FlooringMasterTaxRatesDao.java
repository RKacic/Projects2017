/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.dao;

import com.sg.flooringmasters.dto.Product;
import com.sg.flooringmasters.dto.TaxRate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringMasterTaxRatesDao {
    
    public List<TaxRate> getTaxRates() 
            throws FlooringMasterPersistenceException;
    
}
