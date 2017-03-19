/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.service;

import com.sg.flooringmasters.dao.FlooringMasterOrderNotFoundException;
import com.sg.flooringmasters.dao.FlooringMasterOrderNumberDao;
import com.sg.flooringmasters.dao.FlooringMasterOrdersDao;
import com.sg.flooringmasters.dao.FlooringMasterPersistenceException;
import com.sg.flooringmasters.dao.FlooringMasterProductsDao;
import com.sg.flooringmasters.dao.FlooringMasterTaxRatesDao;
import com.sg.flooringmasters.dto.Order;
import com.sg.flooringmasters.dto.Product;
import com.sg.flooringmasters.dto.TaxRate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;


/**
 *
 * @author apprentice
 */
public class FlooringMasterServiceImpl implements FlooringMasterService{
    FlooringMasterOrdersDao ordersDao;
    FlooringMasterOrderNumberDao orderNumberDao;
    FlooringMasterProductsDao productsDao;
    FlooringMasterTaxRatesDao taxRatesDao;
    
    public FlooringMasterServiceImpl(FlooringMasterOrdersDao ordersDao, FlooringMasterOrderNumberDao orderNumberDao,
            FlooringMasterProductsDao productsDao, FlooringMasterTaxRatesDao taxRatesDao){
        
        this.ordersDao = ordersDao;
        this.orderNumberDao = orderNumberDao;
        this.productsDao = productsDao;
        this.taxRatesDao = taxRatesDao;
        
    }

    @Override
    public List<Order> getAllOrders(LocalDate ld) throws FlooringMasterOrderNotFoundException,
            FlooringMasterPersistenceException {
        return ordersDao.getAllOrders(ld);
    }

    @Override
    public Order getOrder(LocalDate ld, int orderNumber) throws FlooringMasterPersistenceException,FlooringMasterOrderNotFoundException {
        Order returnedOrder = ordersDao.getOrder(ld, orderNumber);
        return returnedOrder;
    }

    @Override
    public List<Product> getProducts() throws FlooringMasterInvalidDataException, FlooringMasterPersistenceException {
        return productsDao.getProducts();
    }

    @Override
    public List<TaxRate> getTaxRates() throws FlooringMasterInvalidDataException, FlooringMasterPersistenceException {
        return taxRatesDao.getTaxRates();
    }

    @Override
    public Integer getNewOrderNumber() throws FlooringMasterInvalidDataException, FlooringMasterPersistenceException {
        return orderNumberDao.getNewOrderNumber();
    }

    @Override
    public Order addOrder(Order order) throws FlooringMasterPersistenceException, FlooringMasterInvalidDataException {
        if(!checkForNull(order)){
        Order newOrder = ordersDao.addOrder(order);
        return newOrder;
        } else {
            throw new FlooringMasterInvalidDataException("This order contains null fields"
                    + " and cannot be persisted.");
        }
        //todo add audit logs for null catches
    }

    @Override
    public Order updateOrder(LocalDate ld, Order orderToUpdate) throws FlooringMasterPersistenceException, FlooringMasterInvalidDataException {
        if(!checkForNull(orderToUpdate)){
        Order updatedOrder = ordersDao.updateOrder(ld, orderToUpdate);
        return updatedOrder;
        } else {
            throw new FlooringMasterInvalidDataException("This order contains null fields"
                    + " and cannot be persisted.");
        }
        //todo add audit logs for null catches
    }

    @Override
    public Order removeOrder(LocalDate ld, int orderNumber) throws FlooringMasterPersistenceException, FlooringMasterOrderNotFoundException {
        Order removedOrder = ordersDao.removeOrder(ld, orderNumber);
        return removedOrder;
    }

    @Override
    public Boolean setMode(Boolean setTrainingMode){
        boolean inTrainingMode = ordersDao.setTrainingMode(setTrainingMode);
        return inTrainingMode;
    }

    @Override
    public Order calculateAllCosts(Order order) throws FlooringMasterInvalidDataException {
        order.setMaterialCostTotal(calcTotalMaterialCost(order));
        order.setLaborCostTotal(calcTotalLaborCost(order));
        order.setTaxCostTotal(calcTotalTaxCost(order));
        order.setOrderTotal(calcTotalOrderCost(order));
        
        return order;
    }
    
    private BigDecimal calcTotalMaterialCost(Order order) {
        BigDecimal matCostPerSQFT = order.getMaterialCostPerSQFT();
        BigDecimal area = order.getAreaSQFT();
        BigDecimal totalCost = matCostPerSQFT.multiply(area);
        
        return totalCost.setScale(2,RoundingMode.HALF_UP);
    }

    private BigDecimal calcTotalLaborCost(Order order) {
        BigDecimal laborCostPerSQFT = order.getLaborCostPerSQFT();
        BigDecimal area = order.getAreaSQFT();
        BigDecimal totalCost = laborCostPerSQFT.multiply(area);
        
        return totalCost.setScale(2,RoundingMode.HALF_UP);
    }

    private BigDecimal calcTotalTaxCost(Order order) {
        BigDecimal hundred = new BigDecimal("100");
        
        BigDecimal laborCostTotal = order.getLaborCostTotal();
        BigDecimal materialCostTotal = order.getMaterialCostTotal();
        BigDecimal taxRate = order.getTaxRate().divide(hundred);
        BigDecimal totalCost = laborCostTotal.add(materialCostTotal).multiply(taxRate);
        return totalCost.setScale(2,RoundingMode.HALF_UP);
    }

    private BigDecimal calcTotalOrderCost(Order order) {
        BigDecimal totalCost = order.getLaborCostTotal()
                .add(order.getMaterialCostTotal())
                .add(order.getTaxCostTotal());
        return totalCost.setScale(2,RoundingMode.HALF_UP);
    }
    private boolean checkForNull(Order order) throws IllegalArgumentException {
        if (order.getCustomerName() == null ||
                order.getOrderDate() == null ||
                order.getProductType() == null ||
                order.getAreaSQFT() == null ||
                order.getState() == null ||
                order.getTaxRate()== null ||
                order.getTaxCostTotal()== null ||
                order.getLaborCostPerSQFT()== null ||
                order.getLaborCostTotal()== null ||
                order.getMaterialCostPerSQFT()== null ||
                order.getMaterialCostTotal()== null ||
                order.getOrderTotal()== null){
            return true;
        } else {
            return false;
        }
    }
}
