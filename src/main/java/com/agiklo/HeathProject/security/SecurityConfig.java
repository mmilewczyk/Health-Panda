package com.agiklo.HeathProject.security;

import com.agiklo.HeathProject.model.User;
import com.agiklo.HeathProject.repository.UserRepository;
import com.agiklo.HeathProject.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private UserRepository userRepository;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/workout").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.DELETE,"/workout").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/workout").hasRole("ADMIN")
                .and()
                .formLogin()
                    .defaultSuccessUrl("/", true)
                    .permitAll()
                .and()
                .logout()
                    .permitAll()
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
        .and()
        .csrf().disable();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createUsers(){
        User admin = new User("Mateusz", passwordEncoder().encode("123"), "ROLE_ADMIN");
        User user = new User("Angelika", passwordEncoder().encode("123"),"ROLE_USER");
        userRepository.save(admin);
        userRepository.save(user);
    }
}
