package com.awatroba.shop.helpers;
/**
 * @author Angelika
 *
 */
public class CreateUserRequest {
    private String login;
    private String password;
    private String confirmPassword;
    private String email;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
