/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public void printBigDecimal(String prompt, BigDecimal currency) {
        System.out.printf(prompt + "%.2f", currency);
    }

    /**
     *
     * @param given
     */
    @Override
    public void print(Integer given) {
        System.out.println(given);
    }

    @Override
    public void print(Object object) {
        System.out.println(object);
    }

    @Override
    public double readDouble(String prompt) {

        System.out.println(prompt);
        boolean isValid = false;
        double num = 0;
        while (!isValid) {
            try {
                String input = sc.nextLine();
                num = Double.parseDouble(input);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input, please try again.");
            }
        }
        return num;

    }

    @Override
    public double readDouble(String prompt, double min, double max) {

        System.out.println(prompt);
        boolean isValid = false;
        boolean inRange = false;
        double num = 0;
        while (!inRange) {
            while (!isValid) {
                try {
                    String input = sc.nextLine();
                    num = Double.parseDouble(input);
                    isValid = true;
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input, please try again.");
                }
            }
            if (num >= min && num <= max) {
                inRange = true;
            } else {
                System.out.println("Whoops please enter a number in given range");
                isValid = false;
            }
        }
        return num;

    }

    @Override
    public float readFloat(String prompt) {

        System.out.println(prompt);
        boolean isValid = false;
        float num = 0;
        while (!isValid) {
            try {
                String input = sc.nextLine();
                num = Float.parseFloat(input);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input, please try again.");
            }
        }
        return num;

    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        boolean isValid = false;
        boolean inRange = false;
        float num = 0;
        while (!inRange) {
            while (!isValid) {
                try {
                    String input = sc.nextLine();
                    num = Float.parseFloat(input);
                    isValid = true;
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input, please try again.");
                }
            }
            if (num >= min && num <= max) {
                inRange = true;
            } else {
                System.out.println("Whoops please enter a number in given range");
                isValid = false;
            }
        }
        return num;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        boolean isValid = false;
        int num = 0;
        while (!isValid) {
            try {
                String input = sc.nextLine();
                num = Integer.parseInt(input);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input, please try again.");
            }
        }
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.print(prompt + "  ");
        boolean isValid = false;
        boolean inRange = false;
        int num = 0;
        while (!inRange) {
            while (!isValid) {
                try {
                    String input = sc.nextLine();
                    num = Integer.parseInt(input);
                    isValid = true;
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input, please try again.");
                }
            }
            if (num >= min && num <= max) {
                inRange = true;
            } else {
                System.out.println("   Whoops please enter a number in given range");
                isValid = false;
            }
        }
        return num;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        boolean isValid = false;
        long num = 0;
        while (!isValid) {
            try {
                String input = sc.nextLine();
                num = Long.parseLong(input);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input, please try again.");
            }
        }
        return num;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        boolean isValid = false;
        boolean inRange = false;
        long num = 0;
        while (!inRange) {
            while (!isValid) {
                try {
                    String input = sc.nextLine();
                    num = Long.parseLong(input);
                    isValid = true;
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input, please try again.");
                }
            }
            if (num >= min && num <= max) {
                inRange = true;
            } else {
                System.out.println("Whoops please enter a number in given range");
                isValid = false;
            }
        }
        return num;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String input = sc.nextLine();
        boolean isString = false;
        while (!isString) {
            if (input.length() >= 0) {
                isString = true;
            } else {
                System.out.println("Whoops please enter a string");
                input = sc.nextLine();
            }
        }
        return input;
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
        LocalDate ld = null;
        LocalDate companyStartDate = LocalDate.parse("2010-01-01");
        LocalDate futureCheck = LocalDate.now().plusYears(4);
        boolean isValidFormat = false;
        String userInput;
        while (!isValidFormat) {
            try {
                System.out.println("Enter a Date in MM/dd/yyyy format please.");
                userInput = sc.nextLine();
                ld = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                if(ld.isBefore(companyStartDate)){
                    System.out.println("Invalid date. Must be after company start date of 01-01-2010");
                     continue;
                }
                if(ld.isAfter(futureCheck)){
                    System.out.println("Invalid date. Cannot be greater then 4 years from today");
                     continue;
                }
                isValidFormat = true;

            } catch (Exception e) {
                System.out.println("Whoops please use proper formatting...");
            }

        }
        return ld;
    }

    @Override
    public BigDecimal getCurrency(String prompt, double min, double max) {
        boolean isValid = false;
        BigDecimal currentBalance = null;
        while (!isValid) {
            Double currencyDouble = readDouble(prompt, min, max);

            String currencyString = currencyDouble.toString();
            if (currencyString.length() > 4) {
                System.out.println("Whoops invalid input, try again");
                continue;
            } else {
                currentBalance = new BigDecimal(currencyString);
                isValid = true;
            }
        }
        return currentBalance;
    }

    @Override
    public BigDecimal getBigDecimal(String prompt, BigDecimal min) {
        boolean isValid = false;
        BigDecimal newBD = null;
        while (!isValid) {
            System.out.println(prompt);
            try{
                String bdString = sc.nextLine();
                BigDecimal bd = new BigDecimal(bdString);
                if (bd.compareTo(min) < 0) {
                    System.out.println("\nWhoops invalid input, please enter an amount equal to or greater then " + min+"\n");
                    continue;
                } else {
                    newBD = bd;
                    isValid = true;
                }
            } catch (Exception ex){
                System.out.println("Invalid input, please try again.");
            }
        }
        return newBD;
    }

    @Override
    public BigDecimal getBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
         boolean isValid = false;
        BigDecimal newBD = null;
        while (!isValid) {
            try{
                System.out.println(prompt);
                String bdString = sc.nextLine();
                BigDecimal bd = new BigDecimal(bdString);
                if (bd.compareTo(min) >= 0 && bd.compareTo(max) <= 0){
                    System.out.println("Whoops invalid input, please enter an amount equal to or greater then " + min);
                    continue;
                } else {
                    newBD = bd;
                    isValid = true;
                }
            } catch (Exception ex){
                System.out.println("Invalid input, please try again.");
            }
        }
        return newBD;
    }

    @Override
    public Boolean getYesOrNo(String prompt) {
        boolean isValid = false;
        boolean response = false;
        while (!isValid) {
            System.out.println(prompt + " Yes or No?");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("yes")
                    || answer.equalsIgnoreCase("y")
                    || answer.equalsIgnoreCase("yea")
                    || answer.equalsIgnoreCase("yeah")) {
                response = true;
                isValid = true;
            } else if (answer.equalsIgnoreCase("no")
                    || answer.equalsIgnoreCase("n")
                    || answer.equalsIgnoreCase("nope")
                    || answer.equalsIgnoreCase("nah")) {
                response = false;
                isValid = true;
            } else {
                System.out.println("Please answer the question with the proper responce.");
            }
        }
        return response;
    }

}
