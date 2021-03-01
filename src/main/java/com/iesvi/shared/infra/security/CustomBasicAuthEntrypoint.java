package com.iesvi.shared.infra.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iesvi.shared.infra.err.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
public class CustomBasicAuthEntrypoint extends BasicAuthenticationEntryPoint {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.addHeader("WWW-Authenticate", "Basic realm='" + getRealmName() + "'");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, authException.getMessage());
        String strApiError = mapper.writeValueAsString(apiError);

        PrintWriter writer = response.getWriter();
        writer.println(strApiError);


    }

    @PostConstruct
    public void initRealname() {
        setRealmName("iesvi.com"); //ambito de la autenticación.
    }


}