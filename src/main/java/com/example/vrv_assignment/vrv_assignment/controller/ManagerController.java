package com.example.vrv_assignment.vrv_assignment.controller;

import com.example.vrv_assignment.vrv_assignment.dto.UserDTO;
import com.example.vrv_assignment.vrv_assignment.user.Role;
import com.example.vrv_assignment.vrv_assignment.user.User;
import com.example.vrv_assignment.vrv_assignment.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    private final UserService userService;

    public ManagerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> get(){
        return userService.findByRole(Role.MANAGER);
    }

    @PostMapping
    public String post(){
        return "Post: manager";
    }

    @PutMapping
    public String put(){
        return "Put: manager";
    }

    @DeleteMapping
    public String delete(){
        return "Delete: manager";
    }
}
