package com.barber.admin.entity.user;

import com.barber.admin.enums.user.UserRole;
import com.barber.admin.interfaces.user.IUserEntity;

import java.util.UUID;

public class UserEntity implements IUserEntity {

    private UUID id;
    private String name;
    private String email;
    private UserRole role;
    private String password;
    private boolean isActive;

    public UserEntity(UUID id, String name, String email, UserRole role) throws Exception {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setRole(role);
        this.isValid();
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public void setId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        this.email = email;
    }

    @Override
    public UserRole getRole() {
        return role;
    }

    @Override
    public void setRole(UserRole role) {
        if (role == null) {
            throw new IllegalArgumentException("Role is required");
        }
        this.role = role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        this.password = password;
    }

    @Override
    public boolean getActive() {
        return this.isActive;
    }

    @Override
    public void setActive(boolean active) {
    this.isActive = active;
    }

    @Override
    public void isValid() throws Exception {
        if (this.name == null || this.name.isBlank()) {
            throw new Exception("Name is required");
        }

        if (this.email == null || this.email.isBlank()) {
            throw new Exception("Email is required");
        }
    }
}
