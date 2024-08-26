package com.nupex.nupex_backend.dto;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private Long businessUnitId;
    private String firstName;
    private String lastName;   
    private String companyName;   
    private Integer phone;    
    private String email;    
    private String address;    
    private String commercialActivity;    
    private String clientType;    
    private String clientCode;    
    private String status;    
    private String nationalId;    
    private ZonedDateTime relationshipStartDate;     
}
