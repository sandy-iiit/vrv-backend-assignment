package com.example.vrv_assignment.vrv_assignment.controller;

import com.example.vrv_assignment.vrv_assignment.dto.UserDTO;
import com.example.vrv_assignment.vrv_assignment.user.Role;
import com.example.vrv_assignment.vrv_assignment.user.User;
import com.example.vrv_assignment.vrv_assignment.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> get(){
        return userService.findByRole(Role.ADMIN);
    }

    @PostMapping
    public String post(){
        return "Post: admin";
    }

    @PutMapping
    public String put(){
        return "Put: admin";
    }

    @DeleteMapping
    public String delete(){
        return "Delete: admin";
    }
}
