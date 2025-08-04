package com.example.EventZone_Backend.DTO.Host;

import com.example.EventZone_Backend.Entity.Host;
import lombok.Data;

@Data
public class HostProfileResponseDTO {
    private String email;
    private String fullname;

    public static HostProfileResponseDTO fromEntityToThis(Host host) {
        HostProfileResponseDTO dto = new HostProfileResponseDTO();
        dto.setEmail(host.getEmail());
        dto.setFullname(host.getFullname());
        return dto;
    }
}
