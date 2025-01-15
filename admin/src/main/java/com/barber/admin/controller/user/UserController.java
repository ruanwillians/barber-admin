package com.barber.admin.controller.user;

import com.barber.admin.dto.user.UserDTO;
import com.barber.admin.dto.user.UserFilterDTO;
import com.barber.admin.presenters.user.UserPresenter;
import com.barber.admin.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("")
    public List<UserPresenter> getAllUser(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "active", required = false) Boolean active,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        UserFilterDTO userFilter = new UserFilterDTO(page, pageSize);
        userFilter.setEmail(email);
        userFilter.setActive(active);

        return userService.getAllUsers(userFilter);
    }

    @PostMapping("")
    public UserPresenter CreateUser(@RequestBody UserDTO user) {
        return userService.createUser(user);
    }
}
