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

    @Value("${google.redirection-uri}")
    private String redirectUri;

    @Value("${google.token-endpoint}")
    private String tokenEndpoint;

    @Value("${google.authorization-endpoint}")
    private String authorizationEndpoint;


    @GetMapping("/callback")
    public ResponseEntity<?> handleGoogleCallback(@RequestParam String code, @RequestParam String state) {
        try {
            String role = state;

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

            assert tokenResponse.getBody() != null;
            String idToken = (String) tokenResponse.getBody().get("id_token");
            String userInfoUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
            ResponseEntity<Map> userInfoResponse = restTemplate.getForEntity(userInfoUrl, Map.class);
            if (userInfoResponse.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid ID token");
            }

            assert userInfoResponse.getBody() != null;
            String email = (String) userInfoResponse.getBody().get("email");

            if ("attendee".equalsIgnoreCase(role)) {
                if (attendeeRepository.findByEmail(email) == null) {
                    Attendee newUser = new Attendee();
                    newUser.setEmail(email);
                    newUser.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
                    attendeeRepository.save(newUser);
                }
            } else if ("host".equalsIgnoreCase(role)) {
                if (hostRepository.findByEmail(email) == null) {
                    Host newUser = new Host();
                    newUser.setEmail(email);
                    newUser.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
                    hostRepository.save(newUser);
                }
            } else {
                return ResponseEntity.badRequest().body("Invalid role");
            }

            String jwtToken = jwtUtil.generateToken(email, role.toUpperCase());
            String redirectMobile = "myapp://auth?token=" + jwtToken + "&email=" + email + "&role=" + role;
            return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectMobile).build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("OAuth error");
        }
    }

    @GetMapping("/url")
    public ResponseEntity<Void> getGoogleOAuthUrl(@RequestParam String role) {
        String scope = "openid email profile";
        String authUrl = authorizationEndpoint + "?" +
                "client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&response_type=code" +
                "&scope=" + scope +
                "&access_type=offline" +
                "&prompt=consent" +
                "&state=" + role;
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, authUrl)
                .build();
    }

}
