package com.webappgroupg.SMUHiring.model1;


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

}
