/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.dao;

import com.sg.flooringmasters.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class FlooringMasterProductsDaoFileImpl implements FlooringMasterProductsDao{
    
    private final String PRODUCT_FILE = "products.txt";
    private static final String DELIMITER = "::";
    
    private List<Product> loadProductList() throws FlooringMasterPersistenceException{
        List<Product> productsList = new ArrayList<>();
        
        Scanner sc;
        try {
            sc = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasterPersistenceException(e.getMessage());
        }

        String currentLine;
        String[] tokens;
        sc.nextLine();
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            tokens = currentLine.split(DELIMITER);
            Product product = new Product();
            product.setProductType(tokens[0]);
            product.setMaterialCostPerSQFT(new BigDecimal(tokens[1]));
            product.setLaborCostPerSQFT(new BigDecimal(tokens[2]));
            productsList.add(product);
        }
        
        return productsList;
    }

    @Override
    public List<Product> getProducts() throws FlooringMasterPersistenceException {
        return loadProductList();
    }


}
