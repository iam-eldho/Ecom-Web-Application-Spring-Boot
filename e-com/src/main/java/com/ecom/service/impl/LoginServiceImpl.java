/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecom.service.impl;

import com.ecom.entity.User;
import com.ecom.exception.BadRequestException;
import com.ecom.form.LoginForm;
import com.ecom.repository.UserRespository;
import com.ecom.security.config.SecurityConfig;
import com.ecom.security.util.TokenGenerator;
import com.ecom.service.LoginService;
import com.ecom.view.LoginView;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author eldho
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final String PURPOSE_REFRESH_TOKEN = "REFRESH_TOKEN";
    public static final String PURPOSE_ACCESS_TOKEN = "ACCESS_TOKEN";

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginView login(LoginForm form) {
        System.out.println("Authentication Started");
        User user = userRespository.findByEmails(form.getEmail());
        if (Objects.isNull(user)) {
            throw new BadRequestException("user not found");
        }
        if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            throw new BadRequestException("incorrect username or password");
        }
        String id = String.format("%010d", user.getUserId());
        TokenGenerator.Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        TokenGenerator.Token refreshToken = tokenGenerator.create(PURPOSE_REFRESH_TOKEN, id + user.getPassword(), securityConfig.getRefreshTokenExpiry());
        return new LoginView(user, accessToken, refreshToken);
    }

}
