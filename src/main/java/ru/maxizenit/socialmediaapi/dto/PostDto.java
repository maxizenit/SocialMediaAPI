package ru.maxizenit.socialmediaapi.dto;

import java.sql.Timestamp;
import lombok.Data;

/** Пост. */
@Data
public class PostDto {

  /** Идентификатор. */
  private Integer id;

  /** Время создания. */
  private Timestamp timestamp;

  /** Автор. */
  private UserDto author;

  /** Заголовок. */
  private String title;

  /** Текст. */
  private String text;
}
