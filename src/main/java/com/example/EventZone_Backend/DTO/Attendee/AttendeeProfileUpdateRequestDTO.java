package com.example.EventZone_Backend.DTO.Attendee;

import com.example.EventZone_Backend.Entity.Attendee;
import lombok.Data;

@Data
public class AttendeeProfileUpdateRequestDTO {

    private String fullname;
    private String collegeName;
    private String USN;
    private String branchName;

    public void applyUpdatesTo(Attendee attendee) {
        if (this.fullname != null) {
            attendee.setEmail(this.fullname);
        }
        if (this.collegeName != null) {
            attendee.setEmail(this.collegeName);
        }
        if (this.branchName != null) {
            attendee.setEmail(this.branchName);
        }
        if (this.USN != null) {
            attendee.setEmail(this.USN);
        }

    }
}
