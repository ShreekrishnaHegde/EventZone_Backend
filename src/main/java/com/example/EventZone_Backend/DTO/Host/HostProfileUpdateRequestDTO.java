package com.example.EventZone_Backend.DTO.Host;

import lombok.Data;

@Data
public class HostProfileUpdateRequestDTO {
    private String clubName;
    private String clubDescription;
    private String phoneNumber;
    private String website;
    private String instagram;
    private String linkedin;
    private String clubLogo;
}
