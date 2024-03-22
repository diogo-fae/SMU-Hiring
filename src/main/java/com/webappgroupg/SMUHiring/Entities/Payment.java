package com.webappgroupg.SMUHiring.Entities;

import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Min(value = 1, message = "Payment amount must be greater than 0")
    private Integer paymentAmount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date paymentDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date paymentDueDate;

    // Constructors, getters, and setters
}
