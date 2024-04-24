package com.webappgroupg.SMUHiring.dao;

public class Operations {

   /* public User login(String username, String password) {
        User user = null;
        StringBuilder query = new StringBuilder("SELECT * FROM User WHERE userId = ? AND password = ? LIMIT 1");

        try{
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int rsUid = resultSet.getInt("userId");
                String rsUserName = resultSet.getString("userName");
                String rsFirstName = resultSet.getString("firstName");
                String rsLastName = resultSet.getString("lastName");
                String rsEmail = resultSet.getString("email");
                int rsPhoneNumber = resultSet.getLong("phoneNumber");
                String rsStatus = resultSet.getString("status");
                String rsUserType = resultSet.getString("userType");

                user = new User(rsUid, rsUserName, rsFirstName, rsLastName, rsEmail, rsPhoneNumber, rsStatus, rsUserType);
            }
        } catch (SQLException e) {
            System.out.println("Exception while logging in - " + e.getMessage());
        }
        return user;
    }

    public void removeDeleteRequest(String userId) {
        try {
            String query = "DELETE FROM AccountDeleteRequest WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
            System.out.println("Delete request has been removed successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while removing the delete request - " + e.getMessage());
        }
    }

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
    public void makePayment(Payment payment) {
        try {
            String query = "INSERT INTO Payment (userId, paymentId, paymentAmount, dueDate, paymentDate) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, payment.getUserId());
            preparedStatement.setString(2, payment.getPaymentId());
            preparedStatement.setDouble(3, payment.getPaymentAmount());
            preparedStatement.setDate(4, java.sql.Date.valueOf(payment.getDueDate()));
            preparedStatement.setDate(5, java.sql.Date.valueOf(payment.getPaymentDate()));
            preparedStatement.execute();
            System.out.println("Payment made successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while making payment - " + e.getMessage());
        }
    }
    public void addCredentials(String userId) {
        String password = generateRandomString();
        try {
            String query = "INSERT INTO Credentials (userId, password) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
//            System.out.println("Credentials added successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while adding credentials - " + e.getMessage());
        }
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
    public void changePassword(String userId, String newPassword){
        try {
            String query = "UPDATE Credentials SET password = ? WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, userId);
            preparedStatement.executeUpdate();
//            System.out.println("Password has been changed successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while changing the password - " + e.getMessage());
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
//            System.out.println("Employer record created successfully.");
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
//            System.out.println("Employer record created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the employer account - " + e.getMessage());
        }
    }
    public void createEmployerAccount(Employer employer) {
        try {
            String query = "INSERT INTO Employer (userId, address1, address2, city, state, zipCode, company) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employer.getUserId());
            preparedStatement.setString(2, employer.getAddress1());
            preparedStatement.setString(3, employer.getAddress2());
            preparedStatement.setString(4, employer.getCity());
            preparedStatement.setString(5, employer.getState());
            preparedStatement.setInt(6, employer.getZipCode());
            preparedStatement.setString(7, employer.getCompany());
            preparedStatement.execute();
//            System.out.println("Employer record created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the employer account - " + e.getMessage());
        }
    }
    public void createProfessionalAccount(Professional professional) {
        try {
            String query = "INSERT INTO Professional (userId, address1, address2, city, state, zipCode, university, graduationDate, degreeType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professional.getUserId());
            preparedStatement.setString(2, professional.getAddress1());
            preparedStatement.setString(3, professional.getAddress2());
            preparedStatement.setString(4, professional.getCity());
            preparedStatement.setString(5, professional.getState());
            preparedStatement.setInt(6, professional.getZipCode());
            preparedStatement.setString(7, professional.getUniversity());
            preparedStatement.setString(8, professional.getGraduationDate());
            preparedStatement.setString(9, professional.getDegreeType());
            preparedStatement.execute();
//            System.out.println("Professional record created successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while creating the professional account - " + e.getMessage());
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
//            System.out.println("Professional record created successfully.");
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
//            System.out.println("Professional record created successfully.");
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
            String query = "SELECT category, keyword FROM ProfessionalQualification WHERE userId = ?";
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
//            System.out.println("Job posting created successfully.");
        } catch (SQLException e) {
            System.out.println("SQL exception while creating the job posting - " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument exception while creating the job posting - " + e.getMessage());
        }
    }
    public void createJobQualification(Integer jobId, String company, String category, String keyword) {
        try {
            String query = "INSERT INTO JobQualification (jobId, company, category, keyword) VALUES (?, ?, ?, ?)";
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
    public void deleteJobQualification(Integer jobId, String company, String category, String keyword) {
        try {
            String query = "DELETE FROM JobQualification WHERE jobId = ? AND company = ? AND category = ? AND keyword = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, jobId);
            preparedStatement.setString(2, company);
            preparedStatement.setString(3, category);
            preparedStatement.setString(4, keyword);
            preparedStatement.executeUpdate();
            System.out.println("Job qualification deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while deleting the job qualification - " + e.getMessage());
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
//            System.out.println("Job matching created successfully.");
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
            String query = "DELETE FROM AccountDeleteRequest WHERE userId = ?";
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
            String query = "INSERT AccountDeleteRequest (userId, requestType) VALUES(?, ?)";
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
    public ResultSet getJobsWithCategory(String category) {
        try {
            String query = "SELECT jobId, company, keyword FROM JobQualification WHERE category = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, category);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Exception while retrieving job qualifications: " + e.getMessage());
        }
        return null;
    }
    public void initiateJobMatching(String userId) {
        int jobMatchesCount = 0;
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
//                        System.out.println("Match found for jobId: " + jobId + " and company: " + company);
                        createJobMatching(userId, jobId, company);
                        jobMatchesCount++;
                    }
                }
            } catch (SQLException e) {
                // No more jobs
            }
        }
        if (jobMatchesCount == 0) {
            System.out.println("No job matches found for the professional " + userId + ".");
        }
    }

    public ArrayList<JobMatching> getJobMatches(String userId) {
        ArrayList<JobMatching> jobMatchings = new ArrayList<JobMatching>();


        try {
            String query = "SELECT * FROM JobMatching WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                JobMatching tempJobMatching = new JobMatching(userId);
                tempJobMatching.setJobId(resultSet.getString("jobId"));
                tempJobMatching.setCompany(resultSet.getString("company"));
                jobMatchings.add(tempJobMatching);
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving job matching request: " + e.getMessage());
        }
        return jobMatchings;
    }

    // Root
    public void createUserAccount(User user) {
        try {
            String query = "INSERT INTO User (userId, firstName, lastName, email, phoneNumber, status, userType) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setLong(5, user.getPhoneNumber());
            preparedStatement.setString(6, "active");
            preparedStatement.setString(7, String.valueOf(user.getUserType()));
            preparedStatement.execute();
//            System.out.println("User record created successfully.");
            addCredentials(user.getUserId());
        } catch (SQLException e) {
            System.out.println("Exception while creating the user account - " + e.getMessage());
        }
    }

    public void createUserAccount(Staff user) {
        try {
            String query = "INSERT INTO User (userId, firstName, lastName, email, phoneNumber, status, userType) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setLong(5, user.getPhoneNumber());
            preparedStatement.setString(6, "active");
            preparedStatement.setString(7, "S");
            preparedStatement.execute();
//            System.out.println("User record created successfully.");
            addCredentials(user.getUserId());
        } catch (SQLException e) {
            System.out.println("Exception while creating the user account - " + e.getMessage());
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

    public void removeEmployerCreateRequest(String userId) {
        try {
            String query = "DELETE FROM EmployerCreateRequest WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
            System.out.println("Employer request has been removed successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while removing the employer request - " + e.getMessage());
        }
    }
    public void removeProfessionalCreateRequest(String userId) {
        try {
            String query = "DELETE FROM ProfessionalCreateRequest WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
            System.out.println("Professional request has been removed successfully.");
        } catch (SQLException e) {
            System.out.println("Exception while removing the professional request - " + e.getMessage());
        }
    }

    public Employer getEmployerCreateRequest(String userId) {
        Employer employer = new Employer(userId);

        try {
            String query = "SELECT * FROM EmployerCreateRequest WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employer.setFirstName(resultSet.getString("firstName"));
                employer.setLastName(resultSet.getString("lastName"));
                employer.setEmail(resultSet.getString("email"));
                employer.setPhoneNumber(resultSet.getLong("phoneNumber"));
                employer.setAddress1(resultSet.getString("address1"));
                employer.setAddress2(resultSet.getString("address2"));
                employer.setCity(resultSet.getString("city"));
                employer.setState(resultSet.getString("state"));
                employer.setZipCode(resultSet.getInt("zipCode"));
                employer.setCompany(resultSet.getString("company"));
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving employer create request: " + e.getMessage());
        }
        return employer;
    }

    public Professional getProfessionalCreateRequest(String userId) {
        Professional professional = new Professional(userId);

        try {
            String query = "SELECT * FROM ProfessionalCreateRequest WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                professional.setFirstName(resultSet.getString("firstName"));
                professional.setLastName(resultSet.getString("lastName"));
                professional.setEmail(resultSet.getString("email"));
                professional.setPhoneNumber(resultSet.getLong("phoneNumber"));
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
            System.out.println("Exception while retrieving professional create request: " + e.getMessage());
        }
        return professional;
    }

    public Staff getStaffUser(String userId){
        Staff staffUser = new Staff(userId);
        try {
            String query = "SELECT * FROM User WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                long phoneNumber = resultSet.getLong("phoneNumber");
                String status = resultSet.getString("status");
                staffUser = new Staff(userId, firstName, lastName, email, phoneNumber, status);
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving staff user: " + e.getMessage());
        }
        return staffUser;
    }



    public ArrayList<JobPosting> getJobPostings(String company) {
        ArrayList<JobPosting> jobPostings = new ArrayList<JobPosting>();

        try {
            String query = "SELECT * FROM JobPosting WHERE company = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, company);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                JobPosting tempJobPosting = new JobPosting(company);
                tempJobPosting.setJobId(resultSet.getInt("jobId"));
                tempJobPosting.setCompany(resultSet.getString("company"));
                tempJobPosting.setPositionName(resultSet.getString("positionName"));
                tempJobPosting.setSupervisorName(resultSet.getString("supervisorName"));
                tempJobPosting.setSupervisorEmail(resultSet.getString("supervisorEmail"));
                tempJobPosting.setStartDate(resultSet.getDate("startDate").toString());
                tempJobPosting.setEndDate(resultSet.getDate("endDate").toString());
                tempJobPosting.setStartTime(resultSet.getTime("startTime").toString());
                tempJobPosting.setEndTime(resultSet.getTime("endTime").toString());
                tempJobPosting.setPayPerHour(resultSet.getDouble("payPerHour"));
                jobPostings.add(tempJobPosting);
//                System.out.println(tempJobPosting.getJobId());
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving job postings: " + e.getMessage());
        }
//        System.out.println(jobPostings);
        return jobPostings;
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
                payments.add(tempPayment);
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving payments: " + e.getMessage());
        }
        return payments;
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

    public String getEmployerCompany(String userId) {
        String company = "";
        try {
            String query = "SELECT company FROM Employer WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                company = resultSet.getString("company");
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving employer: " + e.getMessage());
        }
        return company;
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
        } catch (SQLException e) {
            System.out.println("Exception while updating the employer account - " + e.getMessage());
        }
        try {
            String query = "UPDATE User SET firstName = ?, lastName = ?, email = ?, phoneNumber = ? WHERE userId = ?";
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

    public ArrayList<Employer> getEmployers() {
        ArrayList<Employer> employers = new ArrayList<Employer>();

        // Get all users that are Employers
        try {
            String query = "SELECT userId FROM User WHERE userType = 'E'";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String userId = resultSet.getString("userId");
                // Get the Employer object with all details
                Employer tempEmployer = getEmployerDetails(userId);

                employers.add(tempEmployer);
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving employers: " + e.getMessage());
        }
        return employers;
    }
    public Employer getEmployerDetails(String userId){
        // Returns an Employer object from the userId
        Employer employer = new Employer(userId);
        try {
            String query = "SELECT * FROM User WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
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

    public ArrayList<Professional> getProfessionals() {
        ArrayList<Professional> professionals = new ArrayList<Professional>();

        // Get all users that are Professionals
        try {
            String query = "SELECT userId FROM User WHERE userType = 'P'";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String userId = resultSet.getString("userId");
                // Get the Professional object with all details
                Professional tempProfessional = getProfessionalDetails(userId);

                professionals.add(tempProfessional);
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving professionals: " + e.getMessage());
        }
        return professionals;
    }
    public Professional getProfessionalDetails(String userId){
        // Returns a Professional object from the userId
        Professional professional = new Professional(userId);
        try {
            String query = "SELECT * FROM User WHERE userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                professional.setFirstName(resultSet.getString("firstName"));
                professional.setLastName(resultSet.getString("lastName"));
                professional.setEmail(resultSet.getString("email"));
                professional.setPhoneNumber(resultSet.getLong("phoneNumber"));
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving professional details: " + e.getMessage());
        }
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

    public ArrayList<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<User>();

        try {
            String query = "SELECT * FROM User";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String userId = resultSet.getString("userId");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                long phoneNumber = resultSet.getLong("phoneNumber");
                String status = resultSet.getString("status");
                String userType = resultSet.getString("userType");

                User tempUser = new User(userId, firstName, lastName, email, phoneNumber, status, userType.charAt(0));
                users.add(tempUser);
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving all users: " + e.getMessage());
        }
        return users;
    }
    public ArrayList<Employer> getEmployerCreateRequests(){
        ArrayList<Employer> employerRequests = new ArrayList<Employer>();

        try {
            String query = "SELECT userId FROM EmployerCreateRequest";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String userId = resultSet.getString("userId");
                // Get the Employer object with all details
                Employer tempEmployer = getEmployerCreateRequest(userId);

                employerRequests.add(tempEmployer);
            }
        } catch (SQLException e) {
            System.out.println("Exception while retrieving employer create requests: " + e.getMessage());
        }
        return employerRequests;

    }*/


    //String startDate = request.getGraduationDate();
    //SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    //java.util.Date date = sdf1.parse(startDate);
    //java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

}
