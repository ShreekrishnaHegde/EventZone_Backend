package com.example.EventZone_Backend.DTO.Host;

import com.example.EventZone_Backend.Entity.Host;
import lombok.Data;

@Data
public class HostProfileResponseDTO {
    private String email;
    private String name;
    private String description;
    private String phoneNumber;
    private String logoUrl;
    private String website;
    private String instagram;
    private String linkedin;
    private String twitter;
}
