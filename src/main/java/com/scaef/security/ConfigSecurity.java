package com.scaef.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scaef.enums.EnumFuncao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login-error")
                .permitAll()
                .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll()
                .and()
            .authorizeRequests()
                /*.antMatchers("/resources/**").permitAll().and()*/
                .antMatchers("/usuario/**").hasAuthority(EnumFuncao.Administrador.toString())
                .antMatchers("/paciente/**").hasAnyAuthority(EnumFuncao.Administrador.toString(), EnumFuncao.Farmacêutico.toString(), EnumFuncao.Atendente.toString())
                .antMatchers("/medicamento/**").hasAnyAuthority(EnumFuncao.Administrador.toString(), EnumFuncao.Farmacêutico.toString())
                .and()
            .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/mídia/**")
                .permitAll()
                .and()
            .authorizeRequests()
                .anyRequest().authenticated();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(getPasswordEncoder());
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    public class CustomfailureHandler implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    
            request.setAttribute("error","Login Inválido!");
    
        }
    }
}