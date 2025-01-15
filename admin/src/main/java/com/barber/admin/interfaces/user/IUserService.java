package com.barber.admin.interfaces.user;

import com.barber.admin.dto.user.UserDTO;
import com.barber.admin.dto.user.UserFilterDTO;
import com.barber.admin.presenters.user.UserPresenter;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    UserPresenter createUser(UserDTO user) throws  SQLException;
    List<UserPresenter> getAllUsers(UserFilterDTO userFilter) throws  SQLException;
}
