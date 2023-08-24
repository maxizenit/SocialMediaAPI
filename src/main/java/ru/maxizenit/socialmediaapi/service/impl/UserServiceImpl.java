package ru.maxizenit.socialmediaapi.service.impl;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.maxizenit.socialmediaapi.entity.User;
import ru.maxizenit.socialmediaapi.exception.UserNotFoundException;
import ru.maxizenit.socialmediaapi.repository.UserRepository;
import ru.maxizenit.socialmediaapi.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User getCurrentUser() throws UserNotFoundException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = (String) authentication.getPrincipal();

    return getUserByUsername(username);
  }

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User getUserById(Integer id) throws UserNotFoundException {
    return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
  }

  @Override
  public User getUserByUsername(String username) throws UserNotFoundException {
    return userRepository
        .findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException(username));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      User user = getUserByUsername(username);

      return new org.springframework.security.core.userdetails.User(
          user.getUsername(), user.getPassword(), Collections.emptyList());
    } catch (UserNotFoundException e) {
      throw new UsernameNotFoundException(e.getMessage());
    }
  }
}
