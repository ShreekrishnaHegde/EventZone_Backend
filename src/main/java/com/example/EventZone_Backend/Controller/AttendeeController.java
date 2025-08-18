package com.example.EventZone_Backend.Controller;


import com.example.EventZone_Backend.DTO.Attendee.AttendeeProfileResponseDTO;
import com.example.EventZone_Backend.DTO.Attendee.AttendeeProfileUpdateRequestDTO;
import com.example.EventZone_Backend.Service.AttendeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/attendee")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @GetMapping(value = "/profile")
    public ResponseEntity<AttendeeProfileResponseDTO> getProfile(){
        AttendeeProfileResponseDTO profile=attendeeService.getProfile();
        if(profile==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(profile);
    }

    @PutMapping(
            value = "/profile/update",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AttendeeProfileResponseDTO> updateProfile(
            @Valid @RequestPart(value = "data")AttendeeProfileUpdateRequestDTO updateRequestDTO,
            @RequestPart(value = "image",required = false)MultipartFile image
            ) throws Exception {
        AttendeeProfileResponseDTO updatedProfile=attendeeService.updateProfile(updateRequestDTO,image);
        if(updatedProfile==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedProfile);
    }
    @DeleteMapping("/profile/profilePhoto")
    public ResponseEntity<?> deleteProfilePhoto(){
        try{
            attendeeService.deleteProfilePhoto();
            return ResponseEntity.ok("Logo Deleted Successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error:"+e.getMessage());
        }
    }
}
