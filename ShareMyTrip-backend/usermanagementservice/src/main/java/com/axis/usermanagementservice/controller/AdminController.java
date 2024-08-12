package com.axis.usermanagementservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.axis.usermanagementservice.dto.*;
import com.axis.usermanagementservice.entity.Admin;
import com.axis.usermanagementservice.exception.AdminNotFoundException;
import com.axis.usermanagementservice.service.AdminService;
import com.axis.usermanagementservice.service.PassengerService;
import com.axis.usermanagementservice.service.PublisherService;

@RestController
@RequestMapping("/user/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    private static final String AUTH_SERVICE_URL = "http://authserver:9898/auth/register";
    private static final String TOKEN_SERVER_URL = "http://authserver:9898/auth/token";

    @GetMapping
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        List<AdminDto> adminDtos = admins.stream()
                .map(admin -> modelMapper.map(admin, AdminDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(adminDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> getAdminById(@PathVariable int id) {
        Admin admin = adminService.getAdminById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found"));
        AdminDto adminDto = modelMapper.map(admin, AdminDto.class);
        return ResponseEntity.ok(adminDto);
    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> createAdmin(@RequestBody RegisterRequest registerRequest) {
//        Admin admin = modelMapper.map(registerRequest, Admin.class);
//        UserCredential userCredential = new UserCredential();
//        userCredential.setEmail(registerRequest.getEmail());
//        userCredential.setPassword(registerRequest.getPassword());
//        userCredential.setUserType("admin");
//
//        AuthRequest authRequest = new AuthRequest(userCredential.getEmail(), userCredential.getPassword(), userCredential.getUserType());
//        ResponseEntity<String> authResponse = restTemplate.postForEntity(AUTH_SERVICE_URL, authRequest, String.class);
//
//        if (authResponse.getStatusCode() == HttpStatus.OK) {
//            Admin savedAdmin = adminService.saveAdmin(admin);
//            if (savedAdmin == null)
//                return new ResponseEntity<>("Admin Exists..Please Login!!!", HttpStatus.CONFLICT);
//            return new ResponseEntity<>("Admin added!!!", HttpStatus.OK);
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register admin");
//        }
//    }
     @PostMapping("/register")
     public ResponseEntity<?> createAdmin(@RequestBody RegisterRequest registerRequest) {
         Admin admin = modelMapper.map(registerRequest, Admin.class);
         Admin savedAdmin = adminService.saveAdmin(admin);
         if(savedAdmin==null)
         	return new ResponseEntity<>("Admin Exists..Please Login!!!",HttpStatus.CONFLICT);
         return new ResponseEntity<>("Admin added!!!",HttpStatus.OK);
     }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> updateAdmin(@PathVariable int id, @RequestBody RegisterRequest registerRequest) {
        Admin existingAdmin = adminService.getAdminById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found"));

        existingAdmin.setEmail(registerRequest.getEmail());
        existingAdmin.setPassword(registerRequest.getPassword());
        existingAdmin.setAdminFullName(registerRequest.getAdminFullName());

        Admin updatedAdmin = adminService.updateAdmin(existingAdmin);
        AdminDto adminDto = modelMapper.map(updatedAdmin, AdminDto.class);
        return ResponseEntity.ok(adminDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable int id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AdminDto> loginAdmin(@RequestBody LoginRequest loginRequest) {
        AdminDto admin = adminService.loginAdmin(loginRequest.getEmail(), loginRequest.getPassword());
        if (admin != null) {
            AuthRequest authRequest = new AuthRequest(loginRequest.getEmail(), loginRequest.getPassword(), "admin");
            ResponseEntity<String> authResponse = restTemplate.postForEntity(TOKEN_SERVER_URL, authRequest, String.class);
            if (authResponse.getStatusCode() == HttpStatus.OK && authResponse.getBody() != null) {
                admin.setToken(authResponse.getBody());
                return ResponseEntity.ok(admin);
            } else {
                return ResponseEntity.status(authResponse.getStatusCode()).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/publishers")
    public ResponseEntity<List<PublisherDTO>> getAllPublisher() {
        List<PublisherDTO> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/passengers")
    public ResponseEntity<List<PassengerDTO>> getAllPassengers() {
        List<PassengerDTO> passengers = passengerService.getAllPassengers();
        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserDetails(@PathVariable String email) {
        List<Object> user = adminService.getUserDetails(email);
        user.stream().forEach(System.out::println);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/publisher/{id}")
    public ResponseEntity<PublisherDTO> getPublisher(@PathVariable Integer id) {
        PublisherDTO p = publisherService.getPublisherById(id);
        return ResponseEntity.ok(p);
    }
}
