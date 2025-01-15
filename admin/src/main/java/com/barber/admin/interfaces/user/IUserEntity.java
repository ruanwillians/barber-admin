package com.barber.admin.interfaces.user;

import com.barber.admin.enums.user.UserRole;

import java.util.UUID;

public interface IUserEntity {
    UUID getId();
    void setId(UUID id);

    String getName();
    void setName(String name);

    String getEmail();
    void setEmail(String email);

    UserRole getRole();
    void setRole(UserRole role);

    String getPassword();
    void setPassword(String password);

    boolean getActive();
    void setActive(boolean active);

    void isValid() throws Exception;
}
