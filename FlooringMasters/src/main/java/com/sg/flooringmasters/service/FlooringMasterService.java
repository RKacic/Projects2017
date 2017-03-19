/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.service;

import com.sg.flooringmasters.dao.FlooringMasterOrderNotFoundException;
import com.sg.flooringmasters.dao.FlooringMasterPersistenceException;
import com.sg.flooringmasters.dto.Order;
import com.sg.flooringmasters.dto.Product;
import com.sg.flooringmasters.dto.TaxRate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringMasterService {

    public List<Order> getAllOrders(LocalDate ld) throws FlooringMasterOrderNotFoundException,
            FlooringMasterPersistenceException;
    
    public Order getOrder(LocalDate ld, int orderNumber) throws FlooringMasterPersistenceException,
            FlooringMasterOrderNotFoundException;
    
    public List<Product> getProducts() throws FlooringMasterInvalidDataException, 
            FlooringMasterPersistenceException;
    
    public List<TaxRate> getTaxRates() throws FlooringMasterInvalidDataException, 
            FlooringMasterPersistenceException;
    
    public Integer getNewOrderNumber() throws FlooringMasterInvalidDataException, 
            FlooringMasterPersistenceException;
    
    public Order addOrder(Order order) throws FlooringMasterPersistenceException, 
            FlooringMasterInvalidDataException;
    
    public Order updateOrder(LocalDate ld, Order orderToUpdate) throws FlooringMasterPersistenceException, 
            FlooringMasterInvalidDataException;
    
    public Order removeOrder(LocalDate ld, int orderNumber) throws FlooringMasterPersistenceException,
            FlooringMasterOrderNotFoundException;
    
    public Boolean setMode(Boolean setTrainingMode);
    
    public Order calculateAllCosts(Order order) throws FlooringMasterInvalidDataException;

}
