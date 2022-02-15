package com.demo.springrestdocs.configuration;

import static java.util.Collections.singletonList;

import com.demo.springrestdocs.dto.UserDetails;
import com.demo.springrestdocs.entities.User;
import com.demo.springrestdocs.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

  private final UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    String email = authentication.getName();
    String password = authentication.getCredentials().toString();

    User user = userService.getByCredentials(email, password);

    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());

    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        user.getEmail(), user.getPassword(), singletonList(grantedAuthority));

    authToken.setDetails(new UserDetails(user.getId(), user.getEmail(), user.getRole()));

    return authToken;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return aClass.equals(UsernamePasswordAuthenticationToken.class);
  }
}
