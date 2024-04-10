package com.webappgroupg.SMUHiring.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {
    private String userId;
    private String paymentId;
    private Double paymentAmount;
    private String paymentDate;
    private String dueDate;

    public Payment(String userId, String paymentId, Double paymentAmount, String paymentDate, String dueDate) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.dueDate = dueDate;
    }

    public Payment() {
    }

    @Override
    public String toString() {
        return "Payment{" +
                "userId='" + userId + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", paymentDate='" + paymentDate + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
