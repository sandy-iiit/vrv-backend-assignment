package com.example.vrv_assignment.vrv_assignment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
}
