package ru.maxizenit.socialmediaapi.dto;

import lombok.Data;

/** Пользователь. */
@Data
public class UserDto {

  /** Идентификатор. */
  private Integer id;

  /** Имя пользователя. */
  private String username;

  /** Адрес электронной почты. */
  private String email;
}
