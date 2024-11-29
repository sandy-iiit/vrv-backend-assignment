package com.example.vrv_assignment.vrv_assignment.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.vrv_assignment.vrv_assignment.user.Permission.*;

@RequiredArgsConstructor
@Getter
public enum Role {
    USER(Collections.emptySet()),
    ADMIN(Set.of(ADMIN_READ,ADMIN_CREATE,ADMIN_DELETE,ADMIN_UPDATE,MANAGER_CREATE,MANAGER_DELETE,MANAGER_UPDATE,MANAGER_READ)),
    MANAGER(Set.of(MANAGER_CREATE,MANAGER_DELETE,MANAGER_UPDATE,MANAGER_READ));

    private final Set<Permission> permissionSet;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissionSet()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
