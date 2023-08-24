package ru.maxizenit.socialmediaapi.exception;

/** Сигнализирует о том, что два пользователя не являются друзьями. */
public class UsersNotFriendsException extends Exception {

  private static final String MESSAGE = "Users '%s' and '%s' are not friends";

  public UsersNotFriendsException(String firstUsername, String secondUsername) {
    super(String.format(MESSAGE, firstUsername, secondUsername));
  }
}
