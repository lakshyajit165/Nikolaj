package com.stackroute.helpdesk.commanddesignframework.commands.invoice.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private int transaction_id;
    private String rideId;
    private String userId;

    
    private String mobile;

    
    private String source;

    
    private String destination;

    
    private Double distance;

    
    private int duration;

    
    private String paymentMethodId;

    
    private Double rideFare;
    
    private int discountPercent;

    
    private Double extraCharges;

    
    private Double amountPaid;

    
    private Double fuelRefillAmount;

    
    private String paymentId;

    
    private LocalDateTime deductedAt;

    
    private PaymentStatus status;

}
