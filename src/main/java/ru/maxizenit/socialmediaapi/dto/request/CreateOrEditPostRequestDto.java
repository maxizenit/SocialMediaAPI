package ru.maxizenit.socialmediaapi.dto.request;

import lombok.Data;

/** Запрос на создание или редактирование поста. */
@Data
public class CreateOrEditPostRequestDto {

  /** Заголовок. */
  private String title;

  /** Текст. */
  private String text;
}
