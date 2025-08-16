package com.example.EventZone_Backend.DTO.Host;

import com.example.EventZone_Backend.Entity.Host;
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

    public static HostProfileResponseDTO fromEntityToThis(Host host) {
        HostProfileResponseDTO dto = new HostProfileResponseDTO();
        dto.setEmail(host.getEmail());
        dto.setClubDescription(host.getDescription());
        dto.setClubName(host.getName());
        dto.setLinkedin(host.getLinkedin());
        dto.setInstagram(host.getInstagram());
        dto.setWebsite(host.getWebsite());
        dto.setPhoneNumber(host.getPhoneNumber());
        dto.setClubLogo(host.getLogoUrl());
        return dto;
    }
}
