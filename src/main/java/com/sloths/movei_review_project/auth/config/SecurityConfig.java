package com.sloths.movei_review_project.auth.config;

import com.sloths.movei_review_project.auth.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public SecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //the order of this chain is important
        http
                .authorizeRequests().antMatchers(HttpMethod.POST, "/users/addUser").permitAll()//the parameters that are passed into antMatchers method will be allowed
                .anyRequest().authenticated() //every request except ones that include "/" and "home" must be authenticated
                .and()
                .formLogin() //form login will be used to authenticate the users and "/login" will be the login page
                .permitAll() //all the requests which want to reach to login page will be allowed
                .and()
                .csrf().disable();
//                .logout().permitAll() //and all the requests which want to reach to logout page will be allowed
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
