/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.dao;

import com.sg.flooringmasters.dto.TaxRate;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class FlooringMasterTaxRatesDaoTest {
    
    FlooringMasterTaxRatesDao taxRateTest;
    
    public FlooringMasterTaxRatesDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextTest.xml");
        taxRateTest = ctx.getBean("taxRatesDao", FlooringMasterTaxRatesDao.class);
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
     * Test of getTaxRates method, of class FlooringMasterTaxRatesDao.
     */
    @Test
    public void testGetTaxRates() throws Exception {
        
        List<TaxRate> testTaxRatesList = taxRateTest.getTaxRates();
        
        assertEquals(4, testTaxRatesList.size());
        
    }
    @Test
    public void testGetTaxRateInfo() throws Exception {
        
        List<TaxRate> testTaxRatesList = taxRateTest.getTaxRates();
        BigDecimal testRate = new BigDecimal("6.25");
        assertEquals("OH", testTaxRatesList.get(0).getState());
        assertEquals(testRate, testTaxRatesList.get(0).getTaxRate());
    }
    
}
