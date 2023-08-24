package ru.maxizenit.socialmediaapi.service;

import ru.maxizenit.socialmediaapi.entity.User;
import ru.maxizenit.socialmediaapi.exception.UserNotFoundException;
import ru.maxizenit.socialmediaapi.exception.UsernameAlreadyRegisteredException;

/** Сервис для авторизации. */
public interface AuthService {

  /**
   * Регистрирует пользователя и возвращает его.
   *
   * @param username имя пользователя
   * @param email адрес электронной почты
   * @param password пароль
   * @return зарегистрированный пользователь
   * @throws UsernameAlreadyRegisteredException если имя пользователя занято
   */
  User registerUser(String username, String email, String password)
      throws UsernameAlreadyRegisteredException;

  /**
   * Аутентифицирует пользователя и возвращает JWT-токен.
   *
   * @param username имя пользователя
   * @param password пароль
   * @return JWT-токен
   * @throws UserNotFoundException если пользователь не найден
   */
  String loginUser(String username, String password) throws UserNotFoundException;
}
