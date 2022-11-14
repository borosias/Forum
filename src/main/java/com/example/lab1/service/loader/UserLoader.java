package com.example.lab1.service.loader;

import com.example.lab1.service.DefaultRoles;
import com.example.lab1.service.UserService;
import com.example.lab1.web.data.transfer.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class UserLoader implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public UserLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        userService.signUp(new Credentials("Boros", "qwerty123"), DefaultRoles.ADMIN);
    }
}
