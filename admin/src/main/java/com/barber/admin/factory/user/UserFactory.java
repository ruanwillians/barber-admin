package com.barber.admin.factory.user;

import com.barber.admin.dto.user.UserDTO;
import com.barber.admin.entity.user.UserEntity;

import java.util.UUID;

public class UserFactory {

    public static UserEntity createUser(UserDTO user) throws Exception {
        UserEntity userEntity = new UserEntity(UUID.randomUUID(), user.getName(), user.getEmail(), user.getRole());
        userEntity.setPassword(user.getPassword());
        userEntity.setActive(user.isActive());

        return userEntity;
    }


}
