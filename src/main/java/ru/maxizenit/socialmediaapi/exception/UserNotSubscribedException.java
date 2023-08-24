package ru.maxizenit.socialmediaapi.exception;

/** Сигнализирует о том, что пользователь не подписан на другого пользователя. */
public class UserNotSubscribedException extends Exception {

  private static final String MESSAGE = "User '%s' is not subscribed on user '%s'";

  public UserNotSubscribedException(String subscriberUsername, String authorUsername) {
    super(String.format(MESSAGE, subscriberUsername, authorUsername));
  }
}
