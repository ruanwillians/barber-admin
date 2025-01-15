package com.barber.admin.presenters.user;

import com.barber.admin.enums.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
public class UserPresenter  {

    private UUID id;
    private String name;
    private String email;
    private UserRole role;
    private boolean isActive;
    private Date createdAt;
    private Date deletedAt;
    private Date updatedAt;

    public UserPresenter(UUID id, String name, String email, UserRole role, boolean isActive, Date createdAt, Date deletedAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.updatedAt = updatedAt;
    }

}
