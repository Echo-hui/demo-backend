package com.example.demobackend.service;

import com.example.demobackend.entity.User;

import java.util.List;

/**
 * @Description 
 * @Author wh
 * @Date 2025/1/22
 */public interface UserService {

     List<User> getAll();

     User selectUserById(Integer id);

     void deleteUserById(Integer id);

}
