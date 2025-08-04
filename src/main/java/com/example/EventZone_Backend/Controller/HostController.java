package com.example.EventZone_Backend.Controller;

import com.example.EventZone_Backend.DTO.Host.HostProfileResponse;
import com.example.EventZone_Backend.DTO.Host.HostProfileUpdateRequest;
import com.example.EventZone_Backend.Service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/host")
public class HostController {
    @Autowired
    private HostService hostService;

    @GetMapping("/profile")
    public HostProfileResponse getProfile() {
        return hostService.getProfile();
    }
    @PutMapping("/profile/update")
    public HostProfileResponse updateProfile(@RequestBody HostProfileUpdateRequest request) {
        return hostService.updateProfile(request);
    }
}
