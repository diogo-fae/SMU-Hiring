package com.webappgroupg.SMUHiring.dao;

import com.webappgroupg.SMUHiring.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseOperations {
    private Connection connection = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/smuhiringdbOps";
    private static final String DB_USER = "sqluser";
    private static final String DB_PASSWORD = "password";

    // Constructor to establish database connection
    public DatabaseOperations() {
        try {
            // Load JDBC driver and establish connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println(connection);
        } catch (Exception e) {
            System.out.println("Exception while creating the connection - " + e.getMessage());
        }
    }
    // close database connection
    public void closeConnection() {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("SQLException while closing the connection - " + e.getMessage());
        }
    }

//    public User login(String username, String password) {
//        User user = null;
//        StringBuilder query = new StringBuilder("SELECT * FROM User WHERE userId = ? AND password = ? LIMIT 1");
//
//        try{
//            preparedStatement = connection.prepareStatement(query.toString());
//            preparedStatement.setString(1, username);
//            preparedStatement.setString(2, password);
//
//            resultSet = preparedStatement.executeQuery();
//
//            while(resultSet.next()) {
//                int rsUid = resultSet.getInt("userId");
//                String rsUserName = resultSet.getString("userName");
//                String rsFirstName = resultSet.getString("firstName");
//                String rsLastName = resultSet.getString("lastName");
//                String rsEmail = resultSet.getString("email");
//                int rsPhoneNumber = resultSet.getInt("phoneNumber");
//                String rsStatus = resultSet.getString("status");
//                String rsUserType = resultSet.getString("userType");
//
//                user = new User(rsUid, rsUserName, rsFirstName, rsLastName, rsEmail, rsPhoneNumber, rsStatus, rsUserType);
//            }
//        } catch (SQLException e) {
//            System.out.println("Exception while logging in - " + e.getMessage());
//        }
//        return user;
//    }


    // Create accounts
    public void makePayment(String userId, String paymentId, double paymentAmount, String dueDate, String paymentDate) {
        try {
            String query = "INSERT INTO Payment (userId, paymentId, paymentAmount, dueDate, paymentDate) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, paymentId);
            preparedStatement.setDouble(3, paymentAmount);
            preparedStatement.setDate(4, java.sql.Date.valueOf(dueDate));
            preparedStatement.setDate(5, java.sql.Date.valueOf(paymentDate));
            preparedStatement.execute();
            System.out.println("Payment made successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while making payment - " + e.getMessage());
        }
    }
    public void addCredentials(String userId, String password) {
        try {
            String query = "INSERT INTO Credentials (userId, password) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
            System.out.println("Credentials added successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while adding credentials - " + e.getMessage());
        }
    }
    // With address 2
    public void createEmployerAccount(String userId, String address1, String address2, String city, String state, Integer zipCode, String company) {
        try {
            String query = "INSERT INTO Employer (userId, address1, address2, city, state, zipCode, company) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, address1);
            preparedStatement.setString(3, address2);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, state);
            preparedStatement.setInt(6, zipCode);
            preparedStatement.setString(7, company);
            preparedStatement.execute();
            System.out.println("Employer record created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the employer account - " + e.getMessage());
        }
    }
    // Without address 2
    public void createEmployerAccount(String userId, String address1, String city, String state, Integer zipCode, String company) {
        try {
            String query = "INSERT INTO Employer (userId, address1, address2, city, state, zipCode, company) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, address1);
            preparedStatement.setNull(3, Types.VARCHAR);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, state);
            preparedStatement.setInt(6, zipCode);
            preparedStatement.setString(7, company);
            preparedStatement.execute();
            System.out.println("Employer record created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the employer account - " + e.getMessage());
        }
    }
    // With address 2
    public void createProfessionalAccount(String userId, String address1, String address2, String city, String state, Integer zipCode, String university, String graduationDate, String degreeType) {
        try {
            String query = "INSERT INTO Professional (userId, address1, address2, city, state, zipCode, university, graduationDate, degreeType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, address1);
            preparedStatement.setString(3, address2);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, state);
            preparedStatement.setInt(6, zipCode);
            preparedStatement.setString(7, university);
            preparedStatement.setString(8, graduationDate);
            preparedStatement.setString(9, degreeType);
            preparedStatement.execute();
            System.out.println("Professional record created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the professional account - " + e.getMessage());
        }
    }
    // Without address 2
    public void createProfessionalAccount(String userId, String address1, String city, String state, Integer zipCode, String university, String graduationDate, String degreeType) {
        try {
            String query = "INSERT INTO Professional (userId, address1, address2, city, state, zipCode, university, graduationDate, degreeType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, address1);
            preparedStatement.setNull(3, Types.VARCHAR);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, state);
            preparedStatement.setInt(6, zipCode);
            preparedStatement.setString(7, university);
            preparedStatement.setString(8, graduationDate);
            preparedStatement.setString(9, degreeType);
            preparedStatement.execute();
            System.out.println("Professional record created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the professional account - " + e.getMessage());
        }
    }

    // Requests
    public void createEmployerRequest(String userId, String requestType) {
        try {
            String query = "INSERT INTO EmployerRequest (userId, requestType) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, requestType);
            preparedStatement.execute();
            System.out.println("Employer request created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the employer request - " + e.getMessage());
        }
    }
    public void createProfessionalRequest(String userId, String requestType) {
        try {
            String query = "INSERT INTO ProfessionalRequest (userId, requestType) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, requestType);
            preparedStatement.execute();
            System.out.println("Professional request created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the professional request - " + e.getMessage());
        }
    }

    // Professional Qualifications
    public void createProfessionalQualification(String userId, String category, String keyword) {
        try {
            String query = "INSERT INTO ProfessionalQualifications (userId, category, keyword) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, category);
            preparedStatement.setString(3, keyword);
            preparedStatement.execute();
            System.out.println("Professional qualification created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the professional qualification - " + e.getMessage());
        }
    }
    public Map<String, List<String>> getProfessionalQualifications(String userId) {
        Map<String, List<String>> professionalQualifications = new HashMap<>();
        try {
            String query = "SELECT category, keyword FROM ProfessionalQualifications WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String category = resultSet.getString("category");
                String keyword = resultSet.getString("keyword");

                // Check if category already exists in map
                if (!professionalQualifications.containsKey(category)) {
                    professionalQualifications.put(category, new ArrayList<>());
                }

                // Add keyword to the list under the category
                professionalQualifications.get(category).add(keyword);
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving professional qualifications: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("Exception while closing prepared statement: " + e.getMessage());
            }
        }
        return professionalQualifications;
    }

    // Jobs
    public void createJobPosting(Integer jobId, String company, String positionName, String supervisorName, String supervisorEmail, String startDate, String endDate, String startTime, String endTime, Double payPerHour) {
        try {
            String query = "INSERT INTO JobPosting (jobId, company, positionName, supervisorName, supervisorEmail, startDate, endDate, startTime, endTime, payPerHour) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, jobId);
            preparedStatement.setString(2, company);
            preparedStatement.setString(3, positionName);
            preparedStatement.setString(4, supervisorName);
            preparedStatement.setString(5, supervisorEmail);
            preparedStatement.setDate(6, java.sql.Date.valueOf(startDate));
            preparedStatement.setDate(7, java.sql.Date.valueOf(endDate));
            preparedStatement.setTime(8, java.sql.Time.valueOf(startTime));
            preparedStatement.setTime(9, java.sql.Time.valueOf(endTime));
            preparedStatement.setDouble(10, payPerHour);
            preparedStatement.execute();
            System.out.println("Job posting created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the job posting - " + e.getMessage());
        }
    }
    public void createJobQualification(Integer jobId, String company, String category, String keyword) {
        try {
            String query = "INSERT INTO JobQualifications (jobId, company, category, keyword) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, jobId);
            preparedStatement.setString(2, company);
            preparedStatement.setString(3, category);
            preparedStatement.setString(4, keyword);
            preparedStatement.execute();
            System.out.println("Job qualification created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the job qualification - " + e.getMessage());
        }
    }
    public void createJobMatching(String profUserId, Integer jobId, String company){
        try {
            String query = "INSERT INTO JobMatching (userId, jobId, company) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, profUserId);
            preparedStatement.setInt(2, jobId);
            preparedStatement.setString(3, company);
            preparedStatement.execute();
            System.out.println("Job matching created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the job matching - " + e.getMessage());
        }
    }
    public void createJobMatchingRequest(String userId) {
        try {
            String query = "INSERT INTO JobMatchingRequests (userId) VALUES (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.execute();
            System.out.println("Job matching request created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the job matching request - " + e.getMessage());
        }
    }

    // Updates
    public void updateEmployerAccount(String userId, String address1, String address2, String city, String state, Integer zipCode, String company) {
        try {
            String query = "UPDATE Employer SET address1 = ?, address2 = ?, city = ?, state = ?, zipCode = ?, company = ? WHERE userid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, address1);
            preparedStatement.setString(2, address2);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, state);
            preparedStatement.setInt(5, zipCode);
            preparedStatement.setString(6, company);
            preparedStatement.setString(7, userId);
            preparedStatement.executeUpdate();
            System.out.println("Employer record has been updated successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while updating the employer account - " + e.getMessage());
        }
    }
    public void updateEmployerAccount(String userId, String address1, String city, String state, Integer zipCode, String company) {
        try {
            String query = "UPDATE Employer SET address1 = ?, address2 = ?, city = ?, state = ?, zipCode = ?, company = ? WHERE userid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, address1);
            preparedStatement.setNull(2, Types.VARCHAR);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, state);
            preparedStatement.setInt(5, zipCode);
            preparedStatement.setString(6, company);
            preparedStatement.setString(7, userId);
            preparedStatement.executeUpdate();
            System.out.println("Employer record has been updated successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while updating the employer account - " + e.getMessage());
        }
    }
    public void updateProfessionalAccount(String userId, String address1, String address2, String city, String state, Integer zipCode, String university, String graduationDate, String degreeType) {
        try {
            String query = "UPDATE Professional SET address1 = ?, address2 = ?, city = ?, state = ?, zipCode = ?, university = ?, graduationDate = ?, degreeType = ? WHERE userid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, address1);
            preparedStatement.setString(2, address2);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, state);
            preparedStatement.setInt(5, zipCode);
            preparedStatement.setString(6, university);
            preparedStatement.setString(7, graduationDate);
            preparedStatement.setString(8, degreeType);
            preparedStatement.setString(9, userId);
            preparedStatement.executeUpdate();
            System.out.println("Professional record has been updated successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while updating the employer account - " + e.getMessage());
        }
    }
    public void updateProfessionalAccount(String userId, String address1, String city, String state, Integer zipCode, String university, String graduationDate, String degreeType) {
        try {
            String query = "UPDATE Professional SET address1 = ?, address2 = ?, city = ?, state = ?, zipCode = ?, university = ?, graduationDate = ?, degreeType = ? WHERE userid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, address1);
            preparedStatement.setNull(2, Types.VARCHAR);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, state);
            preparedStatement.setInt(5, zipCode);
            preparedStatement.setString(6, university);
            preparedStatement.setString(7, graduationDate);
            preparedStatement.setString(8, degreeType);
            preparedStatement.setString(9, userId);
            preparedStatement.executeUpdate();
            System.out.println("Professional record has been updated successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while updating the professional account - " + e.getMessage());
        }
    }
    public void updateJobPosting(Integer jobId, String company, String positionName, String supervisorName, String supervisorEmail, String startDate, String endDate, String startTime, String endTime, Double payPerHour) {
        try {
            String query = "UPDATE JobPosting SET positionName = ?, supervisorName = ?, supervisorEmail = ?, startDate = ?, endDate = ?, startTime = ?, endTime = ?, payPerHour = ? WHERE jobId = ? AND company = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, positionName);
            preparedStatement.setString(2, supervisorName);
            preparedStatement.setString(3, supervisorEmail);
            preparedStatement.setDate(4, java.sql.Date.valueOf(startDate));
            preparedStatement.setDate(5, java.sql.Date.valueOf(endDate));
            preparedStatement.setTime(6, java.sql.Time.valueOf(startTime));
            preparedStatement.setTime(7, java.sql.Time.valueOf(endTime));
            preparedStatement.setDouble(8, payPerHour);
            preparedStatement.setInt(9, jobId);
            preparedStatement.setString(10, company);
            preparedStatement.executeUpdate();
            System.out.println("Job posting has been updated successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while updating the job posting - " + e.getMessage());
        }
    }
    public void updatePassword(String userId, String password) {
        try {
            String query = "UPDATE Credentials SET password = ? WHERE userid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, userId);
            preparedStatement.executeUpdate();
            System.out.println("Employer password has been updated successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while updating the password for the employer - " + e.getMessage());
        }
    }

    // Deletes
    public void requestEmployerDeletion(String userId) {
        try {
            String query = "INSERT EmployerRequest (userId, requestType) VALUES(?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(1, "Delete");
            preparedStatement.executeUpdate();
            System.out.println("Employer deletion request has been sent successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while sending the employer deletion request - " + e.getMessage());
        }
    }
    public void deleteEmployerRequest(String userId) {
        try {
            String query = "DELETE FROM EmployerRequest WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
            System.out.println("Employer request has been deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while deleting the employer request - " + e.getMessage());
        }
    }
    public void requestProfessionalDeletion(String userId) {
        try {
            String query = "INSERT ProfessionalRequest (userId, requestType) VALUES(?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(1, "Delete");
            preparedStatement.executeUpdate();
            System.out.println("Professional deletion request has been sent successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while sending the professional deletion request - " + e.getMessage());
        }
    }
    public void deleteProfessionalRequest(String userId) {
        try {
            String query = "DELETE FROM JobMatchingRequests WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
            System.out.println("Professional request has been deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while deleting the professional request - " + e.getMessage());
        }
    }
    public void deleteJobMatchingRequest(String userId) {
        try {
            String query = "DELETE FROM JobMatchingRequests WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
            System.out.println("Job matching request has been deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while deleting the job matching request - " + e.getMessage());
        }
    }
    public void deleteJobPosting(Integer jobId, String company) {
        try {
            String query = "DELETE FROM JobPosting WHERE jobId = ? AND company = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, jobId);
            preparedStatement.setString(2, company);
            preparedStatement.executeUpdate();
            System.out.println("Job posting has been deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while deleting the job posting - " + e.getMessage());
        }
    }




    // Staff Functionalities
    public void createEmployerFromRequest(String userId, String address1, String address2, String city, String state, Integer zipCode, String company){
        // Retrieve the request and delete it
        deleteEmployerRequest(userId);
        // Update the status of the user to active
        try {
            String query = "UPDATE User SET status = ? WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "active");
            preparedStatement.setString(2, userId);
            preparedStatement.executeUpdate();
            System.out.println("Employer account has been created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the employer account - " + e.getMessage());
        }
        // Create Employer account
        createEmployerAccount(userId, address1, address2, city, state, zipCode, company);
    }
    public void createEmployerFromRequest(String userId, String address1, String city, String state, Integer zipCode, String company){
        // Retrieve the request and delete it
        deleteEmployerRequest(userId);
        // Update the status of the user to active
        try {
            String query = "UPDATE User SET status = ? WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "active");
            preparedStatement.setString(2, userId);
            preparedStatement.executeUpdate();
            System.out.println("Employer account has been created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the employer account - " + e.getMessage());
        }
        // Create Employer account
        createEmployerAccount(userId, address1, city, state, zipCode, company);
    }
    public void createProfessionalFromRequest(String userId, String address1, String address2, String city, String state, Integer zipCode, String university, String graduationDate, String degreeType){
        // Retrieve the request and delete it
        deleteProfessionalRequest(userId);
        // Update the status of the user to active
        try {
            String query = "UPDATE User SET status = ? WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "active");
            preparedStatement.setString(2, userId);
            preparedStatement.executeUpdate();
            System.out.println("Professional account has been created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the professional account - " + e.getMessage());
        }
        // Create Professional account
        createProfessionalAccount(userId, address1, address2, city, state, zipCode, university, graduationDate, degreeType);
    }
    public void createProfessionalFromRequest(String userId, String address1, String city, String state, Integer zipCode, String university, String graduationDate, String degreeType){
        // Retrieve the request and delete it
        deleteProfessionalRequest(userId);
        // Update the status of the user to active
        try {
            String query = "UPDATE User SET status = ? WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "active");
            preparedStatement.setString(2, userId);
            preparedStatement.executeUpdate();
            System.out.println("Professional account has been created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the professional account - " + e.getMessage());
        }
        // Create Professional account
        createProfessionalAccount(userId, address1, city, state, zipCode, university, graduationDate, degreeType);
    }
    public void deleteEmployerFromRequest(String userId) {
        // Retrieve the request and delete it
        deleteEmployerRequest(userId);
        // Update the status of the user to inactive
        try {
            String query = "UPDATE User SET status = ? WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "inactive");
            preparedStatement.setString(2, userId);
            preparedStatement.executeUpdate();
            System.out.println("Employer account has been deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while deleting the employer account - " + e.getMessage());
        }
    }
    public void deleteProfessionalFromRequest(String userId) {
        // Retrieve the request and delete it
        deleteProfessionalRequest(userId);
        // Update the status of the user to inactive
        try {
            String query = "UPDATE User SET status = ? WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "inactive");
            preparedStatement.setString(2, userId);
            preparedStatement.executeUpdate();
            System.out.println("Professional account has been deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while deleting the professional account - " + e.getMessage());
        }
    }
    public ResultSet getJobsWithCategory(String category) {
        try {
            String query = "SELECT jobId, company, keyword FROM JobQualifications WHERE category = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, category);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Exception while retrieving job qualifications: " + e.getMessage());
        }
        return null;
    }
    public void initiateJobMatchingFromRequest(String userId) {
        // Retrieve the request and delete it
        deleteProfessionalRequest(userId);
        // Get all skills from ProfessionalQualifications
        Map<String, List<String>> professionalSkills = getProfessionalQualifications(userId);

        // Find which job qualifications match the professional qualifications (get jobId and company)
        // For each category in professionalSkills, get the list of keywords and check if they match with the job qualifications
        for (Map.Entry<String, List<String>> entry : professionalSkills.entrySet()) {
            String category = entry.getKey();
            List<String> keywords_from_professional = entry.getValue();

            // Get the job qualifications for the current category
            ResultSet jobsWithCategory = getJobsWithCategory(category);

            // Iterate over the jobs that have the current category listed
            try {
                while (jobsWithCategory.next()) {
                    // Get details of the job
                    Integer jobId = jobsWithCategory.getInt("jobId");
                    String company = jobsWithCategory.getString("company");
                    String keyword_from_job = jobsWithCategory.getString("keyword");

                    // Check if the keyword matches with any of the professional qualifications
                    if (keywords_from_professional.contains(keyword_from_job)) {
                        // Create JobMatching
                        System.out.println("Match found for jobId: " + jobId + " and company: " + company);
                        createJobMatching(userId, jobId, company);
                    }
                }
            } catch (SQLException e) {
                // No more jobs
            }
        }
    }

    // Root
    public void createUserAccount(String userId, String firstName, String lastName, String email, Integer phone, String userType) {
        try {
            String query = "INSERT INTO User (userId, firstName, lastName, email, phoneNumber, status, userType) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, email);
            preparedStatement.setInt(5, phone);
            preparedStatement.setString(6, "active");
            preparedStatement.setString(7, userType);
            preparedStatement.execute();
            System.out.println("User record created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the user account - " + e.getMessage());
        }
    }



//    public static void main(String[] args) {
//        DatabaseOperations dbOps = new DatabaseOperations();
//
//        // Close the connection after use.
//        dbOps.closeConnection();
//    }
}