package ru.maxizenit.socialmediaapi.dto.request;

import lombok.Data;

/** Запрос на регистрацию пользователя. */
@Data
public class RegisterUserRequestDto {

  /** Имя пользователя. */
  private String username;

  /** Адрес электронной почты. */
  private String email;

  /** Пароль. */
  private String password;
}
