package ru.maxizenit.socialmediaapi.dto;

import lombok.Data;

/** Подписка. */
@Data
public class SubscriptionDto {

  /** Идентификатор. */
  private Integer id;

  /** Подписчик. */
  private UserDto subscriber;

  /** Автор. */
  private UserDto author;
}
