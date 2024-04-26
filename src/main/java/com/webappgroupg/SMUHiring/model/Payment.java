package com.webappgroupg.SMUHiring.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {
    private String userId;
    private String paymentId;
    private String paymentAmount;
    private String paymentDate;
    private String dueDate;
    private String remainingBalance;

}
