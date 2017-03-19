/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.controller;

import com.sg.flooringmasters.dao.FlooringMasterOrderNotFoundException;
import com.sg.flooringmasters.dao.FlooringMasterPersistenceException;
import com.sg.flooringmasters.dto.Order;
import com.sg.flooringmasters.service.FlooringMasterInvalidDataException;
import com.sg.flooringmasters.service.FlooringMasterService;
import com.sg.flooringmasters.ui.FlooringMasterView;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringMasterController {

    FlooringMasterService service;
    FlooringMasterView view;
    private boolean keepRunnning;
    private boolean trainingMode;

    public FlooringMasterController(FlooringMasterView view, FlooringMasterService service) {
        this.service = service;
        this.view = view;
        trainingMode = false;
    }

    public void run() throws  FlooringMasterInvalidDataException, 
            FlooringMasterPersistenceException, 
            FlooringMasterInvalidDataException {
        keepRunnning = true;
        try {
            while (keepRunnning) {
                displayCurrentMode();
                int option = getOptionChoice();
                switch (option) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addNewOrder();
                        break;
                    case 3:
                        updateOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        saveNothing();
                        break;
                    case 6:
                        quit();
                        break;
                    case 7:
                        switchMode();
                        break;
                    default:
                        unknownCommand();
                }
            }
        } catch (FlooringMasterInvalidDataException | 
                FlooringMasterPersistenceException | 
                FlooringMasterOrderNotFoundException |
                InterruptedException e) {
            view.displayErrorMessage(e.getMessage());
            run();
        }
    }
    //todo dashes not lashes
    

    private void unknownCommand() {
        view.displayUnknownCommand();
    }

    private int getOptionChoice() {
        return view.getOptionChoiceMainMenu();
    }

    private void displayOrders() throws FlooringMasterOrderNotFoundException,
            FlooringMasterPersistenceException {
        LocalDate dateToSearch = view.getDate("Please enter the date to search for.");
        List<Order> results = service.getAllOrders(dateToSearch);
        view.displayResults(results);
        view.pauseForEnter();
    }

    private void addNewOrder() throws FlooringMasterInvalidDataException,
            FlooringMasterPersistenceException {
        //todo correct invalid input statement on print to console
        Order newOrder = view.addNewOrder(service.getProducts(), service.getTaxRates());
        newOrder = service.calculateAllCosts(newOrder);
        newOrder.setOrderNumber(service.getNewOrderNumber());
        boolean commitOrder = view.displayOrderToCommit(newOrder, "would you like"
                + " to add this order?");
        if (commitOrder == true) {
            service.addOrder(newOrder);
        }
    }

    private void removeOrder() throws FlooringMasterOrderNotFoundException, 
            FlooringMasterPersistenceException, 
            FlooringMasterInvalidDataException {
        LocalDate ld = view.getDate("Please enter the date of the order you'd"
                + " like to commit to delete");
        int orderNumber = view.getOrderNumber();
        Order orderToDelete = service.removeOrder(ld, orderNumber);
        boolean commitDeleteOrder = view.displayOrderToCommit(orderToDelete,
                "would you like to delete this order?");
        if (commitDeleteOrder == false) {
            service.addOrder(orderToDelete);
        }
    }

    private void updateOrder() throws FlooringMasterOrderNotFoundException,
            FlooringMasterPersistenceException,
            FlooringMasterInvalidDataException{
        LocalDate originalDate = view.getDate("Please enter the date of the order"
                + " you'd like to edit.");
        int orderNumber = view.getOrderNumber();
        Order orderToEdit = service.getOrder(originalDate, orderNumber);
        Order updatedOrder = view.editOrder(orderToEdit, service.getProducts(), 
                service.getTaxRates());
        updatedOrder = service.calculateAllCosts(updatedOrder);
        boolean commitChanges = view.displayOrderToCommit(updatedOrder, "would"
                + " you like to save the changes to this order? ");
        if (commitChanges == true) {
            service.updateOrder(originalDate, updatedOrder);
        }
    }

    private void saveNothing() {
        view.displayAllContentSaved();
    }

    private void switchMode() throws FlooringMasterInvalidDataException, InterruptedException {
        view.getPassCode();
        if(trainingMode){
            trainingMode = false;
        } else {
            trainingMode = true;
            view.displayEnteringTrainingMode();
        }
        service.setMode(trainingMode);
    }

    private void quit() throws InterruptedException{
        view.displayExit();
        keepRunnning = false;
    }

    private void displayCurrentMode() {
        if(trainingMode == true ){
            view.displayInTrainingMode();
        } else {
            view.displayInProMode();
        }
    }

}
