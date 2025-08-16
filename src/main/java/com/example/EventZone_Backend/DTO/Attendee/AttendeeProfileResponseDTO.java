package com.example.EventZone_Backend.DTO.Attendee;

import com.example.EventZone_Backend.Entity.Attendee;
import lombok.Data;

@Data
public class AttendeeProfileResponseDTO {

    private String email;
    private String imageUrl;
    private String imagePublicId;

    private String fullName;
    private String collegeName;
    private String usn;
    private String branchName;
}
