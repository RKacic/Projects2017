/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.dao;

import com.sg.flooringmasters.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class FlooringMasterProductsDaoTest {
    
    FlooringMasterProductsDao productsTest;
    
    public FlooringMasterProductsDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextTest.xml");
        productsTest = ctx.getBean("productsDao", FlooringMasterProductsDao.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getProducts method, of class FlooringMasterProductsDao.
     */
    @Test
    public void testGetProducts() throws Exception {
        
       List<Product> producstTestList =  productsTest.getProducts();
       
       assertEquals(4, producstTestList.size());
       
    }
    @Test
    public void testGetProductsEqual() throws Exception {
        
       List<Product> producstTestList =  productsTest.getProducts();
       BigDecimal materialCost = new BigDecimal("2.25");
       BigDecimal laborCost = new BigDecimal("2.10");
       
       assertEquals("Carpet", producstTestList.get(0).getProductType());
       assertEquals(laborCost, producstTestList.get(0).getLaborCostPerSQFT());
       assertEquals(materialCost, producstTestList.get(0).getMaterialCostPerSQFT());
       
    }
    
}
