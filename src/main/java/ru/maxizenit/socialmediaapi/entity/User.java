package ru.maxizenit.socialmediaapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** Пользователь. */
@Entity
@Table(schema = "public")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

  /** Идентификатор. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Integer id;

  /** Логин. */
  private String username;

  /** Адрес электронной почты. */
  private String email;

  /** Пароль. */
  private String password;
}
