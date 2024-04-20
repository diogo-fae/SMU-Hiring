package com.webappgroupg.SMUHiring.service;
import com.webappgroupg.SMUHiring.model.Payment;
import com.webappgroupg.SMUHiring.model.Professional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class ProfessionalOperations {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ProfessionalOperations() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smu_hiring", "root", "password");
            System.out.println(connection);
        } catch (Exception e) {
            System.out.println("Exception while creating the connection - " + e.getMessage());
        }
    }

    public Character getUserType(String userId) {
        try {
            String query = "SELECT userType FROM User WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("userType").charAt(0);
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving user type: " + e.getMessage());
        }
        return null;
    }

    public void deleteEmployerFromRequest(String userId) {
        // Update the status of the user to inactive
        try {
            String query = "UPDATE User SET status = ? WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "inactive");
            preparedStatement.setString(2, userId);
            preparedStatement.executeUpdate();
//            System.out.println("Employer account has been deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while deleting the employer account - " + e.getMessage());
        }
    }

    public void reviewDeleteRequest(String requesterUserId, String decision) {
        // Check if user is an employer or professional
        Character userType = getUserType(requesterUserId);

        try {
            if (userType == 'E') {
                // If the request is approved, delete the employer
                if (decision.equals("approve")) {
                    deleteEmployerFromRequest(requesterUserId);
                }
                // Regardless of decision, delete the request
                removeDeleteRequest(requesterUserId);
            } else if (userType == 'P') {
                // If the request is approved, delete the professional
                if (decision.equals("approve")) {
                    deleteProfessionalFromRequest(requesterUserId);
                }
                // Regardless of decision, delete the request
                removeDeleteRequest(requesterUserId);
            } else {
                System.out.println("Invalid user type. Must be Professional or Employer.");
            }
        } catch (NullPointerException e) {
            System.out.println("User not found.");
        }
    }

    public void deleteProfessionalFromRequest(String userId) {
        // Update the status of the user to inactive
        try {
            String query = "UPDATE User SET status = ? WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "inactive");
            preparedStatement.setString(2, userId);
            preparedStatement.executeUpdate();
//            System.out.println("Professional account has been deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while deleting the professional account - " + e.getMessage());
        }
    }

    public void removeDeleteRequest(String userId) {
        try {
            String query = "DELETE FROM AccountDeleteRequest WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
//            System.out.println("Delete request has been removed successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while removing the delete request - " + e.getMessage());
        }
    }

    public void createProfessionalAccount(String userId, String firstName, String lastName, String email, String phoneNumber, Character userType, String address1, String address2, String city, String state, String zipCode, String university, Date graduationDate, String degreeType) {
        try {
            String query = "INSERT INTO ProfessionalCreateRequest (userId, firstName, lastName, email, phoneNumber, userType, address1, address2, city, state, zipCode, university, graduationDate, degreeType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, email);
            preparedStatement.setLong(5, Long.parseLong(phoneNumber));
            preparedStatement.setString(6, String.valueOf(userType));
            preparedStatement.setString(7, address1);
            preparedStatement.setString(8, address2);
            preparedStatement.setString(9, city);
            preparedStatement.setString(10, state);
            preparedStatement.setString(11, zipCode);
            preparedStatement.setString(12, university);
            preparedStatement.setDate(13, graduationDate);
            preparedStatement.setString(14, degreeType);
            preparedStatement.execute();
            System.out.println("Professional record created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the professional account - " + e.getMessage());
        }
    }

    public void updateProfessionalAccount(String userId, String firstName, String lastName, String email, String phoneNumber, String address1, String address2, String city, String state, String zipCode, String university, Date graduationDate, String degreeType) {
        try {
            String query = "UPDATE ProfessionalCreateRequest SET firstName = ? ,lastName = ?, email = ?, phoneNumber = ?, address1 = ?, address2 = ?, city = ?, state = ?, zipCode = ?, university = ?, graduationDate = ?, degreeType = ? WHERE userid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setLong(4, Long.parseLong(phoneNumber));
            preparedStatement.setString(5, address1);
            preparedStatement.setString(6, address2);
            preparedStatement.setString(7, city);
            preparedStatement.setString(8, state);
            preparedStatement.setString(9, zipCode);
            preparedStatement.setString(10, university);
            preparedStatement.setDate(11, graduationDate);
            preparedStatement.setString(12, degreeType);
            preparedStatement.setString(13, userId);
            preparedStatement.executeUpdate();
            System.out.println("Professional record has been updated successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while updating the professional account - " + e.getMessage());
        }
    }

    public void createJobMatchingRequest(String userId) {
        try {
            String query = "INSERT INTO JobMatchingRequest (userId) VALUES (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.execute();
            System.out.println("Job matching request created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the job matching request - " + e.getMessage());
        }
    }

    public ArrayList<Payment> getPayments(String userId){
        ArrayList<Payment> payments = new ArrayList<Payment>();
        try {
            String query = "SELECT * FROM Payment WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Payment tempPayment = new Payment();
                tempPayment.setPaymentId(resultSet.getString("paymentId"));
                tempPayment.setUserId(resultSet.getString("userId"));
                tempPayment.setPaymentAmount(resultSet.getDouble("paymentAmount"));
                tempPayment.setPaymentDate(resultSet.getDate("paymentDate").toString());
                tempPayment.setDueDate(resultSet.getDate("dueDate").toString());
                payments.add(tempPayment);
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving payments: " + e.getMessage());
        }
        return payments;
    }

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

    public List<String> viewJobMatchingRequest() {
        List<String> userIds = new ArrayList<>();
        try {
            String query = "SELECT * FROM JobMatchingRequest";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userIds.add(resultSet.getString("userId"));
            }
            resultSet.close();
            System.out.println("Job matching requests.");
        } catch (SQLException e) {
            System.out.println("Exception while viewing the job matching request - " + e.getMessage());
        }
        return userIds;
    }

    public Professional getProfessionals(String professionalId) {
        Professional professional = new Professional();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM ProfessionalCreateRequest WHERE userId = ?");
            statement.setString(1, professionalId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                professional.setUserId(resultSet.getString("userId"));
                professional.setFirstName(resultSet.getString("firstName"));
                professional.setLastName(resultSet.getString("lastName"));
                professional.setEmail(resultSet.getString("email"));
                professional.setPhoneNumber(resultSet.getLong("phoneNumber"));
                professional.setStatus(resultSet.getString("userType"));
                professional.setAddress1(resultSet.getString("address1"));
                professional.setAddress2(resultSet.getString("address2"));
                professional.setCity(resultSet.getString("city"));
                professional.setStatus(resultSet.getString("state"));
                professional.setZipCode(resultSet.getInt("zipCode"));
                professional.setUniversity(resultSet.getString("university"));
                professional.setGraduationDate(resultSet.getString("graduationDate"));
                professional.setDegreeType(resultSet.getString("degreeType"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Exception while getting professionals - " + e.getMessage());
        }
        return professional;
    }

    public ArrayList<String> getDeleteRequests(){
        ArrayList<String> deleteRequests = new ArrayList<String>();
        try {
            String query = "SELECT userId FROM AccountDeleteRequest";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                deleteRequests.add(resultSet.getString("userId"));
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving delete requests: " + e.getMessage());
        }
        return deleteRequests;
    }

    public void initiateJobMatching(String jobId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM jobmatching WHERE jobId = ?");
            statement.setString(1, jobId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String company = resultSet.getString("company");
                String positionName = resultSet.getString("positionName");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String startDate = resultSet.getString("startDate");
                String endDate = resultSet.getString("endDate");
                String startTime = resultSet.getString("startTime");
                String endTime = resultSet.getString("endTime");
                String payPerHour = resultSet.getString("payPerHour");
                System.out.println("Job Matching Details : Company "+ company + " Position Name " + positionName + " Name " + name + " Email " + email + " Start Date " + startDate + " End Date "
                        + endDate + " Start Time " + startTime + " End Time " + endTime + " Pay per hour " + payPerHour);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Exception while initiating job matching - " + e.getMessage());
        }
    }

    public void updatePaymentAmount(String userId, double paymentAmount) {
        try {
            String query = "UPDATE payment SET paymentAmount = ?, paymentDate = ? WHERE userid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, paymentAmount);
            preparedStatement.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.setString(3, userId);
            preparedStatement.executeUpdate();
            System.out.println("Professional payment amount has been updated successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while updating the payment for the professional - " + e.getMessage());
        }
    }

    public void updatePassword(String userId, String password) {
        try {
            String query = "UPDATE credentials SET password = ? WHERE userid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, userId);
            preparedStatement.executeUpdate();
            System.out.println("Professional password has been updated successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while updating the password for the professional - " + e.getMessage());
        }
    }

    public void deleteProfessionalAccount(String userId) {
        try {
            String query = "DELETE FROM professional WHERE userid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Professional account deleted successfully.");
            } else {
                System.out.println("No such Professional found.");
            }
        } catch (SQLException e) {
            System.out.println("Exception while deleting professional account - " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("SQLException while closing the connection - " + e.getMessage());
        }
    }

    public static void main(String[] args) throws ParseException {
        /*ProfessionalOperations sqlOperations = new ProfessionalOperations();
        String grad_date = "11-June-07";
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        Date gradDate = (Date) formatter.parse(grad_date);
        sqlOperations.createProfessionalAccount("user4","012 Pine St","Apt B2","New York","NY","10014","EFG University",gradDate, "Bachelor");
        sqlOperations.updateProfessionalAccount("user2","456 Elm Street","Suite 2001","Los Angeles","CA","90002","YZ College",gradDate,"Masters");
        sqlOperations.initiateJobMatching("3");
        sqlOperations.updatePaymentAmount("user3", 200.00);
        sqlOperations.updatePassword("user2", "updatedPassword");
        sqlOperations.deleteProfessionalAccount("user5");
        sqlOperations.closeConnection();*/
    }
}
