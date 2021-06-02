package com.awatroba.shop.config;

import com.awatroba.shop.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Angelika
 * SpringSecurity class is annotated with @EnableWebSecurity to enable Spring Security’s
 * web security support and provide the Spring MVC integration.
 * It also extends WebSecurityConfigurerAdapter and overrides a couple of its methods to set some
 * specifics of the web security configuration.
 */

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginService loginService;

    /**
     * method defines authentication method
     *
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
    }

    /**
     * method defines which URL paths should be secured and which should not.
     *
     * @param http
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/dashboard").hasAnyRole(new String[]{"ADMIN", "USER"})
                .antMatchers(HttpMethod.GET, "/dashboard/**").hasAnyRole(new String[]{"ADMIN", "USER"})

                .antMatchers( "/cart").hasAnyRole(new String[]{ "USER"})

                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")

                .and().formLogin().loginPage("/login").permitAll().failureUrl("/login?error")
                .and().formLogin().defaultSuccessUrl("/dashboard")

                .and().logout().logoutSuccessUrl("/login")

                .and().csrf().disable()/*for h2 console*/
                .headers().frameOptions().disable()

                .and().httpBasic() .and().cors().disable();
    }

    /**
     * method for password encode
     *
     * @return BCryptPasswordEncoder object
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    ;
}