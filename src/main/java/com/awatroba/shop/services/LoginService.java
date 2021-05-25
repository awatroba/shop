package com.awatroba.shop.services;

import com.awatroba.shop.database.UserRepo;
import com.awatroba.shop.models.User;
import com.awatroba.shop.models.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Angelika
 * custom database-backed UserDetailsService for authentication with Spring Security
 * UserDetailsService interface is used to retrieve user-related data.
 * It has one method named loadUserByUsername() which can be overridden to customize the process of finding the user.
 */
@Service
public class LoginService implements UserDetailsService {
    private UserRepo userRepo;

    @Autowired
    public LoginService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * function for load User by name
     *
     * @param userName login from login page
     * @return UserDetails if  user exist in db
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = Optional.of(userRepo.findFirstByLogin(userName)).
                orElseThrow(() -> new UsernameNotFoundException("No such user in database"));
        UserDetailsImp userDetailsImp = new UserDetailsImp(user);
        return userDetailsImp;
    }

}
