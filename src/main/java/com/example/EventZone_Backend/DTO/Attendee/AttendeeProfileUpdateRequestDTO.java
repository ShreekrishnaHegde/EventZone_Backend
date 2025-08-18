package com.example.EventZone_Backend.DTO.Attendee;

import com.example.EventZone_Backend.Entity.Attendee;
import lombok.Data;

@Data
public class AttendeeProfileUpdateRequestDTO {

    private String fullName;
    private String collegeName;
    private String usn;
    private String branchName;
}
