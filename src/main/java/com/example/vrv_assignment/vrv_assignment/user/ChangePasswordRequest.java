package com.example.vrv_assignment.vrv_assignment.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordRequest {

    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
}

//$2a$10$Q3tZRAWyPhEd2cp6su2V6ub0yKQYBvLO22Mvd9oR78HV/M6bMFUjy