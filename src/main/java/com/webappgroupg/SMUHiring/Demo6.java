package com.webappgroupg.SMUHiring;

import com.webappgroupg.SMUHiring.model.Employer;
import com.webappgroupg.SMUHiring.model.JobMatching;
import com.webappgroupg.SMUHiring.model.JobPosting;
import com.webappgroupg.SMUHiring.model.Payment;
import com.webappgroupg.SMUHiring.service.EmployerOperations;
import com.webappgroupg.SMUHiring.service.RootOperations;
import com.webappgroupg.SMUHiring.service.StaffOperations;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Demo6 {
    public static void main(String[] args) throws MessagingException {
        RootOperations rootOps = new RootOperations();
        StaffOperations staffOps = new StaffOperations("demoStaff");
        EmployerOperations newEmployerOps = new EmployerOperations("demoEmployer");
        EmployerOperations existingEmployerOps = new EmployerOperations("emp9");


        // Sending email
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
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("diogo.fae2001@gmail.com"));
        message.setSubject("Test");

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent("Hello World!!", "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);



//        Email email = EmailBuilder.startingBlank()
//                .from("From", username)
//                .to("1 st Receiver", "diogo.fae2001@gmail.com")
//                .to("2 nd Receiver", "drodrigues@smu.edu")
//                .withSubject("Email Subject")
//                .withPlainText("Email Body")
//                .buildEmail();
//
//        Mailer mailer = MailerBuilder
//                .withSMTPServer("smtp.mailtrap.io", 2525, username, password)
//                .withTransportStrategy(TransportStrategy.SMTPS)
//                .buildMailer();
//
//        mailer.sendMail(email);



        // 1. New professional registers with the agency. Request is saved and can be viewed by staff. Show no username can repeat.

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


        // 3. Professional updates own profile. Show changes are saved.

        // 4. Employer updates own profile. Show changes are saved.
//        System.out.println(existingEmployerOps);
//        existingEmployerOps.editAccount("Zoe", "Green", "zoe.green@gamil.com", 7778889999L, "active", "123 Demo St", "#1604", "Miami", "FL", 91829, "Acme Corporation");
//        System.out.println(existingEmployerOps);

        // 5. Professional requests job matching. Show it can be viewed by staff.

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

        // 8. Employer pays for the account.
//        ArrayList<Payment> payments = existingEmployerOps.seePayments();
//        for (Payment payment : payments) {
//            System.out.print(payment + " ");
//        }
//        System.out.println("\n--------------------");
//        existingEmployerOps.makePayment("payment1", 100.00, "2021-07-01", "2021-07-31");
//        payments = existingEmployerOps.seePayments();
//        for (Payment payment : payments) {
//            System.out.print(payment + " ");
//        }
//        System.out.println("\n--------------------");

        // 9. Professional changes password. Show new password.

        // 10. Employer changes password. Show new password.
//        System.out.println(existingEmployerOps.getPassword());
//        existingEmployerOps.changePassword("newPassword");
//        System.out.println(existingEmployerOps.getPassword());

        // 11. Professional requests account deletion. Show it can be viewed by staff.

        // 12. Employer requests account deletion. Show it can be viewed by staff.
//        EmployerOperations employerToDelete = new EmployerOperations("emp2");
//        System.out.println(employerToDelete.getEmployerUser().getStatus());
//        ArrayList<String> employerRequests = staffOps.getDeleteRequests();
//        System.out.println("Delete requests:");
//        for (String request : employerRequests) {
//            System.out.print(request + " ");
//        }
//        System.out.println("\n--------------------");
//        staffOps.reviewDeleteRequest("emp2", "approve");
//        employerToDelete = new EmployerOperations("emp2");
//        System.out.println(employerToDelete.getEmployerUser().getStatus());
//        System.out.println("Delete requests:");
//        employerRequests = staffOps.getDeleteRequests();
//        for (String request : employerRequests) {
//            System.out.print(request + " ");
//        }

        // 13. Staff changes password. Show new password.
//        StaffOperations staffOpsPassword = new StaffOperations("staff1");
//        System.out.println(staffOpsPassword.getPassword());
//        staffOpsPassword.changePassword("newPassword");
//        staffOpsPassword = new StaffOperations("staff1");
//        System.out.println(staffOpsPassword.getPassword());

        // 14. Staff reviews Professional account creation request. Show account is created and request is cleared.

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
