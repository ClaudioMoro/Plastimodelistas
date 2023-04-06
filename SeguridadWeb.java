/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pmt.plastimodelistasdetucuman;

import com.pmt.plastimodelistasdetucuman.servicios.UsuarioServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author cmoro1
 */
    @Configuration
    @EnableWebSecurity
    @EnableMethodSecurity(prePostEnabled = true)
public class SeguridadWeb {

    @Autowired
    public UsuarioServicio usuarioServicio;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usuarioServicio)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) ->
                {
            try {
                authz
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/mod/**").hasRole("MODERADOR")
//                        .requestMatchers("/**").hasRole("SOCIO")
                        .requestMatchers("/**").permitAll()                         //.hasRole("VISITANTE")
                        .requestMatchers("/css/*", "/js/*", "/img/*", "/**")
                        .permitAll()
                        .and().formLogin(form -> {
                    try {
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/logincheck")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/inicio")
                                .permitAll()
                                
                                .and().logout()
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .permitAll()
                                
                                .and().csrf()
                                .disable();
                    } catch (Exception ex) {
                        Logger.getLogger(SeguridadWeb.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            } catch (Exception ex) {
                Logger.getLogger(SeguridadWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        return http.build();
    }
}
