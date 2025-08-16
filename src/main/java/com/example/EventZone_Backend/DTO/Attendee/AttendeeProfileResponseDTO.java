package com.example.EventZone_Backend.DTO.Attendee;

import com.example.EventZone_Backend.Entity.Attendee;
import lombok.Data;

@Data
public class AttendeeProfileResponseDTO {

    private String email;
    private String imageUrl;
    private String imagePublicId;

    private String fullname;
    private String collegeName;
    private String USN;
    private String branchName;

    public static AttendeeProfileResponseDTO fromEntityToThis(Attendee attendee){
        AttendeeProfileResponseDTO dto=new AttendeeProfileResponseDTO();
        dto.setEmail(attendee.getEmail());
        dto.setImageUrl(attendee.getImageUrl());
        dto.setFullname(attendee.getFullName());
        dto.setBranchName(attendee.getBranchName());
        dto.setUSN(attendee.getUsn());
        return dto;
    }
}
