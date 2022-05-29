/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecom.controller;

import com.ecom.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author eldho
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${elearning.name}")
    private String name;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();

    }

    @PostMapping("/add")
    public User add(@ModelAttribute User form) {
        return userService.add(form);
    }

    @PostMapping("/delete/{user_id}")
    public ResponseEntity delete(@PathVariable("user_id") Long user_id) {
        return userService.delete(user_id);
    }

    @PostMapping("/edit")
    public ResponseEntity edit(@RequestBody User form) {
        return userService.update(form.getUserId(), form.getName(), form.getEmail(), form.getPassword());
    }

}
