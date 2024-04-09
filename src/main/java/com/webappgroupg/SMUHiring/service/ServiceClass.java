package com.webappgroupg.SMUHiring.service;

import com.webappgroupg.SMUHiring.dao.DatabaseOperations;

public class ServiceClass {
    private DatabaseOperations dbOps = new DatabaseOperations();

    public void makePayment(String userId, String paymentId, double paymentAmount, String dueDate, String paymentDate) {
//        if(!isEmployerOrProfessional(userId)){
//            System.out.println("User is not an employer or professional");
//            return;
//        }
        dbOps.makePayment(userId, paymentId, paymentAmount, dueDate, paymentDate);
    }
    public void createUserAccount(String userId, String firstName, String lastName, String email, Integer phone, String userType) {
        dbOps.createUserAccount(userId, firstName, lastName, email, phone, userType);
    }
}
