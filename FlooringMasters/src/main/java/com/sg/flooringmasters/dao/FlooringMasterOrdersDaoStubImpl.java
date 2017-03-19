/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.dao;

import com.sg.flooringmasters.dto.Order;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class FlooringMasterOrdersDaoStubImpl implements FlooringMasterOrdersDao{

    private boolean inTrainingMode = false;
    public static final String DELIMITER = ",";
    private Map<Integer, Order> ordersMap = new HashMap<>();

    public FlooringMasterOrdersDaoStubImpl() {

    }

    private void readFromFile(LocalDate ld) throws FlooringMasterPersistenceException {
        boolean fileExists = searchForFile(ld);
        if(fileExists == true){
            String fileName = makeFileName(ld);
            Scanner sc;
            try {
                sc = new Scanner(
                        new BufferedReader(
                                new FileReader("tests/"+fileName)));
            } catch (FileNotFoundException e) {
                throw new FlooringMasterPersistenceException("File does not exist for that day", e);
            }

            String currentLine;
            String[] tokens;
            sc.nextLine();
            while (sc.hasNextLine()) {
                currentLine = sc.nextLine();
                tokens = currentLine.split(DELIMITER);
                Order order = new Order();
                order.setOrderNumber(Integer.parseInt(tokens[0]));
                order.setOrderDate(LocalDate.parse(tokens[1]));
                order.setCustomerName(tokens[2].replace('\uFFFD', ','));
                order.setState(tokens[3]);
                order.setTaxRate(new BigDecimal(tokens[4]));
                order.setProductType(tokens[5]);
                order.setAreaSQFT(new BigDecimal(tokens[6]));
                order.setMaterialCostPerSQFT(new BigDecimal(tokens[7]));
                order.setLaborCostPerSQFT(new BigDecimal(tokens[8]));
                order.setMaterialCostTotal(new BigDecimal(tokens[9]));
                order.setLaborCostTotal(new BigDecimal(tokens[10]));
                order.setTaxCostTotal(new BigDecimal(tokens[11]));
                order.setOrderTotal(new BigDecimal(tokens[12]));

                ordersMap.put(order.getOrderNumber(), order);
            }
            if(inTrainingMode == false){
                File file = new File(fileName);
                file.delete();
            }
        }
    }

    private void writeToFile() throws FlooringMasterPersistenceException {
        //todo create write method notes
        if (inTrainingMode == false) {
            ArrayList<Order> currentOrdersList = new ArrayList<Order>(ordersMap.values());

            FileWriter fw = null;
            BufferedWriter bw = null;
            PrintWriter pw = null;

            for (Order order : currentOrdersList) {
                try {
                    String fileName = makeFileName(order.getOrderDate());
                    File file = new File("tests/"+fileName);
                    boolean isFileCreated = file.createNewFile();
                    fw = new FileWriter(file, true);
                    bw = new BufferedWriter(fw);
                    pw = new PrintWriter(bw);

                    if (isFileCreated) {
                        pw.println(newFileHeader());
                    }

                    pw.println(orderToString(order));
                    pw.flush();

                } catch (IOException e) {
                    throw new FlooringMasterPersistenceException("Whoops... couldn't write to file -___- sry...", e);
                }
            }
            pw.close();
        }
    }

    private String makeFileName(LocalDate ld) {
        String fileName = ld.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        return "test_order_" + fileName + ".txt";
    }

    @Override
    public Order addOrder(Order order) throws FlooringMasterPersistenceException {
        Order newOrder;
        try {
            readFromFile(order.getOrderDate());
        } catch (FlooringMasterPersistenceException e){
            // do nothing but catch if no file
        } finally {
            newOrder = ordersMap.put(order.getOrderNumber(), order);
        }
        writeToFile();
        return newOrder;
    }

    private String orderToString(Order order) {
        return order.getOrderNumber() + DELIMITER + order.getOrderDate() + DELIMITER
                + order.getCustomerName().replace(',', '\uFFFD') + DELIMITER
                + order.getState() + DELIMITER + order.getTaxRate() + DELIMITER
                + order.getProductType() + DELIMITER + order.getAreaSQFT() + DELIMITER
                + order.getMaterialCostPerSQFT() + DELIMITER + order.getLaborCostPerSQFT() + DELIMITER
                + order.getMaterialCostTotal() + DELIMITER + order.getLaborCostTotal() + DELIMITER
                + order.getTaxCostTotal() + DELIMITER + order.getOrderTotal();
    }

    private String newFileHeader() {
        return "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,"
                + "CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,"
                + "LaborCost,Tax,Total";
    }


    private boolean searchForFile(LocalDate ld) {
        File testIfFile = new File(makeFileName(ld));
        boolean ifExist = testIfFile.exists();
        return ifExist;
    }
    
    @Override
    public Order removeOrder(LocalDate ld, int orderNumber) throws FlooringMasterPersistenceException, FlooringMasterOrderNotFoundException {

        readFromFile(ld);
        Order removedOrder = ordersMap.get(orderNumber);
        if (ordersMap.size() > 1) {
            writeToFile();
        }
        return removedOrder;
    }

    @Override
    public Order updateOrder(LocalDate orderDate, Order updatedOrder) throws FlooringMasterPersistenceException {
        readFromFile(orderDate);
        updatedOrder = addOrder(updatedOrder);
        return updatedOrder;
    }

    @Override
    public List<Order> getAllOrders(LocalDate ld) throws FlooringMasterPersistenceException {

        readFromFile(ld);
        ArrayList<Order> allOrderForDate = new ArrayList<>(ordersMap.values());
        writeToFile();
        return allOrderForDate;
    }

    @Override
    public Order getOrder(LocalDate ld, int orderNumber) throws FlooringMasterPersistenceException {

        readFromFile(ld);
        Order order = ordersMap.get(orderNumber);
        addOrder(order);
        return order;
    }

    @Override
    public Boolean setTrainingMode(Boolean setTrainingMode) {
        inTrainingMode = setTrainingMode;
        return inTrainingMode;
    }
    
}
