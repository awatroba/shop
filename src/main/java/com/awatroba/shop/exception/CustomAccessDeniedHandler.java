package com.awatroba.shop.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * @author Angelika
 * Custom access denied handler
 */
public class CustomAccessDeniedHandler
        implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException exc) throws IOException, ServletException {

        response.sendRedirect(request.getContextPath() + "/accessDenied");
    }

}