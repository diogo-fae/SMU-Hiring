package com.webappgroupg.SMUHiring.dao;

import com.webappgroupg.SMUHiring.model1.*;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.time.*;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;

@Component
public class SmuHiringDatabaseOperations {
    private Connection connection = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/smu_hiring";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    // Constructor to establish database connection
    public SmuHiringDatabaseOperations() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//            System.out.println(connection);
        } catch (Exception e) {
            System.out.println("Exception while creating the connection - " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
            System.out.println("Connection closed successfully.");
        } catch (SQLException e) {
            System.out.println("SQLException while closing the connection - " + e.getMessage());
        }
    }

    public void registerProfessionalAccountRequest(ProfessionalRequest request) {
     try {
            String query = "INSERT INTO ProfessionalProfessionalRequest (userId, firstName, lastName, email, phoneNumber, address1, address2, city, state, zipCode, university, graduationDate, degreeType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, request.getUserId());
            preparedStatement.setString(2, request.getFirstName());
            preparedStatement.setString(3, request.getLastName());
            preparedStatement.setString(4, request.getEmail());
            preparedStatement.setLong(5, Long.parseLong(request.getPhoneNumber()));
            preparedStatement.setString(6, request.getAddress1());
            preparedStatement.setString(7, request.getAddress2());
            preparedStatement.setString(8, request.getCity());
            preparedStatement.setString(9, request.getState());
            preparedStatement.setString(10, request.getZipCode());
            preparedStatement.setString(11, request.getUniversity());
            try{
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
                java.util.Date date = sdf1.parse(request.getGraduationDate());
                java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
                preparedStatement.setDate(12, sqlStartDate);
            } catch (Exception e) {
                System.out.println("Exception while converting date - " + e.getMessage());
            }

            preparedStatement.setString(13, request.getDegreeType());
            preparedStatement.execute();
            System.out.println("Professional record created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the professional account - " + e.getMessage());
        }
    }

    public void registerProfessionalQualificationRequest(List<ProfessionalQualificationRequest> professionalQualificationRequests) {
        try {
            for(ProfessionalQualificationRequest professionalQualificationRequest: professionalQualificationRequests) {
                String query = "INSERT INTO ProfessionalQualificationRequest (userId, category, keyword) VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, professionalQualificationRequest.getUserId());
                preparedStatement.setString(2, professionalQualificationRequest.getCategory());
                preparedStatement.setString(3, professionalQualificationRequest.getKeyword());
                preparedStatement.execute();
            }
            System.out.println("Professional record created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the professional account - " + e.getMessage());
        }
    }

    public void registerEmployerAccountRequest(EmployerRequest employer) {
        try {
            String query = "INSERT INTO EmployerAccountRequest (userId, firstName, lastName, email, phoneNumber, address1, address2, city, state, zipCode, company) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employer.getUserId());
            preparedStatement.setString(2, employer.getFirstName());
            preparedStatement.setString(3, employer.getLastName());
            preparedStatement.setString(4, employer.getEmail());
            preparedStatement.setLong(5, Long.parseLong(employer.getPhoneNumber()));
            preparedStatement.setString(6, employer.getAddress1());
            preparedStatement.setString(7, employer.getAddress2());
            preparedStatement.setString(8, employer.getCity());
            preparedStatement.setString(9, employer.getState());
            preparedStatement.setInt(10, Integer.parseInt(employer.getZipCode()));
            preparedStatement.setString(11, employer.getCompany());
            preparedStatement.execute();
            System.out.println("Employer request created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the employer request - " + e.getMessage());
        }
    }

    public Object getLoginDetails(Cred cred) {
        boolean isValidUser = false;
        Object obj = new Object();
        try {
            String query = "SELECT password FROM Credentials WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cred.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if(resultSet.getString("password").equalsIgnoreCase(cred.getPwd())){
                    isValidUser = true;
                    try {
                        String userQuery = "SELECT status FROM User WHERE userId = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, cred.getUserId());
                        resultSet = preparedStatement.executeQuery();
                        while(resultSet.next()) {
                            if("active".equalsIgnoreCase(resultSet.getString("status"))){
                                if(resultSet.getString("userType").equalsIgnoreCase("E")){
                                    obj = getProfessionalDetails(cred.getUserId());
                                } else if(resultSet.getString("userType").equalsIgnoreCase("P")){
                                    obj = getEmployerDetails(cred.getUserId());
                                }
                            }
                        }
                    }catch (SQLException e) {
                        System.out.println("Exception while retrieving employer create requests: " + e.getMessage());
                    }
                } else {
                    System.out.println("Invalid Credentials.");
                }
            }
        }catch (SQLException e) {
            System.out.println("Exception while retrieving employer create requests: " + e.getMessage());
        }
        return obj;
    }

    public Professional getProfessionalDetails(String userId){
        Professional professional = new Professional();
        try {
            String query = "SELECT * FROM Professional WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                professional.setAddress1(resultSet.getString("address1"));
                professional.setAddress2(resultSet.getString("address2"));
                professional.setCity(resultSet.getString("city"));
                professional.setState(resultSet.getString("state"));
                professional.setZipCode(resultSet.getInt("zipCode"));
                professional.setUniversity(resultSet.getString("university"));
                professional.setGraduationDate(resultSet.getString("graduationDate"));
                professional.setDegreeType(resultSet.getString("degreeType"));
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving professional details: " + e.getMessage());
        }
        return professional;
    }

    public Professional updateProfessional(Professional request) {
        Professional professional = new Professional();
        try {
            professional = getProfessionalDetails(request.getUserId());
            if(StringUtils.isNotEmpty(professional.getFirstName())){
                String query = "UPDATE Professional SET address1 = ?, address2 = ?, city = ?, state = ?, zipCode = ?, university = ?, graduationDate = ?, degreeType = ? WHERE userId = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, request.getAddress1());
                preparedStatement.setString(2, request.getAddress2());
                preparedStatement.setString(3, request.getCity());
                preparedStatement.setString(4, request.getState());
                preparedStatement.setInt(5, request.getZipCode());
                preparedStatement.setString(6, request.getUniversity());
                preparedStatement.setString(7, request.getGraduationDate());
                preparedStatement.setString(8, request.getDegreeType());
                preparedStatement.setString(9, request.getUserId());
                preparedStatement.executeUpdate();
                System.out.println("Professional record has been updated successfully.");

                query = "UPDATE User SET firstName = ?, lastName = ?, email = ?, phoneNumber = ? WHERE userId = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, request.getFirstName());
                preparedStatement.setString(2, request.getLastName());
                preparedStatement.setString(3, request.getEmail());
                preparedStatement.setLong(4, request.getPhoneNumber());
                preparedStatement.setString(5, request.getUserId());
                preparedStatement.executeUpdate();
                System.out.println("User record has been updated successfully.");
            } else {
                System.out.println("Professional does not exist");
            }
        } catch (SQLException e) {
            System.out.println("Exception while updating the employer account - " + e.getMessage());
        }
        return professional;
    }

    public void updateEmployerAccount(Employer employer) {
        try {
            String query = "UPDATE Employer SET address1 = ?, address2 = ?, city = ?, state = ?, zipCode = ?, company = ? WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employer.getAddress1());
            preparedStatement.setString(2, employer.getAddress2());
            preparedStatement.setString(3, employer.getCity());
            preparedStatement.setString(4, employer.getState());
            preparedStatement.setInt(5, employer.getZipCode());
            preparedStatement.setString(6, employer.getCompany());
            preparedStatement.setString(7, employer.getUserId());
            preparedStatement.executeUpdate();
            System.out.println("Employer record has been updated successfully.");

            query = "UPDATE User SET firstName = ?, lastName = ?, email = ?, phoneNumber = ? WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employer.getFirstName());
            preparedStatement.setString(2, employer.getLastName());
            preparedStatement.setString(3, employer.getEmail());
            preparedStatement.setLong(4, employer.getPhoneNumber());
            preparedStatement.setString(5, employer.getUserId());
            preparedStatement.executeUpdate();
            System.out.println("User record has been updated successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while updating the user account - " + e.getMessage());
        }
    }

    public Employer getEmployerDetails(String userId){
        Employer employer = new Employer();
        try {
            String query = "SELECT * FROM User WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employer.setUserId(userId);
                employer.setFirstName(resultSet.getString("firstName"));
                employer.setLastName(resultSet.getString("lastName"));
                employer.setEmail(resultSet.getString("email"));
                employer.setPhoneNumber(resultSet.getLong("phoneNumber"));
                employer.setStatus(resultSet.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving employer details: " + e.getMessage());
        }
        try {
            String query = "SELECT * FROM Employer WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employer.setAddress1(resultSet.getString("address1"));
                employer.setAddress2(resultSet.getString("address2"));
                employer.setCity(resultSet.getString("city"));
                employer.setState(resultSet.getString("state"));
                employer.setZipCode(resultSet.getInt("zipCode"));
                employer.setCompany(resultSet.getString("company"));
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving employer details: " + e.getMessage());
        }
        return employer;
    }

    public Payment makePayment(Payment request) {
        Payment payment = new Payment();
        try {
            payment = getPayment(request.getUserId());
            if(StringUtils.isEmpty(payment.getPaymentId())){
                String query = "INSERT INTO Payment (userId, paymentId, paymentAmount, dueDate, paymentDate) VALUES (?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, request.getUserId());
                preparedStatement.setString(2, request.getPaymentId());
                preparedStatement.setDouble(3, Double.parseDouble(request.getPaymentAmount()));
                preparedStatement.setDate(4, java.sql.Date.valueOf(request.getDueDate()));
                preparedStatement.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
                preparedStatement.execute();
            } else {
                String query = "UPDATE payment SET paymentAmount = ?, paymentDate = ? WHERE userid = ?";
                preparedStatement = connection.prepareStatement(query);
                BigDecimal b1 = BigDecimal.valueOf(Double.parseDouble(payment.getPaymentAmount()));
                BigDecimal b2 = BigDecimal.valueOf(Double.parseDouble(request.getPaymentAmount()));
                BigDecimal b3 = b1.subtract( b2 );
                //Double paymentAmount = payment.getPaymentAmount() Double.parseDouble(request.getPaymentAmount());
                preparedStatement.setDouble(1, b3.doubleValue());
                preparedStatement.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
                preparedStatement.setString(3, request.getUserId());
                preparedStatement.executeUpdate();
                payment = getPayment(request.getUserId());
            }
            System.out.println("Payment made successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while making payment - " + e.getMessage());
        }
        return payment;
    }

    public Payment getPayment(String userId){
        Payment payment = new Payment();
        try {
            String query = "SELECT * FROM Payment WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                payment.setPaymentId(resultSet.getString("paymentId"));
                payment.setUserId(resultSet.getString("userId"));
                payment.setPaymentAmount(String.valueOf(resultSet.getDouble("paymentAmount")));
                payment.setPaymentDate(resultSet.getDate("paymentDate").toString());
                payment.setDueDate(resultSet.getDate("dueDate").toString());
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving payments: " + e.getMessage());
        }
        return payment;
    }

    public boolean approveAccountDeletion(String userId) {
        boolean isDeleted = false;
        try {
            String query = "SELECT * FROM AccountDeleteRequest WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                updateUserStatus(resultSet.getString("userId"), "delete");
                deleteCredentials(userId);
                isDeleted = true;
            }
        }catch (SQLException e) {
            System.out.println("Exception while retrieving employer create requests: " + e.getMessage());
        }
        return isDeleted;
    }

    public void updateUserStatus(String userId, String action){
        try {
            String query = "UPDATE User SET status = ? WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ("delete").equalsIgnoreCase(action) ? "inactive" : "active");
            preparedStatement.setString(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception while updating user status- " + e.getMessage());
        }
    }

    public void deleteCredentials(String userId) {
        try {
            String query = "DELETE FROM Credentials WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
            System.out.println("Credentials has been deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while deleting the credentials - " + e.getMessage());
        }
    }

    public void requestAccountDeletion(String userId) {
        try {
            String query = "INSERT INTO AccountDeleteRequest (userId) VALUES (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.execute();
            System.out.println("Account deletion request has been sent successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while sending the account deletion request - " + e.getMessage());
        }
    }

    public void changePassword(Cred cred){
        try {
            if(StringUtils.isNotEmpty(getPassword(cred.getUserId()))){
                String query = "UPDATE Credentials SET password = ? WHERE userId = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, cred.getPwd());
                preparedStatement.setString(2, cred.getUserId());
                preparedStatement.executeUpdate();
            } else {
                System.out.println("User does not exist");
            }
        } catch (SQLException e) {
            System.out.println("Exception while changing the password - " + e.getMessage());
        }
    }

    public String getPassword(String userId){
        String password = "";
        try {
            String query = "SELECT password FROM Credentials WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving password: " + e.getMessage());
        }
        return password;
    }
}