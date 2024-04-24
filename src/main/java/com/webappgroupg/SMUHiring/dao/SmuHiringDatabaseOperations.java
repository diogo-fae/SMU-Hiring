package com.webappgroupg.SMUHiring.dao;

import com.webappgroupg.SMUHiring.model.*;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.sql.*;
import java.util.*;
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

    public SmuHiringDatabaseOperations() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
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
            String query = "INSERT INTO ProfessionalAccountRequest (userId, firstName, lastName, email, phoneNumber, address1, address2, city, state, zipCode, university, graduationDate, degreeType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            preparedStatement.setInt(10, Integer.parseInt(request.getZipCode()));
            preparedStatement.setString(11, request.getUniversity());
            try {
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

    public void registerProfessionalQualificationRequest(String userId, Map<String, List<String>> professionalQualificationRequests) {
        try {
            for (Map.Entry<String, List<String>> professionalQualificationRequest : professionalQualificationRequests.entrySet()) {
                String category = professionalQualificationRequest.getKey();
                for (String keyword : professionalQualificationRequest.getValue()){
                    String query = "INSERT INTO ProfessionalQualificationRequest (userId, category, keyword) VALUES (?, ?, ?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, userId);
                    preparedStatement.setString(2, category);
                    preparedStatement.setString(3, keyword);
                    preparedStatement.execute();
                }
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

    public User getLoginDetails(Cred cred) {
        boolean isValidUser = false;
        User user = new User();
        try {
            String query = "SELECT password FROM Credentials WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cred.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("password").equalsIgnoreCase(cred.getPwd())) {
                    isValidUser = true;
                    try {
                        String userQuery = "SELECT * FROM User WHERE userId = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, cred.getUserId());
                        resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()) {
                            if ("active".equalsIgnoreCase(resultSet.getString("status"))) {
                                user.setUserId(cred.getUserId());
                                user.setFirstName(resultSet.getString("firstName"));
                                user.setLastName(resultSet.getString("lastName"));
                                user.setEmail(resultSet.getString("email"));
                                user.setPhoneNumber(String.valueOf(resultSet.getLong("phoneNumber")));
                                user.setStatus(resultSet.getString("status"));
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Exception while retrieving employer create requests: " + e.getMessage());
                    }
                } else {
                    System.out.println("Invalid Credentials.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving employer create requests: " + e.getMessage());
        }
        return user;
    }

    public Professional getProfessionalInfo(String userId){
        Professional professional = new Professional();
        try {
            String query = "SELECT * FROM User WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                professional.setUserId(resultSet.getString("userId"));
                professional.setFirstName(resultSet.getString("firstName"));
                professional.setLastName(resultSet.getString("lastName"));
                professional.setEmail(resultSet.getString("email"));
                professional.setPhoneNumber(String.valueOf(resultSet.getLong("phoneNumber")));
            }
            query = "SELECT * FROM Professional WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                professional.setAddress1(resultSet.getString("address1"));
                professional.setAddress2(resultSet.getString("address2"));
                professional.setCity(resultSet.getString("city"));
                professional.setState(resultSet.getString("state"));
                professional.setZipCode(String.valueOf(resultSet.getInt("zipCode")));
                professional.setUniversity(resultSet.getString("university"));
                professional.setGraduationDate(resultSet.getString("graduationDate").toString());
                professional.setDegreeType(resultSet.getString("degreeType"));
            }
            Map<String, List<String>> qualifications = getProfessionalQualifications(userId);
            professional.setProfessionalQualificationsList(qualifications);
        } catch (SQLException e) {
            System.out.println("Exception while retrieving professional details: " + e.getMessage());
        }
        return professional;
    }

    public Map<String, List<String>> getProfessionalQualifications(String userId) {
        Map<String, List<String>> qualifications = new HashMap<>();
        try {
            String query = "SELECT * FROM ProfessionalQualification WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String category = resultSet.getString("category");
                String keyword = resultSet.getString("keyword");
                if (qualifications.containsKey(category)) {
                    qualifications.get(category).add(keyword);
                } else {
                    qualifications.put(category, new ArrayList<String>());
                    qualifications.get(category).add(keyword);
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving professional qualifications: " + e.getMessage());
        }
        return qualifications;
    }

    public Map<String, List<String>> getJobQualifications(int userId, String company) {
        Map<String, List<String>> qualifications = new HashMap<>();
        try {
            String query = "SELECT * FROM JobQualification WHERE jobId = ? AND company = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, company);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String category = resultSet.getString("category");
                String keyword = resultSet.getString("keyword");
                if (qualifications.containsKey(category)) {
                    qualifications.get(category).add(keyword);
                } else {
                    qualifications.put(category, new ArrayList<String>());
                    qualifications.get(category).add(keyword);
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving job qualifications: " + e.getMessage());
        }
        return qualifications;
    }

    public JobPosting getJobInfo(int jobId, String company) {
        JobPosting jobPosting = new JobPosting();
        try {
            String query = "SELECT * FROM JobPosting WHERE jobId = ? AND company = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, jobId);
            preparedStatement.setString(2, company);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                jobPosting.setJobId(String.valueOf(resultSet.getInt("jobId")));
                jobPosting.setCompany(resultSet.getString("company"));
                jobPosting.setPositionName(resultSet.getString("positionName"));
                jobPosting.setSupervisorFirstName(resultSet.getString("supervisorFirstName"));
                jobPosting.setSupervisorLastName(resultSet.getString("supervisorLastName"));
                jobPosting.setSupervisorEmail(resultSet.getString("supervisorEmail"));
                jobPosting.setSupervisorPhoneNumber(String.valueOf(resultSet.getLong("supervisorPhoneNumber")));
                jobPosting.setStartDate(resultSet.getDate("startDate").toString());
                jobPosting.setEndDate(resultSet.getDate("endDate").toString());
                jobPosting.setStartTime(resultSet.getTime("startTime").toString());
                jobPosting.setEndTime(resultSet.getTime("endTime").toString());
                jobPosting.setPayPerHour(String.valueOf(resultSet.getDouble("payPerHour")));
                jobPosting.setJobQualificationsList(getJobQualifications(jobId, company));
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving job posting Info: " + e.getMessage());
        }
        return jobPosting;
    }


    public Employer updateEmployer(Employer request) {
        Employer employer = new Employer();
        try {
            employer = getEmployerInfo(request.getUserId());
            if (StringUtils.isNotEmpty(employer.getFirstName())) {
                String query = "UPDATE Employer SET address1 = ?, address2 = ?, city = ?, state = ?, zipCode = ?, company = ? WHERE userId = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, request.getAddress1());
                preparedStatement.setString(2, request.getAddress2());
                preparedStatement.setString(3, request.getCity());
                preparedStatement.setString(4, request.getState());
                preparedStatement.setInt(5, Integer.parseInt(request.getZipCode()));
                preparedStatement.setString(6, request.getCompany());
                preparedStatement.setString(7, request.getUserId());
                preparedStatement.executeUpdate();
                System.out.println("Employer record has been updated successfully.");
                updateUser(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber(), request.getUserId());
            } else {
                System.out.println("Employer does not exist");
            }
        } catch (SQLException e) {
            System.out.println("Exception while updating the employer account - " + e.getMessage());
        }
        return employer;
    }


    public Professional updateProfessional(Professional request) {
        Professional professional = new Professional();
        try {
            professional = getProfessionalInfo(request.getUserId());
            if (StringUtils.isNotEmpty(professional.getFirstName())) {
                String query = "UPDATE Professional SET address1 = ?, address2 = ?, city = ?, state = ?, zipCode = ?, university = ?, graduationDate = ?, degreeType = ? WHERE userId = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, request.getAddress1());
                preparedStatement.setString(2, request.getAddress2());
                preparedStatement.setString(3, request.getCity());
                preparedStatement.setString(4, request.getState());
                preparedStatement.setInt(5, Integer.parseInt(request.getZipCode()));
                preparedStatement.setString(6, request.getUniversity());
                preparedStatement.setDate(7, java.sql.Date.valueOf(request.getGraduationDate()));
                preparedStatement.setString(8, request.getDegreeType());
                preparedStatement.setString(9, request.getUserId());
                preparedStatement.executeUpdate();
                System.out.println("Professional record has been updated successfully.");

                updateUser(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber(), request.getUserId());
                updateProfessionalQualifications(request.getUserId(), request.getProfessionalQualificationsList());
            } else {
                System.out.println("Professional does not exist");
            }
        } catch (SQLException e) {
            System.out.println("Exception while updating the employer account - " + e.getMessage());
        }
        return professional;
    }

    public void updateProfessionalQualifications(String userId, Map<String, List<String>> qualifications) {
        try {
            for (Map.Entry<String, List<String>> entry : qualifications.entrySet()) {
                String category = entry.getKey();
                String query1 = "DELETE FROM ProfessionalQualification WHERE userId = ? AND category = ?";
                preparedStatement = connection.prepareStatement(query1);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, category);
                preparedStatement.executeUpdate();
                List<String> keywords = entry.getValue();
                for (String keyword : keywords) {
                    String query2 = "INSERT INTO ProfessionalQualification (userId, category, keyword) VALUES (?, ?, ?)";
                    preparedStatement = connection.prepareStatement(query2);
                    preparedStatement.setString(1, userId);
                    preparedStatement.setString(2, category);
                    preparedStatement.setString(3, keyword);
                    preparedStatement.execute();
                }
            }
            System.out.println("Professional qualifications updated successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while updating the professional qualifications - " + e.getMessage());
        }
    }

    public void updateEmployerAccount(Employer employer) {
        try {
            String query = "UPDATE Employer SET address1 = ?, address2 = ?, city = ?, state = ?, zipCode = ?, company = ? WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employer.getAddress1());
            preparedStatement.setString(2, employer.getAddress2());
            preparedStatement.setString(3, employer.getCity());
            preparedStatement.setString(4, employer.getState());
            preparedStatement.setInt(5, Integer.parseInt(employer.getZipCode()));
            preparedStatement.setString(6, employer.getCompany());
            preparedStatement.setString(7, employer.getUserId());
            preparedStatement.executeUpdate();
            System.out.println("Employer record has been updated successfully.");
            updateUser(employer.getFirstName(), employer.getLastName(), employer.getEmail(), employer.getPhoneNumber(), employer.getUserId());
        } catch (SQLException e) {
            System.out.println("Exception while updating the user account - " + e.getMessage());
        }
    }

    public Payment makePayment(Payment request) {
        Payment payment = new Payment();
        try {
            payment = getPayment(request.getUserId());
            if (StringUtils.isEmpty(payment.getPaymentId())) {
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
                BigDecimal b3 = b1.subtract(b2);
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

    public Payment getPayment(String userId) {
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
        } catch (SQLException e) {
            System.out.println("Exception while retrieving employer create requests: " + e.getMessage());
        }
        return isDeleted;
    }

    public void updateUserStatus(String userId, String action) {
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

    public void changePassword(Cred cred) {
        try {
            if (StringUtils.isNotEmpty(getPassword(cred.getUserId()))) {
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

    public String getPassword(String userId) {
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

    public void initiateJobMatching(String userId){
        try {
            String query = "INSERT INTO JobMatchingRequest (userId) VALUES (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Exception while creating job matching request - " + e.getMessage());
        }
    }

    public void postJob(JobPosting request) {
        try {
            String query = "INSERT INTO JobPosting (jobId, company, positionName, supervisorFirstName, supervisorLastName, supervisorEmail, supervisorPhoneNumber, startDate, endDate, startTime, endTime, payPerHour) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(request.getJobId()));
            preparedStatement.setString(2, request.getCompany());
            preparedStatement.setString(3, request.getPositionName());
            preparedStatement.setString(4, request.getSupervisorFirstName());
            preparedStatement.setString(5, request.getSupervisorLastName());
            preparedStatement.setString(6, request.getSupervisorEmail());
            preparedStatement.setLong(7, Long.parseLong(request.getSupervisorPhoneNumber()));
            preparedStatement.setDate(8, java.sql.Date.valueOf(request.getStartDate()));
            preparedStatement.setDate(9, java.sql.Date.valueOf(request.getEndDate()));
            preparedStatement.setTime(10, java.sql.Time.valueOf(request.getStartTime()));
            preparedStatement.setTime(11, java.sql.Time.valueOf(request.getEndTime()));
            preparedStatement.setDouble(12, Double.parseDouble(request.getPayPerHour()));
            preparedStatement.execute();

            if(!CollectionUtils.isEmpty(request.getJobQualificationsList())) {
                for (Map.Entry<String, List<String>> professionalQualificationRequest : request.getJobQualificationsList().entrySet()) {
                    String category = professionalQualificationRequest.getKey();
                    for (String keyword : professionalQualificationRequest.getValue()){
                        query = "INSERT INTO JobQualification (jobId, company, category, keyword) VALUES (?, ?, ?, ?)";
                        preparedStatement = connection.prepareStatement(query);
                        System.out.print("Job ID: " + request.getJobId() + " ");
                        preparedStatement.setInt(1, Integer.parseInt(request.getJobId()));
                        System.out.print("Company: " + request.getCompany() + " ");
                        preparedStatement.setString(2, request.getCompany());
                        System.out.print("Category: " + category + " ");
                        preparedStatement.setString(3, category);
                        System.out.print("Keyword: " + keyword + " ");
                        preparedStatement.setString(4, keyword);
                        System.out.println("EXECUTE");
                        preparedStatement.execute();
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL exception while creating the job posting - " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument exception while creating the job posting - " + e.getMessage());
        }
    }

    public void deleteJob(JobPosting request) {
        try {
            String query = "DELETE FROM JobPosting WHERE jobId = ? AND company = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(request.getJobId()));
            preparedStatement.setString(2, request.getCompany());
            preparedStatement.executeUpdate();

            if(!CollectionUtils.isEmpty(request.getJobQualificationsList())) {
                for (Map.Entry<String, List<String>> professionalQualificationRequest : request.getJobQualificationsList().entrySet()) {
                    String category = professionalQualificationRequest.getKey();
                    for (String keyword : professionalQualificationRequest.getValue()) {
                        query = "DELETE FROM JobQualification WHERE jobId = ? AND company = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, Integer.parseInt(request.getJobId()));
                        preparedStatement.setString(2, request.getCompany());
                        preparedStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception while deleting the job posting - " + e.getMessage());
        }

    }

    public JobPosting updateJob(JobPosting request) {
         try {
            String query = "UPDATE JobPosting SET positionName = ?, supervisorFirstName = ?, supervisorLastName = ?, supervisorEmail = ?, supervisorPhoneNumber = ?, startDate = ?, endDate = ?, startTime = ?, endTime = ?, payPerHour = ? WHERE jobId = ? AND company = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, request.getPositionName());
            preparedStatement.setString(2, request.getSupervisorFirstName());
            preparedStatement.setString(3, request.getSupervisorLastName());
            preparedStatement.setString(4, request.getSupervisorEmail());
            preparedStatement.setLong(5, Long.parseLong(request.getSupervisorPhoneNumber()));
            preparedStatement.setDate(6, java.sql.Date.valueOf(request.getStartDate()));
            preparedStatement.setDate(7, java.sql.Date.valueOf(request.getEndDate()));
            preparedStatement.setTime(8, java.sql.Time.valueOf(request.getStartTime()));
            preparedStatement.setTime(9, java.sql.Time.valueOf(request.getEndTime()));
            preparedStatement.setDouble(10, Double.parseDouble(request.getPayPerHour()));
            preparedStatement.setInt(11, Integer.parseInt(request.getJobId()));
            preparedStatement.setString(12, request.getCompany());
            preparedStatement.executeUpdate();

            if(!CollectionUtils.isEmpty(request.getJobQualificationsList())) {
                query = "DELETE FROM JobQualification WHERE jobId = ? AND company = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.parseInt(request.getJobId()));
                preparedStatement.setString(2, request.getCompany());
                preparedStatement.executeUpdate();

                for (Map.Entry<String, List<String>> professionalQualificationRequest : request.getJobQualificationsList().entrySet()) {
                    String category = professionalQualificationRequest.getKey();
                    for (String keyword : professionalQualificationRequest.getValue()) {
                        query = "INSERT INTO JobQualification (jobId, company, category, keyword) VALUES (?, ?, ?, ?)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, Integer.parseInt(request.getJobId()));
                        preparedStatement.setString(2, request.getCompany());
                        preparedStatement.setString(3, category);
                        preparedStatement.setString(4, keyword);
                        preparedStatement.execute();
                    }
                 }
            }

        } catch (SQLException e) {
            System.out.println("Exception while updating the job posting - " + e.getMessage());
        }

    return request;
    }

    public void requestUserDelete(String userId) {
        try {
            String query = "INSERT INTO AccountDeleteRequest (userId) VALUES (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception while inactivating user account - " + e.getMessage());
        }
    }

    public Employer getEmployerInfo(String userId){
        Employer employer = new Employer();
        try {
            String query = "SELECT * FROM User WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employer.setUserId(resultSet.getString("userId"));
                employer.setFirstName(resultSet.getString("firstName"));
                employer.setLastName(resultSet.getString("lastName"));
                employer.setEmail(resultSet.getString("email"));
                employer.setPhoneNumber(String.valueOf(resultSet.getLong("phoneNumber")));
                employer.setStatus(resultSet.getString("status"));
            }
            query = "SELECT * FROM Employer WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employer.setAddress1(resultSet.getString("address1"));
                employer.setAddress2(resultSet.getString("address2"));
                employer.setCity(resultSet.getString("city"));
                employer.setState(resultSet.getString("state"));
                employer.setZipCode(String.valueOf(resultSet.getInt("zipCode")));
                employer.setCompany(resultSet.getString("company"));
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving employer details: " + e.getMessage());
        }
        return employer;
    }

    public List<JobPosting> getAllJobs(String company) {
        List<JobPosting> jobPostings = new ArrayList<>();
        try {
            String query = "SELECT * FROM JobPosting WHERE company = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, company);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JobPosting jobPosting = new JobPosting();
                jobPosting.setJobId(String.valueOf(resultSet.getInt("jobId")));
                jobPosting.setCompany(resultSet.getString("company"));
                jobPosting.setPositionName(resultSet.getString("positionName"));
                jobPosting.setSupervisorFirstName(resultSet.getString("supervisorFirstName"));
                jobPosting.setSupervisorLastName(resultSet.getString("supervisorLastName"));
                jobPosting.setSupervisorEmail(resultSet.getString("supervisorEmail"));
                jobPosting.setSupervisorPhoneNumber(Long.toString(resultSet.getLong("supervisorPhoneNumber")));
                jobPosting.setStartDate(resultSet.getDate("startDate").toString());
                jobPosting.setEndDate(resultSet.getDate("endDate").toString());
                jobPosting.setStartTime(resultSet.getTime("startTime").toString());
                jobPosting.setEndTime(resultSet.getTime("endTime").toString());
                jobPosting.setPayPerHour(Double.toString(resultSet.getDouble("payPerHour")));
                jobPostings.add(jobPosting);
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving all job postings: " + e.getMessage());
        }
        return jobPostings;
    }

    public List<JobPosting> getAllJobs() {
        List<JobPosting> jobPostings = new ArrayList<>();
        try {
            String query = "SELECT * FROM JobPosting";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JobPosting jobPosting = new JobPosting();
                jobPosting.setJobId(String.valueOf(resultSet.getInt("jobId")));
                jobPosting.setCompany(resultSet.getString("company"));
                jobPosting.setPositionName(resultSet.getString("positionName"));
                jobPosting.setSupervisorFirstName(resultSet.getString("supervisorFirstName"));
                jobPosting.setSupervisorLastName(resultSet.getString("supervisorLastName"));
                jobPosting.setSupervisorEmail(resultSet.getString("supervisorEmail"));
                jobPosting.setSupervisorPhoneNumber(Long.toString(resultSet.getLong("supervisorPhoneNumber")));
                jobPosting.setStartDate(resultSet.getDate("startDate").toString());
                jobPosting.setEndDate(resultSet.getDate("endDate").toString());
                jobPosting.setStartTime(resultSet.getTime("startTime").toString());
                jobPosting.setEndTime(resultSet.getTime("endTime").toString());
                jobPosting.setPayPerHour(Double.toString(resultSet.getDouble("payPerHour")));
                jobPostings.add(jobPosting);
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving all job postings: " + e.getMessage());
        }
        return jobPostings;
    }

    public User getStaffInfo(String userId) {
        User user = new User();
        try {
            String query = "SELECT * FROM User WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(String.valueOf(resultSet.getLong("phoneNumber")));
                user.setStatus(resultSet.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving staff user: " + e.getMessage());
        }
        return user;
    }

    public void updateUser(String firstName, String lastName, String email, String phoneNumber, String userId) {
        try {
            String query = "UPDATE User SET firstName = ?, lastName = ?, email = ?, phoneNumber = ? WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setLong(4, Long.parseLong(phoneNumber));
            preparedStatement.setString(5, userId);
            preparedStatement.executeUpdate();
            System.out.println("User record has been updated successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while updating the user account - " + e.getMessage());
        }
    }

    public List<String> getUserId(String userType){
        List<String> userIds = new ArrayList<>();
        try {
            String query = "SELECT userId FROM User WHERE userType = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userType);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userIds.add(resultSet.getString("userId"));
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving employers: " + e.getMessage());
        }
        return userIds;
    }

    public List<Employer> getAllEmployers() {
        List<Employer> employers = new ArrayList<>();
        List<String> userIds = getUserId("E");
        for(String userId: userIds) {
            Employer employer = getEmployerInfo(userId);
            employers.add(employer);
        }
        return employers;
    }

    public List<Professional> getAllProfessionals() {
        List<Professional> professionals = new ArrayList<>();
        List<String> userIds = getUserId("P");
        for(String userId: userIds) {
            Professional professional = getProfessionalInfo(userId);
            professionals.add(professional);
        }
        return professionals;
    }

    public List<JobMatchingRequest> getJobMatchingRequests() {
        List<JobMatchingRequest> jobMatchingRequests = new ArrayList<>();
        try {
            String query = "SELECT userId FROM JobMatchingRequest";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JobMatchingRequest jobMatchingRequest = new JobMatchingRequest();
                jobMatchingRequest.setUserId(resultSet.getString("userId"));
                jobMatchingRequests.add(jobMatchingRequest);
            }
        } catch (SQLException e) {
            System.out.println("Exception while creating the employer account - " + e.getMessage());
        }
        return jobMatchingRequests;
    }

    public EmployerRequest approveCreateEmployerRequest(String userId) {
        EmployerRequest employerRequest = getCreateEmployerRequest(userId);
        String pwd = createEmployer(employerRequest);
        removeEmployerCreateRequest(userId);
        sendEmail(employerRequest.getEmail(), "Employer Registered Successfully", userId, pwd);
        return employerRequest;
    }

    public String createEmployer(EmployerRequest request) {
        String password = "";
        try {
            createUser(request.getUserId(), request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber(), "E");
            password = addCredentials(request.getUserId());
            String query = "INSERT INTO Employer (userId, address1, address2, city, state, zipCode, company) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, request.getUserId());
            preparedStatement.setString(2, request.getAddress1());
            preparedStatement.setString(3, request.getAddress2());
            preparedStatement.setString(4, request.getCity());
            preparedStatement.setString(5, request.getState());
            preparedStatement.setInt(6, Integer.parseInt(request.getZipCode()));
            preparedStatement.setString(7, request.getCompany());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Exception while creating the employer account - " + e.getMessage());
        }
        return password;
    }

    public String addCredentials(String userId) {
        String password = generateRandomString();
        try {
            String query = "INSERT INTO Credentials (userId, password) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Exception while adding credentials - " + e.getMessage());
        }
        return password;
    }

    public static String generateRandomString() {
        String regularChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialChars = "!@#$%^&*()_+{}[]|;:,.<>?";
        String numbers = "0123456789";
        StringBuilder sb = new StringBuilder();

        // Add one regular character
        sb.append(regularChars.charAt(new Random().nextInt(regularChars.length())));

        // Add one special character
        sb.append(specialChars.charAt(new Random().nextInt(specialChars.length())));

        // Add one number
        sb.append(numbers.charAt(new Random().nextInt(numbers.length())));

        // Add remaining characters randomly
        for (int i = 0; i < 5; i++) {
            String allChars = regularChars + specialChars + numbers;
            sb.append(allChars.charAt(new Random().nextInt(allChars.length())));
        }

        // Shuffle the generated string to make it random
        String randomString = sb.toString();
        char[] charArray = randomString.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            int randomIndex = new Random().nextInt(charArray.length);
            char temp = charArray[i];
            charArray[i] = charArray[randomIndex];
            charArray[randomIndex] = temp;
        }

        return new String(charArray);
    }

    public void createUser(String userId, String firstName, String lastName, String email, String phoneNumber, String userType) {
        try {
            String query = "INSERT INTO User (userId, firstName, lastName, email, phoneNumber, status, userType) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, email);
            preparedStatement.setLong(5, Long.parseLong(phoneNumber));
            preparedStatement.setString(6, "active");
            preparedStatement.setString(7, userType);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Exception while creating the employer account - " + e.getMessage());
        }
    }

    public List<EmployerRequest> getCreateEmployerRequests() {
        List<EmployerRequest> employerRequests = new ArrayList<>();
        try {
            String query = "SELECT * FROM EmployerAccountRequest";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EmployerRequest employerRequest = new EmployerRequest();
                employerRequest.setUserId(resultSet.getString("userId"));
                employerRequest.setFirstName(resultSet.getString("firstName"));
                employerRequest.setLastName(resultSet.getString("lastName"));
                employerRequest.setEmail(resultSet.getString("email"));
                employerRequest.setPhoneNumber(String.valueOf(resultSet.getLong("phoneNumber")));
                employerRequest.setAddress1(resultSet.getString("address1"));
                employerRequest.setAddress2(resultSet.getString("address2"));
                employerRequest.setCity(resultSet.getString("city"));
                employerRequest.setState(resultSet.getString("state"));
                employerRequest.setZipCode(String.valueOf(resultSet.getInt("zipCode")));
                employerRequest.setCompany(resultSet.getString("company"));
                employerRequests.add(employerRequest);
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving employer create requests: " + e.getMessage());
        }
        return employerRequests;
    }

    public List<ProfessionalRequest> getCreateProfessionalRequests() {
        List<ProfessionalRequest> professionalRequests = new ArrayList<>();
        try {
            String query = "SELECT * FROM ProfessionalAccountRequest";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProfessionalRequest professionalRequest = new ProfessionalRequest();
                professionalRequest.setUserId(resultSet.getString("userId"));
                professionalRequest.setFirstName(resultSet.getString("firstName"));
                professionalRequest.setLastName(resultSet.getString("lastName"));
                professionalRequest.setEmail(resultSet.getString("email"));
                professionalRequest.setPhoneNumber(String.valueOf(resultSet.getLong("phoneNumber")));
                professionalRequest.setAddress1(resultSet.getString("address1"));
                professionalRequest.setAddress2(resultSet.getString("address2"));
                professionalRequest.setCity(resultSet.getString("city"));
                professionalRequest.setState(resultSet.getString("state"));
                professionalRequest.setZipCode(String.valueOf(resultSet.getInt("zipCode")));
                professionalRequest.setUniversity(resultSet.getString("university"));
                professionalRequest.setGraduationDate(resultSet.getDate("graduationDate").toString());
                professionalRequest.setDegreeType(resultSet.getString("degreeType"));
                // Need to check if the qualification list is to be set or not
                professionalRequests.add(professionalRequest);
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving professional create requests: " + e.getMessage());
        }
        return professionalRequests;
    }

    public EmployerRequest getCreateEmployerRequest(String userId) {
        EmployerRequest employerRequest = new EmployerRequest();
        try {
            String query = "SELECT * FROM EmployerAccountRequest WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employerRequest.setUserId(resultSet.getString("userId"));
                employerRequest.setFirstName(resultSet.getString("firstName"));
                employerRequest.setLastName(resultSet.getString("lastName"));
                employerRequest.setEmail(resultSet.getString("email"));
                employerRequest.setPhoneNumber(String.valueOf(resultSet.getLong("phoneNumber")));
                employerRequest.setAddress1(resultSet.getString("address1"));
                employerRequest.setAddress2(resultSet.getString("address2"));
                employerRequest.setCity(resultSet.getString("city"));
                employerRequest.setState(resultSet.getString("state"));
                employerRequest.setZipCode(String.valueOf(resultSet.getInt("zipCode")));
                employerRequest.setCompany(resultSet.getString("company"));
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving employer create requests: " + e.getMessage());
        }
        return employerRequest;
    }

    public ProfessionalRequest getCreateProfessionalRequest(String userId) {
        ProfessionalRequest professionalRequest = new ProfessionalRequest();
        try {
            String query = "SELECT * FROM ProfessionalAccountRequest WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                professionalRequest.setUserId(resultSet.getString("userId"));
                professionalRequest.setFirstName(resultSet.getString("firstName"));
                professionalRequest.setLastName(resultSet.getString("lastName"));
                professionalRequest.setEmail(resultSet.getString("email"));
                professionalRequest.setPhoneNumber(String.valueOf(resultSet.getLong("phoneNumber")));
                professionalRequest.setAddress1(resultSet.getString("address1"));
                professionalRequest.setAddress2(resultSet.getString("address2"));
                professionalRequest.setCity(resultSet.getString("city"));
                professionalRequest.setState(resultSet.getString("state"));
                professionalRequest.setZipCode(String.valueOf(resultSet.getInt("zipCode")));
                professionalRequest.setUniversity(resultSet.getString("university"));
                professionalRequest.setGraduationDate(resultSet.getDate("graduationDate").toString());
                professionalRequest.setDegreeType(resultSet.getString("degreeType"));
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving professional create requests: " + e.getMessage());
        }
        return professionalRequest;
    }

    public void removeEmployerCreateRequest(String userId) {
        try {
            String query = "DELETE FROM EmployerAccountRequest WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
            System.out.println("Employer request has been removed successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while removing the employer request - " + e.getMessage());
        }
    }

    public ProfessionalRequest approveCreateProfessionalRequest(String userId) {
        ProfessionalRequest professionalRequest = getCreateProfessionalRequest(userId);
        String pwd = createProfessional(professionalRequest);
        removeProfessionalCreateRequest(userId);
        sendEmail(professionalRequest.getEmail(), "Professional Registered Successfully", userId, pwd);
        return professionalRequest;
    }

    public void removeProfessionalCreateRequest(String userId) {
        try {
            String query = "DELETE FROM ProfessionalAccountRequest WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
            System.out.println("Professional request has been removed successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while removing the professional request - " + e.getMessage());
        }
    }

    public String createProfessional(ProfessionalRequest request) {
        String pwd = "";
        try {
            createUser(request.getUserId(), request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber(), "P");
            pwd = addCredentials(request.getUserId());
            String query = "INSERT INTO Professional (userId, address1, address2, city, state, zipCode, university, graduationDate, degreeType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, request.getUserId());
            preparedStatement.setString(2, request.getAddress1());
            preparedStatement.setString(3, request.getAddress2());
            preparedStatement.setString(4, request.getCity());
            preparedStatement.setString(5, request.getState());
            preparedStatement.setInt(6, Integer.parseInt(request.getZipCode()));
            preparedStatement.setString(7, request.getUniversity());

            //String startDate = request.getGraduationDate();
            //SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            //java.util.Date date = sdf1.parse(startDate);
            //java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

            preparedStatement.setDate(8, java.sql.Date.valueOf(request.getGraduationDate()));
            preparedStatement.setString(9, request.getDegreeType());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Exception while creating the employer account - " + e.getMessage());
        }
        return pwd;
    }

    private void sendEmail(String emailId, String subject, String userId, String pwd) {
        try {
            final String username = "smu.hiring.app@gmail.com";
            final String password = "fupavceiljeibozr";
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", true);
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", 587);
            prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
            message.setSubject(subject);
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            String content = "Please find your login details below: \n" +
                    "username: " + username + "\n" + "password: " + password + "\n" +
                    "We have created a one time password. Please reset your password later.";
            mimeBodyPart.setContent(content, "text/html; charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException exception){
            System.out.println("Exception while sending email: "+ exception.getMessage());
        }

    }

}