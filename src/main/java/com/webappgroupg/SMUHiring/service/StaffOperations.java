package com.webappgroupg.SMUHiring.service;

import com.webappgroupg.SMUHiring.dao.DatabaseOperations;
import com.webappgroupg.SMUHiring.model.*;

import java.util.ArrayList;

public class StaffOperations {
    private DatabaseOperations dbOps = new DatabaseOperations();
    private Staff staffUser;

    // Constructors
    public StaffOperations(String userId) {
        this.staffUser = dbOps.getStaffUser(userId);
    }
    public StaffOperations(String userId, String firstName, String lastName, String email, int phoneNumber) {
        this.staffUser = new Staff(userId, firstName, lastName, email, phoneNumber);
    }
    public void closeConnection(){
        dbOps.closeConnection();
    }


    // Review AccountDeleteRequests
    public ArrayList<String> getDeleteRequests() {
        return dbOps.getDeleteRequests();
    }
    public void reviewDeleteRequest(String requesterUserId, String decision) {
        // Check if user is an employer or professional
        Character userType = dbOps.getUserType(requesterUserId);

        try {
            if (userType == 'E') {
                // If the request is approved, delete the employer
                if (decision.equals("approve")) {
                    dbOps.deleteEmployerFromRequest(requesterUserId);
                }
                // Regardless of decision, delete the request
                dbOps.removeDeleteRequest(requesterUserId);
            } else if (userType == 'P') {
                // If the request is approved, delete the professional
                if (decision.equals("approve")) {
                    dbOps.deleteProfessionalFromRequest(requesterUserId);
                }
                // Regardless of decision, delete the request
                dbOps.removeDeleteRequest(requesterUserId);
            } else {
                System.out.println("Invalid user type. Must be Professional or Employer.");
            }
        } catch (NullPointerException e) {
            System.out.println("User not found.");
        }
    }

    // Review account create requests
    public void reviewCreateRequest(String decision, String requesterUserId) {
        // Check if user is an employer or professional
        Character userType = dbOps.getUserType(requesterUserId);

        if (userType == 'E') {
            reviewEmployerCreateRequest(decision, requesterUserId);
        } else if (userType == 'P') {
            reviewProfessionalCreateRequest(decision, requesterUserId);
        }
        else {
            System.out.println("Invalid user type. Must be Professional or Employer.");
        }
    }
    public void reviewEmployerCreateRequest(String decision, String requesterUserId) {
        Employer employer = dbOps.getEmployerCreateRequest(requesterUserId);
        User user = new User(employer);

        if (decision.equals("approve")) {
            dbOps.createUserAccount(user);
            dbOps.createEmployerAccount(employer);
        }
        dbOps.removeEmployerCreateRequest(requesterUserId);
    }
    public void reviewProfessionalCreateRequest(String decision, String requesterUserId) {
        Professional professional = dbOps.getProfessionalCreateRequest(requesterUserId);
        User user = new User(professional);

        if (decision.equals("approve")) {
            dbOps.createUserAccount(user);
            dbOps.createProfessionalAccount(professional);
        }
        dbOps.removeProfessionalCreateRequest(requesterUserId);
    }

    // Job Matching
    public void initiateJobMatching(String professionalId) {
        dbOps.initiateJobMatching(professionalId);
    }
    public ArrayList<JobMatching> getJobMatches(String professionalId) {
        return dbOps.getJobMatches(professionalId);
    }

    // Password change
    public void changePassword(String newPassword) {
        dbOps.changePassword(staffUser.getUserId(), newPassword);
    }

    // View users
    public ArrayList<Employer> viewEmployers() {
        return dbOps.getEmployers();
    }
    public ArrayList<Professional> viewProfessionals() {
        return dbOps.getProfessionals();
    }
    public ArrayList<Employer> viewEmployerRequests() {
        return dbOps.getEmployerCreateRequests();
    }

    public Staff getStaff() {
        return staffUser;
    }

    public String getPassword(){
        return dbOps.getPassword(staffUser.getUserId());
    }


    // Main function to call all the functions
    public static void main(String[] args) {
        StaffOperations staffOps = new StaffOperations("staff1");

//        staffOps.reviewEmployerCreateRequest("approve", "test");
//        staffOps.reviewProfessionalCreateRequest("approve", "profX");
//        staffOps.reviewDeleteRequest("empX", "approve");
//        staffOps.reviewDeleteRequest("profX", "approve");
//        staffOps.initiateJobMatching("profX");
//        System.out.println(staffOps.getJobMatches("prof2"));
//        staffOps.changePassword("newPassword");
//        System.out.println(staffOps.viewEmployers());
//        System.out.println(staffOps.viewProfessionals());
        staffOps.closeConnection();
    }

}
