package ru.maxizenit.socialmediaapi.dto.request;

import lombok.Data;

/** Запрос на отправку сообщения. */
@Data
public class SendMessageRequestDto {

  /** Идентификатор получателя. */
  private Integer receiverId;

  /** Текст. */
  private String text;
}
