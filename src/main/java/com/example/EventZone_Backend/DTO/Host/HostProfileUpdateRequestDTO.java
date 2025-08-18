package com.example.EventZone_Backend.DTO.Host;

import com.example.EventZone_Backend.Entity.Host;
import lombok.Data;

@Data
public class HostProfileUpdateRequestDTO {
    private String name;
    private String description;
    private String phoneNumber;
    private String website;
    private String instagram;
    private String linkedin;
    private String twitter;
}
