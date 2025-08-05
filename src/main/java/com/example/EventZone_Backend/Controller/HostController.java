package com.example.EventZone_Backend.Controller;

import com.example.EventZone_Backend.DTO.Host.HostProfileResponseDTO;
import com.example.EventZone_Backend.DTO.Host.HostProfileUpdateRequestDTO;
import com.example.EventZone_Backend.Service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public HostProfileResponseDTO updateProfile(
            @RequestPart("data") HostProfileUpdateRequestDTO request,
            @RequestPart(value = "logo",required = false)MultipartFile logoFile
            ) {
        return hostService.updateProfile(request,logoFile);
    }

}
