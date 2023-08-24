package ru.maxizenit.socialmediaapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/** Подписка одного пользователя на другого. */
@Entity
@Data
public class Subscription {

  /** Идентификатор. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /** Подписчик. */
  @ManyToOne
  @JoinColumn(name = "subscriber_id")
  private User subscriber;

  /** Автор (пользователь, на которого подписаны). */
  @ManyToOne
  @JoinColumn(name = "author_id")
  private User author;
}
