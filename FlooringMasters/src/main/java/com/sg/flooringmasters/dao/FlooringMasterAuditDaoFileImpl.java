/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author apprentice
 */
public class FlooringMasterAuditDaoFileImpl implements FlooringMasterAuditDao{

    private final String AUDIT_FILE = "audit/auditLog.txt";
    
    @Override
    public void writeAuditEntry(String entry) throws FlooringMasterPersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new FlooringMasterPersistenceException("could not persist audit info", e);
        }
        LocalDateTime timestamp = LocalDateTime.now();
        String formatted = timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        out.println("----  "+formatted+ " | " + entry);
        out.flush();
        out.close();
    }
    
}
