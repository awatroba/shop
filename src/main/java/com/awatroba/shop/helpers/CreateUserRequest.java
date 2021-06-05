package com.awatroba.shop.helpers;
/**
 * @author Angelika
 * Model of create user request
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
                ",email='" + email + '\'' +
                '}';
    }

    public CreateUserRequest(String login, String email,String password, String confirmPassword) {
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

    public CreateUserRequest() {
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
