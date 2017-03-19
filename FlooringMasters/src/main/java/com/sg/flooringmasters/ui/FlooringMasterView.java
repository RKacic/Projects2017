/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.ui;

import com.sg.flooringmasters.dto.Order;
import com.sg.flooringmasters.dto.Product;
import com.sg.flooringmasters.dto.TaxRate;
import com.sg.flooringmasters.service.FlooringMasterInvalidDataException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringMasterView {

    UserIO io;
    BigDecimal minimum = new BigDecimal("1");

    public FlooringMasterView(UserIOConsoleImpl io) {
        this.io = io;
    }

    public int getOptionChoiceMainMenu() {
        io.print("1. Display Orders\n");
        io.print("2. Add new Order\n");
        io.print("3. Update an Order\n");
        io.print("4. Remove an Order\n");
        io.print("5. Save Work\n");
        io.print("6. EXIT\n");
        io.print("7. Switch to Training Mode\n");
        return io.readInt("\nPlease pick from the above choices", 1, 7);
    }

    public Order addNewOrder(List<Product> products, List<TaxRate> taxRates) {
        Order newOrder = new Order();
        newOrder.setCustomerName(getCustomerName("Please enter the customers name."));
        newOrder.setOrderDate(io.readLocalDate("Please enter customers requested order date."));
        newOrder.setAreaSQFT(io.getBigDecimal("Please enter the total area of the order site.", minimum));
        Product productChoice = getProductChoice(products);
        TaxRate customerState = getTaxRateChoice(taxRates);
        newOrder.setProductType(productChoice.getProductType());
        newOrder.setLaborCostPerSQFT(productChoice.getLaborCostPerSQFT());
        newOrder.setMaterialCostPerSQFT(productChoice.getMaterialCostPerSQFT());
        newOrder.setState(customerState.getState());
        newOrder.setTaxRate(customerState.getTaxRate());

        return newOrder;
    }

    private String getCustomerName(String prompt) {
        boolean isValid = false;
        String name = "";
        while (!isValid) {
            name = io.readString(prompt);
            if (name.length() <= 1) {
                io.print("Please enter atleast 2 characters for name field.");
            } else {
                isValid = true;
            }
        }
        return name;
    }

    private Product getProductChoice(List<Product> products) {
        int i = 1;
        for (Product product : products) {
            io.print(i + ". " + product.getProductType());
            i++;
        }
        int choice = io.readInt("Please select one of the above products", 1, products.size());
        return products.get(choice - 1);
    }

    private TaxRate getTaxRateChoice(List<TaxRate> taxRates) {
        int i = 1;
        for (TaxRate taxRate : taxRates) {
            io.print(i + ". " + taxRate.getState());
            i++;
        }
        int choice = io.readInt("Please select the customers state", 1, taxRates.size());
        return taxRates.get(choice - 1);
    }

    public boolean displayOrderToCommit(Order newOrder, String prompt) {

        io.print("=====================================");
        io.print("============    Order   =============");
        io.print("=====================================");
        io.print("Customer name: " + newOrder.getCustomerName());
        io.print("Customer State: " + newOrder.getState());
        io.print("Customer order number: " + newOrder.getOrderNumber());
        io.print("Customer order date: " + newOrder.getOrderDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        io.print("Customer product type: " + newOrder.getProductType());
        io.print("Customer area of order: " + newOrder.getAreaSQFT());
        io.print("Customer material cost per sqft: " + newOrder.getMaterialCostPerSQFT());
        io.print("Customer material total: " + newOrder.getMaterialCostTotal());
        io.print("Customer labor cost per sqft: " + newOrder.getLaborCostPerSQFT());
        io.print("Customer labor total: " + newOrder.getLaborCostTotal());
        io.print("Customer tax total: " + newOrder.getTaxCostTotal());
        io.print("Customer order total: " + newOrder.getOrderTotal());
        io.print("=====================================");
        io.print("=====================================");

        return io.getYesOrNo(prompt);

    }

    public void displayOrder(Order order) {

        io.print("=====================================");
        io.print("============    Order   =============");
        io.print("=====================================");
        io.print("Customer name: " + order.getCustomerName());
        io.print("Customer State: " + order.getState());
        io.print("Customer order number: " + order.getOrderNumber());
        io.print("Customer order date: " + order.getOrderDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        io.print("Customer product type: " + order.getProductType());
        io.print("Customer area of order: " + order.getAreaSQFT());
        io.print("Customer material cost per sqft: " + order.getMaterialCostPerSQFT());
        io.print("Customer material total: " + order.getMaterialCostTotal());
        io.print("Customer labor cost per sqft: " + order.getLaborCostPerSQFT());
        io.print("Customer labor total: " + order.getLaborCostTotal());
        io.print("Customer tax total: " + order.getTaxCostTotal());
        io.print("Customer order total: " + order.getOrderTotal());
        io.print("=====================================");
        io.print("=====================================");

    }

    public void pauseForEnter() {
        io.readString("Press enter to continue...");
    }

    public void displayUnknownCommand() {
        io.print("~~~~ UNKNOWN COMMAND!!! ~~~~");
    }

    public LocalDate getDate(String prompt) {
        return io.readLocalDate(prompt);
    }

    public void displayResults(List<Order> results) {
        int i = 1;
        if (results.size() > 0) {
            for (Order order : results) {
                io.print(i + ". Name: " + order.getCustomerName() + " | order number: " + order.getOrderNumber());
                i++;
            }

            int choice = io.readInt("Please select one of the above orders to display", 1, results.size()) - 1;
            displayOrder(results.get(choice));

        } else {
            io.print("Sorry, there are no orders for this date.");
        }
    }

    public void displayAllContentSaved() {
        io.print("ALL CONTENT SAVED...lol");
    }

    public int getOrderNumber() {
        return io.readInt("Please enter an order number", 1, 999999999);
    }

    public void displayErrorMessage(String message) {
        io.print(message);
    }

    public Order editOrder(Order orderToEdit, List<Product> products, List<TaxRate> taxRates) {

        orderToEdit.setCustomerName(getNameChange(orderToEdit.getCustomerName()));
        orderToEdit.setOrderDate(getDateChange(orderToEdit.getOrderDate()));
        orderToEdit.setAreaSQFT(getAreaChage(orderToEdit.getAreaSQFT()));
        Product updatedProduct = getProductChange(orderToEdit, products);
        TaxRate updatedTaxRate = getTaxRateChange(orderToEdit, taxRates);
        orderToEdit.setProductType(updatedProduct.getProductType());
        orderToEdit.setMaterialCostPerSQFT(updatedProduct.getMaterialCostPerSQFT());
        orderToEdit.setLaborCostPerSQFT(updatedProduct.getLaborCostPerSQFT());
        orderToEdit.setState(updatedTaxRate.getState());
        orderToEdit.setTaxRate(updatedTaxRate.getTaxRate());
        orderToEdit.setState(updatedTaxRate.getState());

        return orderToEdit;
    }

    private String getNameChange(String customerName) {
        String nameChange = io.readString("Customers name: " + customerName + "\n"
                + "press enter to keep or make changes and press enter.");

        if (nameChange.equals("")) {
            return customerName;
        } else {
            return nameChange;
        }

    }

    private LocalDate getDateChange(LocalDate orderDate) {
        boolean isValid = false;
        LocalDate newOrderDate = null;
        String newDate = io.readString("Customers order date: " + orderDate + "\n"
                + "\npress enter to keep current date or\n"
                + "enter new date in MM/dd/yyyy format.");
        if (newDate.equals("")) {
            return orderDate;
        } else {
            while (!isValid) {
                try {
                    newOrderDate = LocalDate.parse(newDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    isValid = true;

                } catch (Exception e) {
                    System.out.println("\nWhoops please use proper formatting...\n");
                }
            }
            return newOrderDate;
        }
    }

    private Product getProductChange(Order orderToEdit, List<Product> products) {

        io.print("\nCustomers product type: " + orderToEdit.getProductType() + "\n");
        Product newProduct = getProductChoice(products);
        return newProduct;

    }

    private TaxRate getTaxRateChange(Order orderToEdit, List<TaxRate> taxRates) {

        io.print("\nCustomers state: " + orderToEdit.getState() + "\n");
        TaxRate newTaxRate = getTaxRateChoice(taxRates);
        return newTaxRate;

    }

    private BigDecimal getAreaChage(BigDecimal areaSQFT) {
        boolean isValid = false;
        BigDecimal newAreaSQFT = new BigDecimal("0");
        BigDecimal min = new BigDecimal("1");
        String areaToCheck = io.readString("Customers area: " + areaSQFT + "\n"
                + "press enter to keep or make changes and press enter.");
        while (!isValid) {
            if (areaToCheck.isEmpty()) {
                newAreaSQFT = areaSQFT;
                isValid = true;
            } else if (Integer.parseInt(areaToCheck) >= 1) {
                BigDecimal isGoodArea = new BigDecimal(areaToCheck);
                newAreaSQFT = isGoodArea;
                isValid = true;
            }
        }
        return newAreaSQFT;
    }

    public void getPassCode() throws FlooringMasterInvalidDataException {
        int passCode = 1988;
        int userEntry = io.readInt("Please enter the password to set mode...");
        if (userEntry != passCode) {
            throw new FlooringMasterInvalidDataException("ERROR: password incorrect");
        }
    }

    public void displayInTrainingMode() {
        io.print("\n======== FLOOR-O-MATIC 3000 =========");
        io.print("========= IN TRAINING MODE ==========\n");
    }

    public void displayInProMode() {
        io.print("\n======== FLOOR-O-MATIC 3000 =========");
        io.print("=========== IN PRO MODE =============\n");
    }

    public void displayExit() throws InterruptedException {
        io.print("\n      Good day sir.\n");
        Thread.sleep(2000);
    }

    public void displayEnteringTrainingMode() throws InterruptedException {
        io.print("\nYOU ARE NOW ENTERING TRAINING MODE\n"
                + "\nNO RECORDS WILL BE KEPT WHILE IN TRAINING MODE\n");
        Thread.sleep(3000);
    }

}
