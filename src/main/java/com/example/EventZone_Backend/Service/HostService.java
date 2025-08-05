package com.example.EventZone_Backend.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.EventZone_Backend.DTO.Host.HostProfileResponseDTO;
import com.example.EventZone_Backend.DTO.Host.HostProfileUpdateRequestDTO;
import com.example.EventZone_Backend.DTO.Auth.HostSignUpRequestDTO;
import com.example.EventZone_Backend.Entity.Host;
import com.example.EventZone_Backend.Repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class HostService {

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Cloudinary cloudinary;

    //signup
    public Host register(HostSignUpRequestDTO request){
        Host host=new Host();
        host.setEmail(request.getEmail());
        host.setPassword(request.getPassword());
        host.setClubName(request.getClubName());
        return hostRepository.save(host);
    }
    //to fetch the profile
    public HostProfileResponseDTO getProfile() {
        String email = getCurrentUserEmail();
        Host host = hostRepository.findByEmail(email);
        return HostProfileResponseDTO.fromEntityToThis(host);
    }
    //to update the profile
    public HostProfileResponseDTO updateProfile(HostProfileUpdateRequestDTO request, MultipartFile logo) {
        String email = getCurrentUserEmail();
        Host host = hostRepository.findByEmail(email);
        host.setClubName(request.getClubName());
        host.setClubDescription(request.getClubDescription());
        host.setPhoneNumber(request.getPhoneNumber());
        host.setWebsite(request.getWebsite());
        host.setInstagram(request.getInstagram());
        host.setLinkedin(request.getLinkedin());
        host.setClubLogo(request.getClubLogo());
        if (logo != null && !logo.isEmpty()) {
            try {
                Map uploadResult = cloudinary.uploader().upload(logo.getBytes(), ObjectUtils.emptyMap());
                String logoUrl = (String) uploadResult.get("secure_url");
                request.setClubLogo(logoUrl);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload logo", e);
            }
        }
        hostRepository.save(host);
        return HostProfileResponseDTO.fromEntityToThis(host);
    }

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
