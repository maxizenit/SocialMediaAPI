package ru.maxizenit.socialmediaapi.exception;

/** Сигнализирует о том, что пользователь не найден. */
public class UserNotFoundException extends Exception {

  private static final String MESSAGE_FOR_ID = "User with id '%d' not found";

  private static final String MESSAGE_FOR_USERNAME = "User with username '%s' not found";

  public UserNotFoundException(Integer userId) {
    super(String.format(MESSAGE_FOR_ID, userId));
  }

  public UserNotFoundException(String username) {
    super(String.format(MESSAGE_FOR_USERNAME, username));
  }
}
