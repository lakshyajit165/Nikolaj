package com.stackroute.helpdesk.commanddesignframework.commands.kycstatus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class KycStatus {
    private String documentStatus;
    private String drivingLicenceNumber;
    private String image;
    private ExpiryDate expiryDate;
}
