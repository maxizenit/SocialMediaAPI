package ru.maxizenit.socialmediaapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Timestamp;
import lombok.Data;

/** Пост. */
@Entity
@Data
public class Post {

  /** Идентификатор. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /** Время создания поста. */
  private Timestamp timestamp;

  /** Автор. */
  @ManyToOne
  @JoinColumn(name = "author_id")
  private User author;

  /** Заголовок. */
  private String title;

  /** Текст. */
  private String text;
}
