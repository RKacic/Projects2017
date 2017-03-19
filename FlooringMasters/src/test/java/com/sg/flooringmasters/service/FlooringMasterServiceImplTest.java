/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.service;

import com.sg.flooringmasters.dao.FlooringMasterOrderNumberDao;
import com.sg.flooringmasters.dao.FlooringMasterOrdersDao;
import com.sg.flooringmasters.dao.FlooringMasterProductsDao;
import com.sg.flooringmasters.dao.FlooringMasterTaxRatesDao;
import com.sg.flooringmasters.dto.Order;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class FlooringMasterServiceImplTest {

    FlooringMasterService serviceTest;
    FlooringMasterOrderNumberDao orderNumberDaoTest;
    FlooringMasterOrdersDao ordersDaoTest;
    FlooringMasterProductsDao productsDaoTest;
    FlooringMasterTaxRatesDao taxRatesDaoTest;
    Order orderTest1;
    Order orderTest2;

    public FlooringMasterServiceImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextTest.xml");

        serviceTest = ctx.getBean("service", FlooringMasterService.class);
        orderNumberDaoTest = ctx.getBean("orderNumberDaoStub", FlooringMasterOrderNumberDao.class);
        ordersDaoTest = ctx.getBean("orderDaoStub", FlooringMasterOrdersDao.class);
        productsDaoTest = ctx.getBean("productsDao", FlooringMasterProductsDao.class);
        taxRatesDaoTest = ctx.getBean("taxRatesDao", FlooringMasterTaxRatesDao.class);

        orderTest1 = new Order();
        orderTest1.setOrderNumber(1);
        orderTest1.setOrderDate(LocalDate.parse("2017-02-27"));
        orderTest1.setCustomerName("kacic");
        orderTest1.setAreaSQFT(new BigDecimal("100"));
        orderTest1.setProductType("Carpet");
        orderTest1.setState("OH");
        orderTest1.setTaxRate(new BigDecimal("2.00"));
        orderTest1.setLaborCostPerSQFT(new BigDecimal("1.00"));
        orderTest1.setMaterialCostPerSQFT(new BigDecimal("2.00"));
        orderTest1.setTaxCostTotal(new BigDecimal("54.38"));
        orderTest1.setLaborCostTotal(new BigDecimal("450"));
        orderTest1.setMaterialCostTotal(new BigDecimal("420"));
        orderTest1.setOrderTotal(new BigDecimal("924.38"));

        orderTest2 = new Order();
        orderTest2.setOrderNumber(2);
        orderTest2.setOrderDate(LocalDate.parse("2017-02-28"));
        orderTest2.setCustomerName("smith");
        orderTest2.setAreaSQFT(new BigDecimal("200"));
        orderTest2.setProductType("Carpet");
        orderTest2.setState("OH");
        orderTest2.setTaxRate(new BigDecimal("6.25"));
        orderTest2.setLaborCostPerSQFT(new BigDecimal("1"));
        orderTest2.setMaterialCostPerSQFT(new BigDecimal("1"));
        orderTest2.setTaxCostTotal(new BigDecimal("0"));
        orderTest2.setLaborCostTotal(new BigDecimal("0"));
        orderTest2.setMaterialCostTotal(new BigDecimal("0"));
        orderTest2.setOrderTotal(new BigDecimal("0"));
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        File file1 = new File("TEST_ORDERS_02272017.txt");
        File file2 = new File("TEST_ORDERS_02282017.txt");
        File file3 = new File("TEST_ORDERS_02262017.txt");
        file1.delete();
        file2.delete();
        file3.delete();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getProducts method, of class FlooringMasterServiceImpl.
     */
    @Test
    public void testGetProducts() throws Exception {

        assertEquals(4, serviceTest.getProducts().size());

    }

    /**
     * Test of getTaxRates method, of class FlooringMasterServiceImpl.
     */
    @Test
    public void testGetTaxRates() throws Exception {

        assertEquals(4, serviceTest.getTaxRates().size());

    }

    /**
     * Test of calculateAllCosts method, of class FlooringMasterServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateAllCosts() throws Exception {

        Order orderTest = new Order();
        orderTest.setOrderNumber(300);
        orderTest.setOrderDate(LocalDate.parse("2017-02-28"));
        orderTest.setCustomerName("smith");
        orderTest.setAreaSQFT(new BigDecimal("100"));
        orderTest.setProductType("Carpet");
        orderTest.setState("OH");
        orderTest.setTaxRate(new BigDecimal("2.00"));
        orderTest.setLaborCostPerSQFT(new BigDecimal("1.00"));
        orderTest.setMaterialCostPerSQFT(new BigDecimal("1"));
        orderTest.setTaxCostTotal(new BigDecimal("0"));
        orderTest.setLaborCostTotal(new BigDecimal("0"));
        orderTest.setMaterialCostTotal(new BigDecimal("0"));
        orderTest.setOrderTotal(new BigDecimal("0"));

        BigDecimal checkMatTotal = new BigDecimal("100.00");
        BigDecimal checkLaborTotal = new BigDecimal("100.00");
        BigDecimal checkTaxTotal = new BigDecimal("4.00");
        BigDecimal checkOrderTotal = new BigDecimal("204.00");

        orderTest = serviceTest.calculateAllCosts(orderTest);

        assertEquals(checkMatTotal, orderTest.getMaterialCostTotal());
        assertEquals(checkLaborTotal, orderTest.getLaborCostTotal());
        assertEquals(checkTaxTotal, orderTest.getTaxCostTotal());
        assertEquals(checkOrderTotal, orderTest.getOrderTotal());

    }

    /**
     * Test of addOrder method, of class FlooringMasterServiceImpl.
     */
    @Test
    public void testAddOrder() throws Exception {

        assertNull(serviceTest.addOrder(orderTest1));

    }

    /**
     * Test of removeOrder method, of class FlooringMasterServiceImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        assertNull(serviceTest.addOrder(orderTest1));
        assertNotNull(serviceTest.removeOrder(orderTest1.getOrderDate(), orderTest1.getOrderNumber()));
    }

    /**
     * Test of getAllOrders method, of class FlooringMasterServiceImpl.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        Order orderTest = new Order();
        orderTest.setOrderNumber(300);
        orderTest.setOrderDate(LocalDate.parse("2017-02-27"));
        orderTest.setCustomerName("smith");
        orderTest.setAreaSQFT(new BigDecimal("100"));
        orderTest.setProductType("Carpet");
        orderTest.setState("OH");
        orderTest.setTaxRate(new BigDecimal("2.00"));
        orderTest.setLaborCostPerSQFT(new BigDecimal("1.00"));
        orderTest.setMaterialCostPerSQFT(new BigDecimal("1"));
        orderTest.setTaxCostTotal(new BigDecimal("0"));
        orderTest.setLaborCostTotal(new BigDecimal("0"));
        orderTest.setMaterialCostTotal(new BigDecimal("0"));
        orderTest.setOrderTotal(new BigDecimal("0"));

        serviceTest.addOrder(orderTest1);
        serviceTest.addOrder(orderTest);

        assertEquals(2, serviceTest.getAllOrders(LocalDate.parse("2017-02-27")).size());
    }

    /**
     * Test of getOrder method, of class FlooringMasterServiceImpl.
     */
    @Test
    public void testGetOrder() throws Exception {
        Order orderTest = new Order();
        orderTest.setOrderNumber(300);
        orderTest.setOrderDate(LocalDate.parse("2017-02-27"));
        orderTest.setCustomerName("smith");
        orderTest.setAreaSQFT(new BigDecimal("100"));
        orderTest.setProductType("Carpet");
        orderTest.setState("OH");
        orderTest.setTaxRate(new BigDecimal("2.00"));
        orderTest.setLaborCostPerSQFT(new BigDecimal("1.00"));
        orderTest.setMaterialCostPerSQFT(new BigDecimal("1"));
        orderTest.setTaxCostTotal(new BigDecimal("0"));
        orderTest.setLaborCostTotal(new BigDecimal("0"));
        orderTest.setMaterialCostTotal(new BigDecimal("0"));
        orderTest.setOrderTotal(new BigDecimal("0"));

        serviceTest.addOrder(orderTest1);
        serviceTest.addOrder(orderTest);

        Order returnedOrder = serviceTest.getOrder(LocalDate.parse("2017-02-27"), 300);

        assertEquals(300, returnedOrder.getOrderNumber());
    }

    /**
     * Test of getNewOrderNumber method, of class FlooringMasterServiceImpl.
     */
    @Test
    public void testGetNewOrderNumber() throws Exception {

        Order order1 = new Order();
        order1.setOrderNumber(0);
        order1.setOrderDate(LocalDate.parse("2017-02-27"));
        order1.setCustomerName("smith");
        order1.setAreaSQFT(new BigDecimal("100"));
        order1.setProductType("Carpet");
        order1.setState("OH");
        order1.setTaxRate(new BigDecimal("2.00"));
        order1.setLaborCostPerSQFT(new BigDecimal("1.00"));
        order1.setMaterialCostPerSQFT(new BigDecimal("1"));
        order1.setTaxCostTotal(new BigDecimal("0"));
        order1.setLaborCostTotal(new BigDecimal("0"));
        order1.setMaterialCostTotal(new BigDecimal("0"));
        order1.setOrderTotal(new BigDecimal("0"));

        Order order2 = new Order();
        order2.setOrderNumber(0);
        order2.setOrderDate(LocalDate.parse("2017-02-27"));
        order2.setCustomerName("pink");
        order2.setAreaSQFT(new BigDecimal("100"));
        order2.setProductType("Carpet");
        order2.setState("OH");
        order2.setTaxRate(new BigDecimal("2.00"));
        order2.setLaborCostPerSQFT(new BigDecimal("1.00"));
        order2.setMaterialCostPerSQFT(new BigDecimal("1"));
        order2.setTaxCostTotal(new BigDecimal("0"));
        order2.setLaborCostTotal(new BigDecimal("0"));
        order2.setMaterialCostTotal(new BigDecimal("0"));
        order2.setOrderTotal(new BigDecimal("0"));

        Order order3 = new Order();
        order3.setOrderNumber(0);
        order3.setOrderDate(LocalDate.parse("2017-02-27"));
        order3.setCustomerName("orange");
        order3.setAreaSQFT(new BigDecimal("100"));
        order3.setProductType("Carpet");
        order3.setState("OH");
        order3.setTaxRate(new BigDecimal("2.00"));
        order3.setLaborCostPerSQFT(new BigDecimal("1.00"));
        order3.setMaterialCostPerSQFT(new BigDecimal("1"));
        order3.setTaxCostTotal(new BigDecimal("0"));
        order3.setLaborCostTotal(new BigDecimal("0"));
        order3.setMaterialCostTotal(new BigDecimal("0"));
        order3.setOrderTotal(new BigDecimal("0"));

        Order order4 = new Order();
        order4.setOrderNumber(0);
        order4.setOrderDate(LocalDate.parse("2017-02-27"));
        order4.setCustomerName("jeffery");
        order4.setAreaSQFT(new BigDecimal("100"));
        order4.setProductType("Carpet");
        order4.setState("OH");
        order4.setTaxRate(new BigDecimal("2.00"));
        order4.setLaborCostPerSQFT(new BigDecimal("1.00"));
        order4.setMaterialCostPerSQFT(new BigDecimal("1"));
        order4.setTaxCostTotal(new BigDecimal("0"));
        order4.setLaborCostTotal(new BigDecimal("0"));
        order4.setMaterialCostTotal(new BigDecimal("0"));
        order4.setOrderTotal(new BigDecimal("0"));

        order1.setOrderNumber(serviceTest.getNewOrderNumber());
        order2.setOrderNumber(serviceTest.getNewOrderNumber());
        order3.setOrderNumber(serviceTest.getNewOrderNumber());
        order4.setOrderNumber(serviceTest.getNewOrderNumber());

        assertThat(order1.getOrderNumber(), not(0));
        assertThat(order2.getOrderNumber(), not(order1.getOrderNumber()));
        assertThat(order3.getOrderNumber(), not(order2.getOrderNumber()));
        assertThat(order4.getOrderNumber(), not(order3.getOrderNumber()));

    }

    /**
     * Test of updateOrder method, of class FlooringMasterServiceImpl.
     */
    @Test
    public void testUpdateOrder() throws Exception {

        Order order = new Order();
        order.setOrderNumber(99);
        order.setOrderDate(LocalDate.parse("2017-02-27"));
        order.setCustomerName("jeffery");
        order.setAreaSQFT(new BigDecimal("100"));
        order.setProductType("Carpet");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("2.00"));
        order.setLaborCostPerSQFT(new BigDecimal("1.00"));
        order.setMaterialCostPerSQFT(new BigDecimal("1"));
        order.setTaxCostTotal(new BigDecimal("0"));
        order.setLaborCostTotal(new BigDecimal("0"));
        order.setMaterialCostTotal(new BigDecimal("0"));
        order.setOrderTotal(new BigDecimal("0"));

        LocalDate originalDate = order.getOrderDate();

        serviceTest.addOrder(order);

        order.setOrderDate(LocalDate.parse("2017-02-26"));

        serviceTest.updateOrder(originalDate, order);

        Order updatedOrder = serviceTest.getOrder(LocalDate.parse("2017-02-26"), 99);

        assertNotEquals(originalDate, updatedOrder.getOrderDate());

    }

    /**
     * Test of setMode method, of class FlooringMasterServiceImpl.
     */
    @Test
    public void testSetMode() throws Exception {
        
        assertEquals(true, serviceTest.setMode(true));
        
        assertEquals(false, serviceTest.setMode(false));
        
    }

}
