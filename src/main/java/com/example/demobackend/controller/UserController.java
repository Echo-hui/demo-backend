package com.example.demobackend.controller;

import com.example.demobackend.entity.User;
import com.example.demobackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author wh
 * @Date 2025/1/21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "getUser", method = {RequestMethod.GET, RequestMethod.POST})
    public User getUserById() {
        User user = new User();
        int userId = 1;
        user = userService.selectUserById(userId);
        System.out.println(user);
        System.out.println(user.getId());
        return user;
    }
}
