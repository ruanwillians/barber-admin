package com.barber.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/teste")
    public String getAdminPage() {
        return "health api";
    }
}
