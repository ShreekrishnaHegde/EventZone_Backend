package com.example.EventZone_Backend.Controller;

import com.example.EventZone_Backend.DTO.Host.HostProfileResponseDTO;
import com.example.EventZone_Backend.DTO.Host.HostProfileUpdateRequestDTO;
import com.example.EventZone_Backend.Service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/host")
public class HostController {
    @Autowired
    private HostService hostService;

    @GetMapping("/profile")
    public HostProfileResponseDTO getProfile() {
        return hostService.getProfile();
    }

    @PutMapping("/profile/update")
    public HostProfileResponseDTO updateProfile(@RequestBody HostProfileUpdateRequestDTO request) {
        return hostService.updateProfile(request);
    }
}
