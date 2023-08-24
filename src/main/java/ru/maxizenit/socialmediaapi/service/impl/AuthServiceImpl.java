package ru.maxizenit.socialmediaapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.maxizenit.socialmediaapi.entity.User;
import ru.maxizenit.socialmediaapi.exception.UserNotFoundException;
import ru.maxizenit.socialmediaapi.exception.UsernameAlreadyRegisteredException;
import ru.maxizenit.socialmediaapi.service.AuthService;
import ru.maxizenit.socialmediaapi.service.UserService;
import ru.maxizenit.socialmediaapi.util.JwtTokenUtils;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserService userService;

  private final AuthenticationManager authenticationManager;

  private final JwtTokenUtils jwtTokenUtils;

  private final PasswordEncoder passwordEncoder;

  @Override
  public User registerUser(String username, String email, String password)
      throws UsernameAlreadyRegisteredException {
    try {
      userService.getUserByUsername(username);
      throw new UsernameAlreadyRegisteredException(username);
    } catch (UserNotFoundException e) {
      User user = new User();

      user.setUsername(username);
      user.setEmail(email);
      user.setPassword(passwordEncoder.encode(password));

      return userService.saveUser(user);
    }
  }

  @Override
  public String loginUser(String username, String password) throws UserNotFoundException {
    userService.getUserByUsername(username);
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    UserDetails userDetails = userService.loadUserByUsername(username);

    return jwtTokenUtils.generateToken(userDetails);
  }
}
