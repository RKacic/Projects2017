/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author apprentice
 */
public class Order {
    
    int orderNumber;
    LocalDate orderDate;
    String customerName;
    String state;
    String productType;
    BigDecimal taxRate;
    BigDecimal materialCostPerSQFT;
    BigDecimal laborCostPerSQFT;
    BigDecimal areaSQFT;
    BigDecimal materialCostTotal;
    BigDecimal laborCostTotal;
    BigDecimal taxCostTotal;
    BigDecimal orderTotal;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getMaterialCostPerSQFT() {
        return materialCostPerSQFT;
    }

    public void setMaterialCostPerSQFT(BigDecimal materialCostPerSQFT) {
        this.materialCostPerSQFT = materialCostPerSQFT;
    }

    public BigDecimal getLaborCostPerSQFT() {
        return laborCostPerSQFT;
    }

    public void setLaborCostPerSQFT(BigDecimal laborCostPerSQFT) {
        this.laborCostPerSQFT = laborCostPerSQFT;
    }

    public BigDecimal getAreaSQFT() {
        return areaSQFT;
    }

    public void setAreaSQFT(BigDecimal areaSQFT) {
        this.areaSQFT = areaSQFT;
    }

    public BigDecimal getMaterialCostTotal() {
        return materialCostTotal;
    }

    public void setMaterialCostTotal(BigDecimal materialCostTotal) {
        this.materialCostTotal = materialCostTotal;
    }

    public BigDecimal getLaborCostTotal() {
        return laborCostTotal;
    }

    public void setLaborCostTotal(BigDecimal laborCostTotal) {
        this.laborCostTotal = laborCostTotal;
    }

    public BigDecimal getTaxCostTotal() {
        return taxCostTotal;
    }

    public void setTaxCostTotal(BigDecimal taxCostTotal) {
        this.taxCostTotal = taxCostTotal;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

}
