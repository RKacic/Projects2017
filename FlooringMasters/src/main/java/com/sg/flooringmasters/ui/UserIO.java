/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author apprentice
 */
public interface UserIO {

    void print(String message);

    void print(Integer given);

    void print(Object object);

    void printBigDecimal(String prompt, BigDecimal BD);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);
    
    BigDecimal getBigDecimal(String prompt, BigDecimal min);
    
    BigDecimal getBigDecimal(String prompt, BigDecimal min, BigDecimal max);

    BigDecimal getCurrency(String prompt, double min, double max);

    LocalDate readLocalDate(String prompt);

    String readString(String prompt);
    
    Boolean getYesOrNo(String prompt);
}
