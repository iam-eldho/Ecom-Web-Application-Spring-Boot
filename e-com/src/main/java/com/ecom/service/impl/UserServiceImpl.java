/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecom.service.impl;

import com.ecom.entity.User;
import com.ecom.repository.UserRespository;
import com.ecom.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author eldho
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRespository userRespository;

    @Override
    public List<User> list() {
        System.out.println("User Listing");
        List<User> userList = new ArrayList<>();
        userList = userRespository.findAll();
        userList.forEach(user -> System.out.println("User :" + user));
        return userList;
    }

    @Override
    public User add(User user) {
        System.out.println("Registration Started");
        String password = passwordEncoder.encode(user.getPassword());
        return userRespository.save(new User(user.getName(), user.getEmail(), password));
    }

    @Override
    public ResponseEntity delete(Long userId) {
        System.out.println("Deletion Started");
        userRespository.deleteById((userId));
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity update(Long userId, String name, String email, String password) {
        System.out.println("Updation Started");
        userRespository.update(userId, name, email, password);
        return new ResponseEntity(HttpStatus.OK);
    }
}
