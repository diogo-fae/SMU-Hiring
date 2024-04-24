package com.webappgroupg.SMUHiring.controller;

import com.webappgroupg.SMUHiring.model.Cred;
import com.webappgroupg.SMUHiring.model.*;
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
    public User login(@RequestBody Cred cred){
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

    @GetMapping(value = "/getJobInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public JobPosting getJobInfo(@RequestParam("jobId") int jobId, @RequestParam("company") String company) {
//        System.out.println("Received user id: " + jobId + " and company: " + company);
        // Assuming smuHiringService.getJobInfo(jobId, company) retrieves job information based on jobId and company
        return smuHiringService.getJobInfo(jobId, company);
    }

    @PostMapping(value = "/updateEmployer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employer updateEmployer(@RequestBody Employer request){
        System.out.println("Received user id: " + request.getUserId());
        return smuHiringService.updateEmployer(request);
    }

    @GetMapping(value = "/getAllJobs/{company}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobPosting> getAllJobs(@PathVariable("company") String company) { //Employer
        return smuHiringService.getAllJobs(company); // Do we need job qualifications as well to be displayed here?
    }

    @GetMapping(value = "/getAllJobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobPosting> getAllJobs() { //professional
        return smuHiringService.getAllJobsForProfessional();
    }

    @PostMapping(value = "/requestProfessionalDelete/{userId}")
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

    @PostMapping(value = "/requestJobMatching/{userId}")
    public void initiateJobMatching(@PathVariable("userId") String userId){
        smuHiringService.initiateJobMatching(userId);
    }

    @GetMapping(value = "/getStaffInfo/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getStaffInfo(@PathVariable("userId") String userId) {
        return smuHiringService.getStaffInfo(userId);
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

    @PostMapping(value = "/approveAccountDeletion/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean approveAccountDeletion(@PathVariable("userId") String userId){
        return smuHiringService.approveAccountDeletion(userId);
    }

    @PostMapping(value = "/createStaff", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createStaff(@RequestBody User request){
        smuHiringService.createStaff(request);
    }

    @PostMapping(value = "/updateStaff", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStaff(@RequestBody User request){
        smuHiringService.updateStaff(request);
    }

    @GetMapping(value = "/getAllEmployers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employer> getAllEmployers() {
        return smuHiringService.getAllEmployers();
    }

    @GetMapping(value = "/getAllProfessionals", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Professional> getAllProfessionals() {
        return smuHiringService.getAllProfessionals();
    }

    @GetMapping(value = "/getJobMatchingRequests", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobMatchingRequest> getJobMatchingRequests() {
        return smuHiringService.getJobMatchingRequests();
    }

    @PostMapping(value = "/approveCreateEmployerRequest/{userId}")
    public EmployerRequest approveCreateEmployerRequest(@PathVariable("userId") String userId){
        return smuHiringService.approveCreateEmployerRequest(userId);
    }

    @PostMapping(value = "/approveCreateProfessionalRequest/{userId}")
    public void approveCreateProfessionalRequest(@PathVariable("userId") String userId){
        smuHiringService.approveCreateProfessionalRequest(userId);
    }

    @GetMapping(value = "/getCreateEmployerRequests", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployerRequest> getCreateEmployerRequests() {
        return smuHiringService.getCreateEmployerRequests();
    }

    /*@GetMapping(value = "/getDeleteEmployerRequests", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobMatchingRequest> getDeleteEmployerRequests() {
        return smuHiringService.getDeleteEmployerRequests();
    }*/

    @GetMapping(value = "/getCreateProfessionalRequests", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProfessionalRequest> getCreateProfessionalRequests() {
        return smuHiringService.getCreateProfessionalRequests();
    }

    /*@GetMapping(value = "/getDeleteProfessionalRequests", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobMatchingRequest> getDeleteProfessionalRequests() {
        return smuHiringService.getDeleteProfessionalRequests();
    }*/

    @PostMapping(value = "/denyCreateEmployerRequest/{userId}")
    public void denyCreateEmployerRequest(@PathVariable("userId") String userId){
        smuHiringService.denyCreateEmployerRequest(userId);
    }

    @PostMapping(value = "/denyCreateProfessionalRequest/{userId}")
    public void denyCreateProfessionalRequest(@PathVariable("userId") String userId){
        smuHiringService.denyCreateProfessionalRequest(userId);
    }

    //denyCreateEmployerRequest
    //denyCreateProfessionalRequest

}
