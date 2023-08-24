package ru.maxizenit.socialmediaapi.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maxizenit.socialmediaapi.dto.SubscriptionDto;
import ru.maxizenit.socialmediaapi.dto.UserDto;
import ru.maxizenit.socialmediaapi.entity.Subscription;
import ru.maxizenit.socialmediaapi.entity.User;
import ru.maxizenit.socialmediaapi.exception.UserAlreadySubscribedException;
import ru.maxizenit.socialmediaapi.exception.UserNotFoundException;
import ru.maxizenit.socialmediaapi.exception.UserNotSubscribedException;
import ru.maxizenit.socialmediaapi.service.SubscriptionService;
import ru.maxizenit.socialmediaapi.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  private final SubscriptionService subscriptionService;

  private final ModelMapper modelMapper;

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUser(@PathVariable Integer id) throws UserNotFoundException {
    User user = userService.getUserById(id);
    UserDto userDto = modelMapper.map(user, UserDto.class);

    return new ResponseEntity<>(userDto, HttpStatus.OK);
  }

  @PostMapping("/{id}/subscribe")
  public ResponseEntity<SubscriptionDto> subscribe(@PathVariable Integer id)
      throws UserNotFoundException, UserAlreadySubscribedException {
    Subscription subscription = subscriptionService.subscribe(id);
    SubscriptionDto subscriptionDto = modelMapper.map(subscription, SubscriptionDto.class);

    return new ResponseEntity<>(subscriptionDto, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}/unsubscribe")
  public ResponseEntity<?> unSubscribe(@PathVariable Integer id)
      throws UserNotFoundException, UserNotSubscribedException {
    subscriptionService.unsubscribe(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
