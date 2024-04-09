package com.webappgroupg.SMUHiring.controller;

import com.webappgroupg.SMUHiring.service.RootOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/createStaff")
    public void createStaff(@RequestParam String userId, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam int phoneNumber) {
        RootOperations rootOps = new RootOperations();
        rootOps.createStaff(userId, firstName, lastName, email, phoneNumber);
    }
}
