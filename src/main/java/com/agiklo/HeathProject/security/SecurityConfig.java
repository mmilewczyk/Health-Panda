package com.agiklo.HeathProject.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Mateusz").password(PasswordEncoder().encode("123")).roles("ADMIN")
                .and()
                .withUser("Angelika").password(PasswordEncoder().encode("123")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/console/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/workout").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,"/workout").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/workout").hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .csrf().disable();
    }
}
