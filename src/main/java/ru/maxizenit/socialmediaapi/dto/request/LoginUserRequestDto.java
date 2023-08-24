package ru.maxizenit.socialmediaapi.dto.request;

import lombok.Data;

/** Запрос на аутентификацию пользователя. */
@Data
public class LoginUserRequestDto {

  /** Имя пользователя. */
  private String username;

  /** Пароль. */
  private String password;
}
