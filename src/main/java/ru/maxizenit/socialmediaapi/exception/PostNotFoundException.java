package ru.maxizenit.socialmediaapi.exception;

/** Сигнализирует о том, что пост не найден. */
public class PostNotFoundException extends Exception {

  private static final String MESSAGE = "Post with id '%d' not found";

  public PostNotFoundException(Integer postId) {
    super(String.format(MESSAGE, postId));
  }
}
