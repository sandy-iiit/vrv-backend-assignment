package com.example.vrv_assignment.vrv_assignment.controller;

import com.example.vrv_assignment.vrv_assignment.item.Item;
import com.example.vrv_assignment.vrv_assignment.item.ItemRepository;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/demo")
@RequiredArgsConstructor
public class DemoController {
    private final ItemRepository itemRepository;

    @GetMapping
    public List<Item> getDemoData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String role = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .filter(authority -> authority.startsWith("ROLE_"))
                    .findFirst()
                    .orElse("No Role");
            System.out.println(authentication.getAuthorities());
            // Logic based on role
            if ("ROLE_ADMIN".equals(role)) {
                return itemRepository.findAll(); // Admin can see all items
            } else if ("ROLE_MANAGER".equals(role)) {
                return itemRepository.findAll().stream()
                        .filter(item -> item.getCategory().equals("Category A")) // Manager sees Category A items
                        .toList();
            } else if ("ROLE_USER".equals(role)) {
                return itemRepository.findAll().stream()
                        .filter(item -> item.getCategory().equals("Category B")) // User sees Category B items
                        .toList();
            } else {
                return List.of(); // If no role or unknown role
            }
        }
        return List.of();
    }

}