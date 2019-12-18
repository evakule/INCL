package com.incl.config;

import com.incl.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  
  private static final String CUSTOMERS_ENDPOINT = "/api/v1/customers/**";
  private static final String COUNTRIES_ENDPOINT = "/api/v1/countries/**";
  private static final String LOAD_ENDPOINT = "/api/v1/load/**";
  private static final String INTERESTS_ENDPOINT = "/api/v1/interests/**";
  private static final String POSTS_ENDPOINT = "/api/v1/posts/**";
  
  
  @Autowired
  private CustomerService customerService;
  
  @Autowired
  private AuthenticationEntryPoint authEntryPoint;
  
  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customerService);
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(
            CUSTOMERS_ENDPOINT,
            COUNTRIES_ENDPOINT,
            LOAD_ENDPOINT
        )
        .hasRole("ADMIN")
        .antMatchers(
            INTERESTS_ENDPOINT,
            POSTS_ENDPOINT
        )
        .hasAnyRole(
            "ADMIN",
            "CUSTOMER"
        )
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .authenticationEntryPoint(authEntryPoint);
  }
}
