package com.example.vrv_assignment.vrv_assignment.controller;

import com.example.vrv_assignment.vrv_assignment.dto.UserDTO;
import com.example.vrv_assignment.vrv_assignment.user.ChangePasswordRequest;
import com.example.vrv_assignment.vrv_assignment.user.Role;
import com.example.vrv_assignment.vrv_assignment.user.User;
import com.example.vrv_assignment.vrv_assignment.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    @GetMapping
    public List<UserDTO> get(){
        return service.findByRole(Role.USER);
    }
    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
