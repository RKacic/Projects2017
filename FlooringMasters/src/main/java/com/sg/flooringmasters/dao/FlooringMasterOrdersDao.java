/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.dao;

import com.sg.flooringmasters.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringMasterOrdersDao {
    
    public Order addOrder(Order order)  
            throws FlooringMasterPersistenceException;
    
    public Order removeOrder(LocalDate ld, int orderNumber)
            throws FlooringMasterPersistenceException,
            FlooringMasterOrderNotFoundException;
    
    public Order updateOrder(LocalDate orderDate, Order updatedOrder)
            throws FlooringMasterPersistenceException;
    
    public List<Order> getAllOrders(LocalDate ld)
            throws FlooringMasterPersistenceException,
            FlooringMasterOrderNotFoundException;
    
    public Order getOrder(LocalDate ld, int orderNumber)
            throws FlooringMasterPersistenceException, FlooringMasterOrderNotFoundException;
    
    public Boolean setTrainingMode(Boolean setTrainingMode);
}
