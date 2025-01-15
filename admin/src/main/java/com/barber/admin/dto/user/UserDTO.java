package com.barber.admin.dto.user;

import com.barber.admin.enums.user.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDTO {

    private UUID id;
    private String name;
    private String email;
    private UserRole role;
    private String password;
    private boolean isActive;

    public UserDTO(UUID id, String name, String email, UserRole role, String password, boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
        this.isActive = isActive;
    }

}
