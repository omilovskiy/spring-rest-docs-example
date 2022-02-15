package com.demo.springrestdocs.configuration;

import com.demo.springrestdocs.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {

  private final AuthProvider authProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors()
        .and()
        .csrf()
        .disable()
        .authorizeRequests()
        .and()
        .formLogin()
        .loginPage("/unauthorized")
        .loginProcessingUrl("/login")
        .failureUrl("/failure")
        .defaultSuccessUrl("/success")
        .usernameParameter("email")
        .passwordParameter("password")
        .permitAll()
        .and()
        .authorizeRequests()
        .antMatchers("/users/**").authenticated()
        .antMatchers("/admin/**").hasAuthority(UserRole.ADMIN.toString());
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authProvider);
  }
}

