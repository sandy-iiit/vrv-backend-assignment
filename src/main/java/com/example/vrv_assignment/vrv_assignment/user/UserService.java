package com.example.vrv_assignment.vrv_assignment.user;

import com.example.vrv_assignment.vrv_assignment.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Not the same password");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }
    public static UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(String.valueOf(user.getRole()));
        return userDTO;
    }

    // Method to map a list of User entities to a list of UserDTOs
    public static List<UserDTO> mapToDTOList(List<User> users) {
        return users.stream()
                .map(UserService::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> findByRole(Role role) {
        List<User> users = new ArrayList<>();
        if (Role.ADMIN.equals(role)) {
            users = repository.findAll();
        } else if (Role.USER.equals(role)) {
            users = repository.findByRole(role);
        } else if (Role.MANAGER.equals(role)) {
            users = repository.findByRole(role);
            List<User> temp = repository.findByRole(Role.USER);
            users.addAll(temp);
        }

        // Map the list of User entities to UserDTOs
        return UserService.mapToDTOList(users);
    }
}
