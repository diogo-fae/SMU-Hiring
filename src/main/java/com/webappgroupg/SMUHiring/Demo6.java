package com.webappgroupg.SMUHiring;

import com.webappgroupg.SMUHiring.model.Professional;
import com.webappgroupg.SMUHiring.service.EmployerOperations;
import com.webappgroupg.SMUHiring.service.ProfessionalOperations;
import com.webappgroupg.SMUHiring.service.StaffOperations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Demo6 {
    public static void main(String[] args) throws ParseException {
        //RootOperations rootOps = new RootOperations();
       // StaffOperations staffOps = new StaffOperations("demoStaff");
        //EmployerOperations newEmployerOps = new EmployerOperations("demoEmployer");
       // EmployerOperations existingEmployerOps = new EmployerOperations("emp9");
        ProfessionalOperations professionalOperations = new ProfessionalOperations();

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        //1. A new professional registers with the agency. The demo must show that the request is saved and can be viewed by a staff member. Make sure to demonstrate the uniqueness of preferred username.

        /*Professional professional = professionalOperations.getProfessionals("prof4");
        if(StringUtils.isNotEmpty(professional.getUserId())){
            System.out.print("Username already exists");
        } else {
            String grad_date = "2023-05-30";
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date gradDate = formatter.parse(grad_date);
            java.sql.Date sqlDate = new java.sql.Date(gradDate.getTime());
            professionalOperations.createProfessionalAccount("prof4","f4","l4","prof4@test.com","0987654321", 'P',"012 Pine St","Apt B2","New York","NY","10014","EFG University",sqlDate, "Bachelor");
            System.out.print("New Professional Registered");
        }*/



        // 2. New employer registers with the agency. Request is saved and can be viewed by staff. Show no username can repeat.
//        ArrayList<Employer> employerRequests = staffOps.viewEmployerRequests();
//        for (Employer employer : employerRequests) {
//            System.out.print(employer.getUserId() + " ");
//        }
//        System.out.println("\n--------------------");
//        newEmployerOps.requestNewAccount("John", "Glass", "jg@gmail.com", 1234567890, "inactive", "123 Main St", "", "Dallas", "TX", 75001, "Glass Co.");
//        employerRequests = staffOps.viewEmployerRequests();
//        for (Employer employer : employerRequests) {
//            System.out.print(employer.getUserId() + " ");
//        }


        // 3. A professional modifies his/her profile. The demo must show that the modified profile is saved and is viewed again from the interface when retrieved. Make sure to show that fields such as username should not be modified.
        Professional p = professionalOperations.getProfessionals("prof1");
        if(null != p.getUserId()){
            String grad_date = "2025-05-05";
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date gradDate = formatter.parse(grad_date);
            java.sql.Date sqlDate = new java.sql.Date(gradDate.getTime());
            professionalOperations.updateProfessionalAccount("prof1", "Green", "Zoe", "test@test.com", "7778889999", "active", "123 Demo St", "city", "MA", "90001", "SMU", sqlDate, "graduate");
            System.out.print("Professional updated successfully");
        } else {
            System.out.print("Professional does not exist");
        }

        // 4. Employer updates own profile. Show changes are saved.
//        System.out.println(existingEmployerOps);
//        existingEmployerOps.editAccount("Zoe", "Green", "zoe.green@gamil.com", 7778889999L, "active", "123 Demo St", "#1604", "Miami", "FL", 91829, "Acme Corporation");
//        System.out.println(existingEmployerOps);

        // 5. Professional requests job matching. Show it can be viewed by staff.
        //professionalOperations.createJobMatchingRequest("prof3");
        //System.out.println(professionalOperations.viewJobMatchingRequest().toString());

        // 6. Employer posts a job. Show job is saved and can be viewed by Employer. Show no jobId and company combination can repeat.
//        ArrayList<JobPosting> acmeJobs = existingEmployerOps.getJobPostings();
//        for (JobPosting job : acmeJobs) {
//            System.out.print(job + " ");
//        }
//        System.out.println("\n--------------------");
//        existingEmployerOps.createJobPosting(1, "Software Engineer", "John Doe", "j@gmail.com", "2021-08-01", "2021-12-31", "08:00:00", "17:00:00", 50.00);
//        existingEmployerOps.createJobPosting(2, "Software Engineer", "John Doe", "j@gmail.com", "2021-08-01", "2021-12-31", "08:00:00", "17:00:00", 50.00);
//        existingEmployerOps.createJobPosting(2, "Software Engineer", "Jack Black", "j@gmail.com", "2021-08-01", "2021-12-31", "08:00:00", "17:00:00", 50.00);
//        acmeJobs = existingEmployerOps.getJobPostings();
//        for (JobPosting job : acmeJobs) {
//            System.out.println(job + " ");
//        }
//        System.out.println("\n--------------------");

        // 7. Professional pays for the account.
        /*ArrayList<Payment> payments = professionalOperations.getPayments("prof3");
        if(!CollectionUtils.isEmpty(payments)){
            for(Payment payment: payments){
                double dueAmount = payment.getPaymentAmount();
                double inputAmount = 20.00;
                professionalOperations.updatePaymentAmount("prof3", (dueAmount-inputAmount));
            }
        } else {
            professionalOperations.makePayment("prof3", "payment1", 100.00, "2024-07-01", "2024-04-11");
        }
        payments = professionalOperations.getPayments("prof3");
        for (Payment payment : payments) {
            System.out.print(payment + " ");
        }
        System.out.println("\n--------------------"); */


        // 8. Employer pays for the account.
        /*ArrayList<Payment> payments = existingEmployerOps.seePayments();
        if(!CollectionUtils.isEmpty(payments)){
            for(Payment payment: payments){
                double dueAmount = payment.getPaymentAmount();
                double inputAmount = 20.00;
                professionalOperations.updatePaymentAmount("emp9", (dueAmount-inputAmount));
            }
        } else {
            existingEmployerOps.makePayment("payment1", 100.00, "2021-07-01", "2021-07-31");
        }
        payments = existingEmployerOps.seePayments();
        for (Payment payment : payments) {
            System.out.print(payment + " ");
        }
        System.out.println("\n--------------------"); */

        // 9. Professional changes password. Show new password.
          /*if(StringUtils.isNotEmpty(professionalOperations.getPassword("prof1"))){
              professionalOperations.updatePassword("prof1","password1");
              if("password1".equalsIgnoreCase(professionalOperations.getPassword("prof1"))){
                  Professional professional = professionalOperations.getProfessionals("prof1");
                  System.out.print("Professional Details "+ professional.toString());
              } else {
                  System.out.print("Invalid Password");
              }
          } else {
              System.out.print("User does not exist");
          } */

//        existingEmployerOps.changePassword("newPassword");
//        System.out.println(existingEmployerOps.getPassword());

        // 10. Employer changes password. Show new password.
//        System.out.println(existingEmployerOps.getPassword());
//        existingEmployerOps.changePassword("newPassword");
//        System.out.println(existingEmployerOps.getPassword());

        // 11. Professional requests account deletion. Show it can be viewed by staff.

        /*Professional professional = professionalOperations.getProfessionals("prof1");
        if(StringUtils.isNotEmpty(professional.getUserId())){
            ArrayList<String> requests = professionalOperations.getDeleteRequests();
            professionalOperations.reviewDeleteRequest("prof1", "approve");
        } else {
            System.out.print("Professional Account does not exist");
        }
*/

         //12. Employer requests account deletion. Show it can be viewed by staff.
        /*EmployerOperations employerToDelete = new EmployerOperations("emp2");
        System.out.println(employerToDelete.getEmployerUser().getStatus());
        ArrayList<String> employerRequests = staffOps.getDeleteRequests();
        System.out.println("Delete requests:");
        for (String request : employerRequests) {
            System.out.print(request + " ");
        }
        System.out.println("\n--------------------");
        staffOps.reviewDeleteRequest("emp2", "approve");
        employerToDelete = new EmployerOperations("emp2");
        System.out.println(employerToDelete.getEmployerUser().getStatus());
        System.out.println("Delete requests:");
        employerRequests = staffOps.getDeleteRequests();
        for (String request : employerRequests) {
            System.out.print(request + " ");
        }*/

        // 13. Staff changes password. Show new password.
//        StaffOperations staffOpsPassword = new StaffOperations("staff1");
//        System.out.println(staffOpsPassword.getPassword());
//        staffOpsPassword.changePassword("newPassword");
//        staffOpsPassword = new StaffOperations("staff1");
//        System.out.println(staffOpsPassword.getPassword());

        // 14. Staff reviews Professional account creation request. Show account is created and request is cleared.
        /*List<Professional> professionals = staffOps.viewProfessionals();
        System.out.println("Professional:");
        for (Professional professional : professionals) {
            System.out.print(professional.getUserId() + " ");
        }
        System.out.println("\n--------------------");
        staffOps.reviewProfessionalCreateRequest("approve", "profX");
        professionals = staffOps.viewProfessionals();
        System.out.println("Professional:");
        for (Professional professional : professionals) {
            System.out.print(professional.getUserId() + " ");
        }
        System.out.println("\n--------------------"); */


        // 15. Staff reviews Employer account creation request. Show account is created and request is cleared.
//        List<Employer> employers = staffOps.viewEmployers();
//        System.out.println("Employers:");
//        for (Employer employer : employers) {
//            System.out.print(employer.getUserId() + " ");
//        }
//        System.out.println("\n--------------------");
//        staffOps.reviewEmployerCreateRequest("approve", "empX");
//        employers = staffOps.viewEmployers();
//        System.out.println("Employers:");
//        for (Employer employer : employers) {
//            System.out.print(employer.getUserId() + " ");
//        }
//        System.out.println("\n--------------------");

        // 16. Staff reviews Professional account deletion request. Show account is deactivated and request is cleared.

        /*ArrayList<String> deleteRequests = staffOps.getDeleteRequests();
        staffOps.reviewDeleteRequest("prof2", "approve");
        deleteRequests = staffOps.getDeleteRequests();
        for (String request : deleteRequests) {
            System.out.print(request + " ");
        }*/

        // 17. Staff reviews Employer account deletion request. Show account is deactivated and request is cleared.
//        ArrayList<String> deleteRequests = staffOps.getDeleteRequests();
//        EmployerOperations emp3 = new EmployerOperations("emp3");
//        System.out.println("Status: " + emp3.getEmployerUser().getStatus());
//        System.out.println("Delete requests:");
//        for (String request : deleteRequests) {
//            System.out.print(request + " ");
//        }
//        System.out.println("\n--------------------");
//        staffOps.reviewDeleteRequest("emp3", "approve");
//        deleteRequests = staffOps.getDeleteRequests();
//        emp3 = new EmployerOperations("emp3");
//        System.out.println("Status: " + emp3.getEmployerUser().getStatus());
//        System.out.println("Delete requests:");
//        for (String request : deleteRequests) {
//            System.out.print(request + " ");
//        }

        // 18. Staff initiates job matching for a professional. Show matched jobs.
//        ArrayList<JobMatching> jobMatches = staffOps.getJobMatches("prof1");
//        for (JobMatching job : jobMatches) {
//            System.out.println(job);
//        }
//        System.out.println("\n--------------------");
//        staffOps.initiateJobMatching("prof1");
//        jobMatches = staffOps.getJobMatches("prof1");
//        for (JobMatching job : jobMatches) {
//            System.out.println(job);
//        }

        // 19. Root creates new staff account. Show new staff account.
//        rootOps.printUserIds();
//        rootOps.createStaff("demoStaff", "demoStaff", "demoStaff", "demo@gmail.com", 1234567890);
//        rootOps.printUserIds();
    }
}
