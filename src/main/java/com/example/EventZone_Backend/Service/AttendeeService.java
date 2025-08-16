package com.example.EventZone_Backend.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.EventZone_Backend.DTO.Attendee.AttendeeProfileResponseDTO;
import com.example.EventZone_Backend.DTO.Attendee.AttendeeProfileUpdateRequestDTO;
import com.example.EventZone_Backend.DTO.Auth.AttendeeSignUpRequestDTO;
import com.example.EventZone_Backend.Entity.Attendee;
import com.example.EventZone_Backend.Mapper.AttendeeMapper;
import com.example.EventZone_Backend.Repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class AttendeeService {
    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Cloudinary cloudinary;

    //signup
    public Attendee register(AttendeeSignUpRequestDTO request){
        Attendee attendee= AttendeeMapper.toEntity(request);
        attendee.setPassword(passwordEncoder.encode(request.getPassword()));
        return attendeeRepository.save(attendee);
    }

    //to fetch the profile
    public AttendeeProfileResponseDTO getProfile(){
        String email=getCurrentUserEmail();
        Attendee attendee=attendeeRepository.findByEmail(email);
        return AttendeeProfileResponseDTO.fromEntityToThis(attendee);
    }

    //to update the profile
    public AttendeeProfileResponseDTO updateProfile(AttendeeProfileUpdateRequestDTO requestDTO, MultipartFile image) throws Exception {
        String email=getCurrentUserEmail();
        Attendee attendee=attendeeRepository.findByEmail(email);
        if (attendee == null) {
            throw new Exception("Attendee not found with email: " + email);
        }

        if(image!=null && attendee.getImageUrl()!=null){

        }
        if(image!=null && !image.isEmpty()){
            Map uploadResult=cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            attendee.setImageUrl((String)uploadResult.get("secure_url"));
            attendee.setImagePublicId((String)uploadResult.get("public_id"));
        }
        requestDTO.applyUpdatesTo(attendee);
        attendeeRepository.save(attendee);
        return AttendeeProfileResponseDTO.fromEntityToThis(attendee);

    }
    private String getCurrentUserEmail(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
