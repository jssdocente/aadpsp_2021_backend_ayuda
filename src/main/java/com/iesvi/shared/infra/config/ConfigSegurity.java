package com.iesvi.shared.infra.config;

import com.iesvi.shared.infra.security.CustomBasicAuthEntrypoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfigSegurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomBasicAuthEntrypoint authbasic;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//
//       // web.ignoring().anyRequest();  //Ahora no ignorabmos las peticiones, sino que las vamos a autentifificar.
//
//    }

    //Se indica cómo se va a validar los usuarios, y qué tipo de encoder utilizar.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .authenticationEntryPoint(authbasic)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/pedido/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/pedido/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/pedido/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/pedido/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
