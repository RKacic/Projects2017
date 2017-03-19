/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters;

import com.sg.flooringmasters.controller.FlooringMasterController;
import com.sg.flooringmasters.dao.FlooringMasterPersistenceException;
import com.sg.flooringmasters.service.FlooringMasterInvalidDataException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class App {
    public static void main(String[] args) throws 
            FlooringMasterInvalidDataException, 
            FlooringMasterPersistenceException {
  
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasterController controller = ctx.getBean("controller", FlooringMasterController.class);
        
        controller.run();

    } 
}
