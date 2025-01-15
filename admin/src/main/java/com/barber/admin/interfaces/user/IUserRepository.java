package com.barber.admin.interfaces.user;

import com.barber.admin.dto.user.UserDTO;
import com.barber.admin.dto.user.UserFilterDTO;
import com.barber.admin.entity.user.UserEntity;
import com.barber.admin.presenters.user.UserPresenter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IUserRepository  {

    UserPresenter saveUser(Connection connection, UserEntity user) throws SQLException;
    List<UserPresenter> getPaginatedUsers(Connection connection , UserFilterDTO userFilter)  throws SQLException;
    UserPresenter findUserByEmail(Connection connection, String email) throws SQLException;

}
