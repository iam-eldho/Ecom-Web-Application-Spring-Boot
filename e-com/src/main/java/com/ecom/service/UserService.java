/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecom.service;

import com.ecom.entity.User;
import com.ecom.exception.BadRequestException;
import com.ecom.view.LoginView;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author eldho
 */
public interface UserService {

    List<User> list();

    User add(User form);

    ResponseEntity delete(Long userId);

    ResponseEntity update(Long userId, String name, String email, String password);

}
