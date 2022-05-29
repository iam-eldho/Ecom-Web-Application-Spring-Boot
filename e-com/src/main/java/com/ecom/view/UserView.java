/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.view;

import com.ecom.entity.User;
import java.util.Date;

/**
 *
 * @author eldho
 */
public class UserView {

    private final Long userId;
    private final String name;
    private final String email;

    public UserView(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
