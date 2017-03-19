/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.dao;

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
public class FlooringMasterOrderNumberDaoTest {
    
    FlooringMasterOrderNumberDao orderNumberTest;
    
    public FlooringMasterOrderNumberDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextTest.xml");
        orderNumberTest = ctx.getBean("orderNumberDaoStub", FlooringMasterOrderNumberDao.class);
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
     * Test of getNewOrderNumber method, of class FlooringMasterOrderNumberDao.
     */
    @Test
    public void testGetNewOrderNumber() throws Exception {
        int test1 = orderNumberTest.getNewOrderNumber();
        int test2 = orderNumberTest.getNewOrderNumber();
        
        assertNotEquals(test1, test2);
    }
    @Test
    public void testGetNewOrderNumberMulitple() throws Exception {
        int test1 = orderNumberTest.getNewOrderNumber();
        int test2 = orderNumberTest.getNewOrderNumber();
        int test3 = orderNumberTest.getNewOrderNumber();
        assertNotEquals(test3, test2);
    }
    @Test
    public void testGetNewOrderNumberIncrementByOne() throws Exception {
        int test1 = orderNumberTest.getNewOrderNumber();
        int test2 = orderNumberTest.getNewOrderNumber();
        
        assertEquals(1, test2 - test1);
    }

   
    
}
