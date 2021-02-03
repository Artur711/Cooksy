package com.cooksy.configuration;

import com.cooksy.filter.AuthTokenFilter;

//import com.cooksy.util.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/login", "/register", "/swagger**", "/api/v1/*", "/api/v1/recipes/*",
                        "/api/v1/recipes/recipe-detail/*", "/api/v1/favorites/*", "/api/v1/products/*", "/api/v1/favorites/recipe/*",
                        "/temp/*", "/shopping-list/*/*", "/shopping-list/*", "/shopping-list").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//                .logout()
//                .logoutSuccessHandler(new LogoutSuccessHandlerConfig());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


}
