/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class FlooringMasterOrderNumberDaoFileImpl implements FlooringMasterOrderNumberDao{

    private final String NUM_FILE = "orderNumbers/newOrderNumber.txt";
    
    private void saveOrderNumberHistory(int orderNumber) throws FlooringMasterPersistenceException {

        String output = Integer.toString(orderNumber);

        try (PrintWriter out = new PrintWriter(new FileWriter(NUM_FILE))) {
            out.println(output);
            out.flush();
            out.close();

        } catch (IOException e) {
            throw new FlooringMasterPersistenceException(e.getMessage());
        }

        
    }
    
    private int loadOrderNumber() throws FlooringMasterPersistenceException {
        
        int orderNumber; 
        
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(NUM_FILE)))) {
            orderNumber = Integer.parseInt(sc.nextLine());
        } catch (FileNotFoundException e) {
            throw new FlooringMasterPersistenceException(e.getMessage());
        }

        return orderNumber ;
        
    }
    
    
    @Override
    public int getNewOrderNumber() throws FlooringMasterPersistenceException {
        int newOrderNumber = loadOrderNumber() +1;
        saveOrderNumberHistory(newOrderNumber);
        return newOrderNumber;
    }
    
}
