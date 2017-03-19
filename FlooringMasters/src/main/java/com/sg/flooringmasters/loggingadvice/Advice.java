
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmasters.loggingadvice;

import com.sg.flooringmasters.dao.FlooringMasterAuditDao;
import com.sg.flooringmasters.dao.FlooringMasterPersistenceException;
import com.sg.flooringmasters.dto.Order;
import java.time.LocalDate;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author apprentice
 */
public class Advice {

    FlooringMasterAuditDao auditDao;

    public Advice(FlooringMasterAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void auditEntryOrderCreated(JoinPoint jp) {
//        public Order addOrder(Order order)
//        JoinPoint in service layer impl
        Order orderAdded = (Order) jp.getArgs()[0];
        String auditEntry = jp.getSignature().getName() + " | order number: " + orderAdded.getOrderNumber() + "| new order added for customer: "
                + orderAdded.getCustomerName() + " | Product type: " + orderAdded.getProductType()
                + " | State: " + orderAdded.getState() + " | area: " + orderAdded.getAreaSQFT()
                + " | order total: " + orderAdded.getOrderTotal() + " | orders scheduled date: "
                + orderAdded.getOrderDate();
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringMasterPersistenceException e) {
            System.err.println("ERROR: I just.  can't.  even...  <( - ___ - )>");
        }
    }

    public void auditEntryProgramOn(JoinPoint jp) {

        String auditEntry = jp.getSignature().getName() + " | Program powered on default to pro mode all files persist";
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringMasterPersistenceException e) {
            System.err.println("ERROR: I just.  can't.  even...  <( - ___ - )>");
        }
    }

    public void auditEntryProgramOff(JoinPoint jp) {

        String auditEntry = jp.getSignature().getName() + " | Program powered off";
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringMasterPersistenceException e) {
            System.err.println("ERROR: I just.  can't.  even...  <( - ___ - )>");
        }
    }

    public void auditEntryOrderEdited(JoinPoint jp) {
//        public Order updateOrder(LocalDate ld, Order orderToUpdate)
//        JoinPoint in service layer impl
        LocalDate originalDate = (LocalDate) jp.getArgs()[0];
        Order orderEdited = (Order) jp.getArgs()[1];
        String auditEntry = jp.getSignature().getName() + " | order number edited: "
                + orderEdited.getOrderNumber() + " | customer: " + orderEdited.getCustomerName()
                + " | order original date: " + originalDate;
        if (originalDate.compareTo(orderEdited.getOrderDate()) != 0) {
            auditEntry += " | new order date: " + orderEdited.getOrderDate();
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringMasterPersistenceException e) {
            System.err.println("ERROR: I just.  can't.  even...  <( - ___ - )>");
        }
    }

    public void auditEntryOrderRemoved(JoinPoint jp) {
//        public Order removeOrder(LocalDate ld, int orderNumber)
//        JoinPoint in service layer impl
        LocalDate orderRemovedDate = (LocalDate) jp.getArgs()[0];
        int removedOrderNumber = (int) jp.getArgs()[1];
        String auditEntry = jp.getSignature().getName() + " | order number removed: "
                + removedOrderNumber + " | date of order removed: " + orderRemovedDate;
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringMasterPersistenceException e) {
            System.err.println("ERROR: I just.  can't.  even...  <( - ___ - )>");
        }
    }

    public void auditEntryModeChanged(JoinPoint jp) {
//        public Boolean setMode(Boolean setTrainingMode)
//        JoinPoint in service layer impl
        boolean trainingMode = (boolean) jp.getArgs()[0];
        String auditEntry = jp.getSignature().getName();
        if (trainingMode == true) {
            auditEntry
                    += ">>>>>( no files persist )>>>>>>>>>  TRAINING MODE ENTERED   <<<<<<<<<<( no files persist )<<<<<";
        } else {
            auditEntry
                    += ">>>>>>>>>( files persist )>>>>>>>>     PRO MODE ENTERED     <<<<<<<<<<<( files persist )<<<<<<<";
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringMasterPersistenceException e) {
            System.err.println("ERROR: I just.  can't.  even...  <( - ___ - )>");
        }
    }

    public void auditEntryPassCodeFailed(JoinPoint jp, Throwable ex) {
//        public void getPassCode()
//        JoinPoint in ui layer flooringmasterview
        String auditEntry = jp.getSignature().getName()
                + " | failed attempt was made to change persistence mode." + " | " + ex;
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringMasterPersistenceException e) {
            System.err.println("ERROR: I just.  can't.  even...  <( - ___ - )>");
        }
    }

    public void auditEntryFailedToPersistData(JoinPoint jp, Throwable ex) {
//        public void getPassCode()
//        JoinPoint in ui layer flooringmasterview
        String auditEntry = jp.getSignature().getName() + " | " + ex;
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringMasterPersistenceException e) {
            System.err.println("ERROR: I just.  can't.  even...  <( - ___ - )>");
        }
    }

    public void auditEntryFailToFindOrder(JoinPoint jp, Throwable ex) {
        LocalDate ld = (LocalDate) jp.getArgs()[0];
        String auditEntry = jp.getSignature().getName()
                + " | failed to find date for date: " + ld + " | " + ex;
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringMasterPersistenceException e) {
            System.err.println("ERROR: I just.  can't.  even...  <( - ___ - )>");
        }
    }
}
