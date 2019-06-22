package com.globomatics.bikes.config;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfigution extends WebSecurityConfigurerAdapter {
    @Value(value = "$(authO.apiAudience)")
    private String apiAudience;

    @Value(value = "$(authO.issue)")
    private String issuer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer.forRS256(apiAudience, issuer)
                .configure(http)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/bikes").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/bikes").hasAuthority("view:registrations")
                .antMatchers(HttpMethod.GET, "/api/v1/bikes/**").hasAuthority("view:registration")
                .anyRequest().authenticated();
    }
}
