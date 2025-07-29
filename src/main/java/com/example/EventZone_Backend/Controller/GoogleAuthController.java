package com.example.EventZone_Backend.Controller;

import com.example.EventZone_Backend.Entity.Attendee;
import com.example.EventZone_Backend.Entity.Host;
import com.example.EventZone_Backend.Repository.AttendeeRepository;
import com.example.EventZone_Backend.Repository.HostRepository;
import com.example.EventZone_Backend.utilis.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth/google")
public class GoogleAuthController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private HostRepository hostRepository;

    @Value("${google.client-id}")
    private String clientId;

    @Value("${google.client-secret}")
    private String clientSecret;

    @Value("${google.redirect-uri}")
    private String redirectUri;

    @Value("${google.token-endpoint}")
    private String tokenEndpoint;


    @GetMapping("/callback")
    public ResponseEntity<?> handleGoogleCallback(@RequestParam String code, @RequestParam String role) {
        try {
            // Step 1: Exchange authorization code for token
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("code", code);
            params.add("client_id", clientId);
            params.add("client_secret", clientSecret);
            params.add("redirect_uri", redirectUri);
            params.add("grant_type", "authorization_code");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

            ResponseEntity<Map> tokenResponse = restTemplate.postForEntity(tokenEndpoint, request, Map.class);
            if (!tokenResponse.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token exchange failed");
            }

            String idToken = (String) tokenResponse.getBody().get("id_token");

            // Step 2: Extract email from ID token
            String userInfoUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
            ResponseEntity<Map> userInfoResponse = restTemplate.getForEntity(userInfoUrl, Map.class);
            if (userInfoResponse.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid ID token");
            }

            String email = (String) userInfoResponse.getBody().get("email");

            // Step 3: Check if the user exists and create if not
            if (role.equalsIgnoreCase("attendee")) {
                Optional<Attendee> existing = Optional.ofNullable(attendeeRepository.findByEmail(email));
                if (existing.isEmpty()) {
                    Attendee newUser = new Attendee();
                    newUser.setEmail(email);
                    newUser.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
                    attendeeRepository.save(newUser);
                }
            } else if (role.equalsIgnoreCase("host")) {
                Optional<Host> existing = Optional.ofNullable(hostRepository.findByEmail(email));
                if (existing.isEmpty()) {
                    Host newUser = new Host();
                    newUser.setEmail(email);
                    newUser.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
                    hostRepository.save(newUser);
                }
            } else {
                return ResponseEntity.badRequest().body("Invalid role specified");
            }

            // Step 4: Generate and return JWT
            String jwtToken = jwtUtil.generateToken(email,role.toUpperCase()); // or include role in claims
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwtToken);
            response.put("role", role.toUpperCase());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("OAuth error occurred");
        }
    }
}
