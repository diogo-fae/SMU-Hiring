package com.webappgroupg.SMUHiring.controller;

import com.webappgroupg.SMUHiring.model1.Cred;
import com.webappgroupg.SMUHiring.model1.*;
import com.webappgroupg.SMUHiring.service.SmuHiringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SmuHiringController {

    @Autowired
    private SmuHiringService smuHiringService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object login(@RequestBody Cred cred){
        return smuHiringService.login(cred);
    }

    @PostMapping(value = "/registerProfessional", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerProfessional(@RequestBody ProfessionalRequest request){
        smuHiringService.registerProfessional(request);
    }

    @PostMapping(value = "/registerEmployer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerEmployer(@RequestBody EmployerRequest request){
        smuHiringService.registerEmployer(request);
    }

    @PostMapping(value = "/postJob", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postJob(@RequestBody JobPosting request){
        smuHiringService.postJob(request);
    }

    @PostMapping(value = "/updateJob", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JobPosting updateJob(@RequestBody JobPosting request){
        return smuHiringService.updateJob(request);
    }

    @PostMapping(value = "/deleteJob", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteJob(@RequestBody JobPosting request){
        smuHiringService.deleteJob(request);
    }

    @PostMapping(value = "/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void changePassword(@RequestBody Cred cred){
        smuHiringService.changePassword(cred);
    }

    @PostMapping(value = "/requestEmployerDelete/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void requestEmployerDelete(@PathVariable("userId") String userId){
        smuHiringService.requestUserDelete(userId);
    }

    @GetMapping(value = "/getEmployerInfo/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employer getEmployerInfo(@PathVariable("userId") String userId) {
        return smuHiringService.getEmployerInfo(userId);
    }

    @GetMapping(value = "/getJobInfo/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void getJobInfo(@PathVariable("userId") String userId) {
        //return smuHiringService.getJobInfo(userId); //Need to check if this is needed for employer
    }

    @PostMapping(value = "/updateEmployer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employer updateEmployer(@RequestBody Employer request){
        return smuHiringService.updateEmployer(request);
    }

    @GetMapping(value = "/getAllJobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobPosting> getAllJobs() {
        return smuHiringService.getAllJobs(); // Do we need job qualifications as well to be displayed here?
    }

    @PostMapping(value = "/requestProfessionalDelete/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void requestProfessionalDelete(@PathVariable("userId") String userId){
        smuHiringService.requestUserDelete(userId);
    }

    @GetMapping(value = "/getProfessionalInfo/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Professional getProfessionalInfo(@PathVariable("userId") String userId) {
        return smuHiringService.getProfessionalInfo(userId);
    }

    @PostMapping(value = "/updateProfessional", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Professional updateProfessional(@RequestBody Professional request){
        return smuHiringService.updateProfessional(request);
    }


    //getAllJobs
    //getJobInfo
    //requestJobMatching


    @PostMapping(value = "/requestJobMatching/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void initiateJobMatching(@PathVariable("userId") String userId){
        smuHiringService.initiateJobMatching(userId);
    }



    @PostMapping(value = "/makePayment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Payment makePayment(@RequestBody Payment payment){
        return smuHiringService.makePayment(payment);
    }



    @PostMapping(value = "/deleteAccount/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAccount(@PathVariable("userId") String userId){
        smuHiringService.deleteAccount(userId);
    }

    @PostMapping(value = "/createProfessional", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProfessional(@RequestBody ProfessionalRequest request){
        smuHiringService.createProfessional(request);
    }

    @PostMapping(value = "/createEmployer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createEmployer(@RequestBody EmployerRequest request){
        smuHiringService.createEmployer(request);
    }

    @GetMapping(value = "/getNumberOfRequests/{userType}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getNumberOfRequests(@PathVariable("userType") String userType){
       return smuHiringService.getNumberOfRequests(userType);
    }

    @PostMapping(value = "/approveAccountDeletion/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean approveAccountDeletion(@PathVariable("userId") String userId){
        return smuHiringService.approveAccountDeletion(userId);
    }

    @PostMapping(value = "/createStaff", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createStaff(@RequestBody User request){
        smuHiringService.createStaff(request);
    }

}
