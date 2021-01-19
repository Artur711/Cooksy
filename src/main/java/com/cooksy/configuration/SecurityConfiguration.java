package com.cooksy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/register", "/swagger**", "/api/v1/*", "/api/v1/recipes/*",
                        "/api/v1/recipes/recipe-detail/*", "/api/v1/favorites/*", "/api/v1/products/*", "/api/v1/favorites/recipe/*",
                        "/temp/*").permitAll()
                .antMatchers("/login", "/register", "/swagger**", "/api/v1/*", "/api/v1/recipes/recipe-detail/*", "/shopping-list/*/*", "/shopping-list/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandlerConfig());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
