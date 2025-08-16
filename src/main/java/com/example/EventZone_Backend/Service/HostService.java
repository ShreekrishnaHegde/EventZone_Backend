package com.example.EventZone_Backend.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.EventZone_Backend.DTO.Host.HostProfileResponseDTO;
import com.example.EventZone_Backend.DTO.Host.HostProfileUpdateRequestDTO;
import com.example.EventZone_Backend.DTO.Auth.HostSignUpRequestDTO;
import com.example.EventZone_Backend.Entity.Attendee;
import com.example.EventZone_Backend.Entity.Host;
import com.example.EventZone_Backend.Mapper.HostMapper;
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
        Host host= HostMapper.toEntity(request);
        host.setPassword(passwordEncoder.encode(request.getPassword()));
        return hostRepository.save(host);
    }
    //to fetch the profile
    public HostProfileResponseDTO getProfile() {
        String email = getCurrentUserEmail();
        Host host = hostRepository.findByEmail(email);
        return HostMapper.toHostProfileResponseDTO(host);
    }
    //to update the profile
    public HostProfileResponseDTO updateProfile(HostProfileUpdateRequestDTO request, MultipartFile logo) throws Exception {
        String email = getCurrentUserEmail();
        Host host = hostRepository.findByEmail(email);
        if(host==null){
            throw new Exception("Host not found with email "+email);
        }
        //if image already exists; then delete the old image then add the new image.
        if (logo != null && host.getLogoUrl() != null) {
            deleteImageFromCloudinary(host.getLogoPublicId());
        }
        if (logo != null && !logo.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(logo.getBytes(), ObjectUtils.emptyMap());
            host.setLogoUrl((String) uploadResult.get("secure_url"));
            host.setLogoPublicId((String) uploadResult.get("public_id"));
        }
        HostMapper.applyUpdates(request,host);
        hostRepository.save(host);
        return HostMapper.toHostProfileResponseDTO(host);
    }
    //to delete profile photo
    public void deleteProfilePhoto(){
        String email=getCurrentUserEmail();
        Host host=hostRepository.findByEmail(email);
        if(host==null){
            throw new RuntimeException("Host not email "+email+" not found");
        }
        String publicId= host.getLogoPublicId();
        if(publicId!=null){
            deleteImageFromCloudinary(publicId);
            host.setLogoPublicId(null);
            host.setLogoUrl(null);
            hostRepository.save(host);
        }
    }
    public void deleteImageFromCloudinary(String publicId) {
        try {
            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image from Cloudinary", e);
        }
    }
    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
