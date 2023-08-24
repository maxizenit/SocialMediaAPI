package ru.maxizenit.socialmediaapi.dto;

import java.sql.Timestamp;
import lombok.Data;

/** Сообщение. */
@Data
public class MessageDto {

  /** Идентификатор. */
  private Integer id;

  /** Время отправки. */
  private Timestamp timestamp;

  /** Отправитель. */
  private UserDto sender;

  /** Получатель. */
  private UserDto receiver;

  /** Текст. */
  private String text;
}
