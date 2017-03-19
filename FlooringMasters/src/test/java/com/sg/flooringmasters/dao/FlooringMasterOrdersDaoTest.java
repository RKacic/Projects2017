/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.dao;

import com.sg.flooringmasters.dto.Order;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class FlooringMasterOrdersDaoTest {

    FlooringMasterOrdersDao testOrderDao;
    Order order;

    public FlooringMasterOrdersDaoTest() {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextTest.xml");
        testOrderDao = ctx.getBean("orderDaoStub", FlooringMasterOrdersDao.class);
        
        order = new Order();
        order.setOrderNumber(1);
        order.setOrderDate(LocalDate.parse("2017-02-27"));
        order.setCustomerName("kacic");
        order.setAreaSQFT(new BigDecimal("200"));
        order.setProductType("Carpet");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setLaborCostPerSQFT(new BigDecimal("2.25"));
        order.setMaterialCostPerSQFT(new BigDecimal("2.10"));
        order.setTaxCostTotal(new BigDecimal("54.38"));
        order.setLaborCostTotal(new BigDecimal("450"));
        order.setMaterialCostTotal(new BigDecimal("420"));
        order.setOrderTotal(new BigDecimal("924.38"));
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        File file1 = new File("tests/test_order_02272017.txt");
        file1.delete();
        File file2 = new File("tests/test_order_02282017.txt");
        file2.delete();
    }

    @After
    public void tearDown() {
        
    }

    /**
     * Test of addOrder method, of class FlooringMasterOrdersDao.
     */
    @Test
    public void testAddOrder() throws Exception {

        assertNull(testOrderDao.addOrder(order));

    }

    /**
     * Test of removeOrder method, of class FlooringMasterOrdersDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        testOrderDao.addOrder(order);
        assertNotNull(testOrderDao.removeOrder(order.getOrderDate(), order.getOrderNumber()));      
        
    }

    /**
     * Test of updateOrder method, of class FlooringMasterOrdersDao.
     */
    @Test
    public void testUpdateOrder() throws Exception {
        
        LocalDate og = order.getOrderDate();
        testOrderDao.addOrder(order);
        Order updatedOrder = order;
        updatedOrder.setCustomerName("Smith");
        updatedOrder.setOrderDate(LocalDate.parse("2017-02-28"));
        testOrderDao.updateOrder(og, updatedOrder);
        Order checkUpdates = testOrderDao.getOrder(order.getOrderDate(), order.getOrderNumber());
        
        assertEquals("Smith", checkUpdates.getCustomerName());
        assertEquals(order.getOrderTotal(), checkUpdates.getOrderTotal());
        assertNotEquals(og, checkUpdates.getOrderDate());
        
    }

    /**
     * Test of getAllOrders method, of class FlooringMasterOrdersDao.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        LocalDate testLD = LocalDate.parse("2017-02-27");
        testOrderDao.addOrder(order);
        
        List<Order> testListOne = testOrderDao.getAllOrders(testLD);
        
        assertEquals(1,testListOne.size());

        Order secondOrder = new Order();
        secondOrder.setOrderNumber(2);
        secondOrder.setOrderDate(testLD);
        secondOrder.setCustomerName("kacic");
        secondOrder.setAreaSQFT(new BigDecimal("200"));
        secondOrder.setProductType("Carpet");
        secondOrder.setState("OH");
        secondOrder.setTaxRate(new BigDecimal("6.25"));
        secondOrder.setLaborCostPerSQFT(new BigDecimal("2.25"));
        secondOrder.setMaterialCostPerSQFT(new BigDecimal("2.10"));
        secondOrder.setTaxCostTotal(new BigDecimal("54.38"));
        secondOrder.setLaborCostTotal(new BigDecimal("450"));
        secondOrder.setMaterialCostTotal(new BigDecimal("420"));
        secondOrder.setOrderTotal(new BigDecimal("924.38"));
        
        testOrderDao.addOrder(secondOrder);
        
        List<Order> testListTwo = testOrderDao.getAllOrders(testLD);
        
        assertEquals(2,testListTwo.size());
    }

    /**
     * Test of getOrder method, of class FlooringMasterOrdersDao.
     */
    @Test
    public void testGetOrder() throws Exception {
        
    LocalDate testLD = LocalDate.parse("2017-02-27");
    
    Order secondOrder = new Order();
        secondOrder.setOrderNumber(2);
        secondOrder.setOrderDate(testLD);
        secondOrder.setCustomerName("kacic, rob");
        secondOrder.setAreaSQFT(new BigDecimal("200"));
        secondOrder.setProductType("Carpet");
        secondOrder.setState("OH");
        secondOrder.setTaxRate(new BigDecimal("6.25"));
        secondOrder.setLaborCostPerSQFT(new BigDecimal("2.25"));
        secondOrder.setMaterialCostPerSQFT(new BigDecimal("2.10"));
        secondOrder.setTaxCostTotal(new BigDecimal("54.38"));
        secondOrder.setLaborCostTotal(new BigDecimal("450"));
        secondOrder.setMaterialCostTotal(new BigDecimal("420"));
        secondOrder.setOrderTotal(new BigDecimal("924.38"));
        
        testOrderDao.addOrder(secondOrder);
    
    Order thirdOrder = new Order();
        thirdOrder.setOrderNumber(3);
        thirdOrder.setOrderDate(testLD);
        thirdOrder.setCustomerName("kacic");
        thirdOrder.setAreaSQFT(new BigDecimal("200"));
        thirdOrder.setProductType("Carpet");
        thirdOrder.setState("OH");
        thirdOrder.setTaxRate(new BigDecimal("6.25"));
        thirdOrder.setLaborCostPerSQFT(new BigDecimal("2.25"));
        thirdOrder.setMaterialCostPerSQFT(new BigDecimal("2.10"));
        thirdOrder.setTaxCostTotal(new BigDecimal("54.38"));
        thirdOrder.setLaborCostTotal(new BigDecimal("450"));
        thirdOrder.setMaterialCostTotal(new BigDecimal("420"));
        thirdOrder.setOrderTotal(new BigDecimal("924.38"));
        
        testOrderDao.addOrder(thirdOrder);
    
    Order orderCheck = testOrderDao.getOrder(testLD, 2);
        assertEquals(2, orderCheck.getOrderNumber());
        assertEquals(testLD, orderCheck.getOrderDate());
    }

}
