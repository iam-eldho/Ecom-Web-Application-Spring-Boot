/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecom.service;

import com.ecom.exception.BadRequestException;
import com.ecom.form.LoginForm;
import com.ecom.view.LoginView;

/**
 *
 * @author eldho
 */
public interface LoginService {

    LoginView login(LoginForm form);

}
