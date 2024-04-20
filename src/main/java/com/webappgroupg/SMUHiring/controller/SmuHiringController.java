package com.webappgroupg.SMUHiring.controller;

import com.webappgroupg.SMUHiring.model1.Cred;
import com.webappgroupg.SMUHiring.model1.*;
import com.webappgroupg.SMUHiring.service.SmuHiringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class SmuHiringController {

    @Autowired
    private SmuHiringService smuHiringService;

    @PostMapping(value = "/registerProfessional", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerProfessional(@RequestBody ProfessionalRequest request){
        smuHiringService.registerProfessional(request);
    }

    @PostMapping(value = "/registerEmployer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerEmployer(@RequestBody EmployerRequest request){
        smuHiringService.registerEmployer(request);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object login(@RequestBody Cred cred){
        return smuHiringService.login(cred);
    }

    @PostMapping(value = "/updateProfessional", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Professional updateProfessional(@RequestBody Professional request){
        return smuHiringService.updateProfessional(request);
    }

    @PostMapping(value = "/updateEmployer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employer updateEmployer(@RequestBody Employer request){
        return smuHiringService.updateEmployer(request);
    }

    @PostMapping(value = "/initiateJobMatching/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void initiateJobMatching(@PathVariable("userId") String userId){
        smuHiringService.initiateJobMatching(userId);
    }

    @PostMapping(value = "/postJob", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postJob(@RequestBody JobPosting request){
        smuHiringService.postJob(request);
    }

    @PostMapping(value = "/makePayment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Payment makePayment(@RequestBody Payment payment){
        return smuHiringService.makePayment(payment);
    }

    @PostMapping(value = "/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void changePassword(@RequestBody Cred cred){
        smuHiringService.changePassword(cred);
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
