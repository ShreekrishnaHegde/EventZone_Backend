package com.example.EventZone_Backend.Controller;

import com.example.EventZone_Backend.DTO.Host.HostProfileResponseDTO;
import com.example.EventZone_Backend.DTO.Host.HostProfileUpdateRequestDTO;
import com.example.EventZone_Backend.Service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/host")
public class HostController {
    @Autowired
    private HostService hostService;

    @GetMapping("/profile")
    public HostProfileResponseDTO getProfile() {
        return hostService.getProfile();
    }

    @PutMapping(value = "/profile/update",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HostProfileResponseDTO> updateProfile(
            @RequestPart("data") HostProfileUpdateRequestDTO request,
            @RequestPart(value = "logo",required = false)MultipartFile logoFile
            ) throws Exception {
        HostProfileResponseDTO updatedProfile=hostService.updateProfile(request,logoFile);
        if(updatedProfile==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping(value = "/profile/logo")
    public ResponseEntity<?> deleteProfileLogo(){
        try{
            hostService.deleteProfilePhoto();
            return ResponseEntity.ok("Logo deleted successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error:"+e.getMessage());
        }
    }


}
