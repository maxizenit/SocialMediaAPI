package ru.maxizenit.socialmediaapi.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.maxizenit.socialmediaapi.dto.UserDto;
import ru.maxizenit.socialmediaapi.dto.request.LoginUserRequestDto;
import ru.maxizenit.socialmediaapi.dto.request.RegisterUserRequestDto;
import ru.maxizenit.socialmediaapi.entity.User;
import ru.maxizenit.socialmediaapi.exception.UserNotFoundException;
import ru.maxizenit.socialmediaapi.exception.UsernameAlreadyRegisteredException;
import ru.maxizenit.socialmediaapi.service.AuthService;

/** Контроллер авторизации. */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  private final ModelMapper modelMapper;

  /**
   * Регистрирует пользователя.
   *
   * @param registerUserRequestDto DTO с информацией для регистрации (имя пользователя, e-mail,
   *     пароль)
   * @return зарегистрированный пользователь
   * @throws UsernameAlreadyRegisteredException если имя пользователя уже зарегистрировано
   */
  @PostMapping("/register")
  public ResponseEntity<UserDto> registerUser(
      @RequestBody RegisterUserRequestDto registerUserRequestDto)
      throws UsernameAlreadyRegisteredException {
    User user =
        authService.registerUser(
            registerUserRequestDto.getUsername(),
            registerUserRequestDto.getEmail(),
            registerUserRequestDto.getPassword());

    return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.CREATED);
  }

  /**
   * Возвращает JWT-токен авторизации.
   *
   * @param loginUserRequestDto DTO с информацией для авторизации (имя пользователя, пароль)
   * @return JWT-токен авторизации
   * @throws UserNotFoundException если пользователь с таким именем не существует
   */
  @PostMapping("/login")
  public ResponseEntity<String> loginUser(@RequestBody LoginUserRequestDto loginUserRequestDto)
      throws UserNotFoundException {
    String token =
        authService.loginUser(loginUserRequestDto.getUsername(), loginUserRequestDto.getPassword());

    return new ResponseEntity<>(token, HttpStatus.OK);
  }
}
