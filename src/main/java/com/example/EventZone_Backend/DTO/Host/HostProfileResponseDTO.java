package com.example.EventZone_Backend.DTO.Host;

import com.example.EventZone_Backend.Entity.Host;
import lombok.Data;

@Data
public class HostProfileResponseDTO {
    private String email;
    private String clubName;
    private String clubDescription;
    private String phoneNumber;
    private String clubLogo;
    private String website;
    private String instagram;
    private String linkedin;

    public static HostProfileResponseDTO fromEntityToThis(Host host) {
        HostProfileResponseDTO dto = new HostProfileResponseDTO();
        dto.setEmail(host.getEmail());
        dto.setClubDescription(host.getClubDescription());
        dto.setClubName(host.getClubName());
        dto.setLinkedin(host.getLinkedin());
        dto.setInstagram(host.getInstagram());
        dto.setWebsite(host.getWebsite());
        dto.setPhoneNumber(host.getPhoneNumber());
        dto.setClubLogo(host.getClubLogo());
        return dto;
    }
}
