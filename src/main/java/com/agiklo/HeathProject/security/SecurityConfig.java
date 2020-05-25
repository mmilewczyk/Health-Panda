package com.agiklo.HeathProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("Mateusz").password("123").roles("ADMIN").build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("User").password("123").roles("USER").build();
        return new InMemoryUserDetailsManager(admin, user);
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
