package com.awatroba.shop.services;

import com.awatroba.shop.database.UserRepo;
import com.awatroba.shop.helpers.CreateUserRequest;
import com.awatroba.shop.models.ShoppingCart;
import com.awatroba.shop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Angelika
 * Service that allows users to register. Processes user input and writes to database
 */
@Service
public class RegistrationService {
    private final int CHARACTERS_IN_PASS = 7;
    private final String PASS_INC_REG_ERROR = "Passwords should contain" +
            " digit, special char,upper case and more than " + CHARACTERS_IN_PASS + " characters! ";
    private final static String PASS_INC_ERROR = "Passwords must be the same!Please enter correct password";
    private final static String LOGIN_INC_REG_ERROR = "Login should contain only digit, upper case";
    private final static String EMAIL_ERROR = "Please enter correct email";
    private final static String LOGIN_UNAVAILABLE_ERROR = "Login unavailable, please choose another one";
    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private final static String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^?!&+=])(?=\\S+$).{4,}$";
    private final static String LOGIN_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";

    private UserRepo userRepo;

    @Autowired
    public RegistrationService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * function searching for a user by login from the database and checking user data
     *
     * @param request create user request
     * @return errorMessage or ""
     */
    public String addNewUser(CreateUserRequest request) {
        String errorMessage = checkUserData(request);
        if (!errorMessage.equals(""))
            return errorMessage;
        User user =new User(request.getLogin(), request.getEmail(), request.getPassword(),new ShoppingCart());
        userRepo.save(user);
        return "";
    }

    /**
     * function for data validation
     *
     * @param request create user request
     * @return errorMessage or ""
     */
    private String checkUserData(CreateUserRequest request) {
        if (request.getLogin().isEmpty() || !isValidLogin(request.getLogin())) {
            return LOGIN_INC_REG_ERROR;
        }
        if (!isLoginAvailable(request.getLogin())) {
            return LOGIN_UNAVAILABLE_ERROR;
        }
        if (request.getEmail().isEmpty() || !isValidEmail(request.getEmail())) {
            return EMAIL_ERROR;
        }
        if (request.getPassword().isEmpty() || !isValidPassword(request.getPassword())) {
            return PASS_INC_REG_ERROR;
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return PASS_INC_ERROR;
        }
        return "";
    }

    /**
     * function for password validation
     *
     * @param password password from create user request
     * @return true if  valid  else false
     */
    private boolean isValidPassword(final String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * function for email validation
     *
     * @param email email from create user request
     * @return true if  valid  else false
     */
    private boolean isValidEmail(final String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * function for login validation
     *
     * @param login login from create user request
     * @return true if  valid  else false
     */
    private boolean isValidLogin(final String login) {
        Pattern pattern = Pattern.compile(LOGIN_PATTERN);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    /**
     * function for checking the availability of the login
     *
     * @param login login from create user request
     * @return true if  valid  else false
     */
    private boolean isLoginAvailable(final String login) {
        return userRepo.findFirstByLogin(login) == null ? true : false;
    }
    User getUserById(Long id){
        return userRepo.findFirstById(id);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }
}
