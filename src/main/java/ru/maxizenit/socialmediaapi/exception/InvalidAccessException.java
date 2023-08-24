package ru.maxizenit.socialmediaapi.exception;

/** Сигнализирует об отсутствии прав на операцию. */
public class InvalidAccessException extends Exception {

  private static final String MESSAGE =
      "User '%s' does not have rights to operation with '%s' with id '%d'";

  public InvalidAccessException(String username, String entity, Integer entityId) {
    super(String.format(MESSAGE, username, entity, entityId));
  }
}
