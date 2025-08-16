package com.example.EventZone_Backend.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.EventZone_Backend.DTO.Host.HostProfileResponseDTO;
import com.example.EventZone_Backend.DTO.Host.HostProfileUpdateRequestDTO;
import com.example.EventZone_Backend.DTO.Auth.HostSignUpRequestDTO;
import com.example.EventZone_Backend.Entity.Host;
import com.example.EventZone_Backend.Repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        host.setName(request.getClubName());
        return hostRepository.save(host);
    }
    //to fetch the profile
    public HostProfileResponseDTO getProfile() {
        String email = getCurrentUserEmail();
        Host host = hostRepository.findByEmail(email);
        return HostProfileResponseDTO.fromEntityToThis(host);
    }
    //to update the profile
    public HostProfileResponseDTO updateProfile(HostProfileUpdateRequestDTO request, MultipartFile logo) throws IOException {
        String email = getCurrentUserEmail();
        Host host = hostRepository.findByEmail(email);

        if (logo != null && host.getLogoUrl() != null) {
            deleteImageFromCloudinary(host.getLogoPublicId());
        }
        if (logo != null && !logo.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(logo.getBytes(), ObjectUtils.emptyMap());
            host.setLogoUrl((String) uploadResult.get("secure_url"));
            host.setLogoPublicId((String) uploadResult.get("public_id"));
        }
        HostProfileUpdateRequestDTO.fromEntityToThis(host);
        hostRepository.save(host);
        return HostProfileResponseDTO.fromEntityToThis(host);
    }

    public ResponseEntity<?> deleteLogo(){
        String email=getCurrentUserEmail();
        Host host=hostRepository.findByEmail(email);
        String url=host.getLogoUrl();
        deleteImageFromCloudinary(url);
        host.setLogoUrl(null);
        return null;
    }
    public void deleteImageFromCloudinary(String publicId) {
        try {
            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            System.out.println("Deleted from Cloudinary: " + result);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image from Cloudinary", e);
        }
    }
    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
