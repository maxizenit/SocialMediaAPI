package ru.maxizenit.socialmediaapi.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.maxizenit.socialmediaapi.entity.User;
import ru.maxizenit.socialmediaapi.exception.UserNotFoundException;

/** Сервис для пользователей. */
public interface UserService extends UserDetailsService {

  /**
   * Возвращает текущего авторизованного пользователя.
   *
   * @return текущий пользователь
   * @throws UserNotFoundException если пользователь не найден
   */
  User getCurrentUser() throws UserNotFoundException;

  /**
   * Сохраняет пользователя
   *
   * @param user сохраняемый пользователь
   * @return сохранённый пользователь
   */
  User saveUser(User user);

  /**
   * Возвращает пользователя по его идентификатору.
   *
   * @param id идентификатор
   * @return пользователь
   * @throws UserNotFoundException если пользователь не найден
   */
  User getUserById(Integer id) throws UserNotFoundException;

  /**
   * Возвращает пользователя по имени пользователя.
   *
   * @param username имя пользователя
   * @return пользователь
   * @throws UserNotFoundException если пользователь не найден
   */
  User getUserByUsername(String username) throws UserNotFoundException;
}
