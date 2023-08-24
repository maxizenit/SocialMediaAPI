package ru.maxizenit.socialmediaapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Timestamp;
import lombok.Data;

/** Сообщение. */
@Entity
@Data
public class Message {

  /** Идентификатор. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /** Время отправления сообщения. */
  private Timestamp timestamp;

  /** Отправитель. */
  @ManyToOne
  @JoinColumn(name = "sender_id")
  private User sender;

  /** Получатель. */
  @ManyToOne
  @JoinColumn(name = "receiver_id")
  private User receiver;

  /** Текст. */
  private String text;
}
