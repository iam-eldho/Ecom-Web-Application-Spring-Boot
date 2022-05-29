/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecom.form;

/**
 *
 * @author eldho
 */
public class UserForm {

    private String name;

    private String email;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserForm{" + "name=" + name + ", email=" + email + ", password=" + password + '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

}
