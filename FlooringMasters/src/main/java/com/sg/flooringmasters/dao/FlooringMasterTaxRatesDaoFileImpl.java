/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.dao;

import com.sg.flooringmasters.dto.Product;
import com.sg.flooringmasters.dto.TaxRate;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class FlooringMasterTaxRatesDaoFileImpl implements FlooringMasterTaxRatesDao {

    private final String TAXES_FILE = "taxes.txt";
    private static final String DELIMITER = "::";

    private List<TaxRate> loadTaxRates() throws FlooringMasterPersistenceException {
        List<TaxRate> taxRatesList = new ArrayList<>();

        Scanner sc;
        try {
            sc = new Scanner(
                    new BufferedReader(
                            new FileReader(TAXES_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasterPersistenceException(e.getMessage());
        }
        String currentLine;
        String[] tokens;
        sc.nextLine();
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            tokens = currentLine.split(DELIMITER);
            TaxRate taxRate = new TaxRate();
            taxRate.setState(tokens[0]);
            taxRate.setTaxRate(new BigDecimal(tokens[1]));
            taxRatesList.add(taxRate);
        }

        return taxRatesList;
    }

    @Override
    public List<TaxRate> getTaxRates() throws FlooringMasterPersistenceException {
        
        return loadTaxRates();

    }

}
