package com.example.EventZone_Backend.DTO.Attendee;

import com.example.EventZone_Backend.Entity.Attendee;
import lombok.Data;

@Data
public class AttendeeProfileResponseDTO {

    private String email;
    private String imageUrl;
    private String imagePublicId;

    public static AttendeeProfileResponseDTO fromEntityToThis(Attendee attendee){
        AttendeeProfileResponseDTO dto=new AttendeeProfileResponseDTO();
        dto.setEmail(attendee.getEmail());
        dto.setImageUrl(attendee.getImageUrl());
        return dto;
    }
}
