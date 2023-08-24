package ru.maxizenit.socialmediaapi.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.maxizenit.socialmediaapi.exception.InvalidAccessException;
import ru.maxizenit.socialmediaapi.exception.PostNotFoundException;
import ru.maxizenit.socialmediaapi.exception.UserAlreadySubscribedException;
import ru.maxizenit.socialmediaapi.exception.UserNotFoundException;
import ru.maxizenit.socialmediaapi.exception.UserNotSubscribedException;
import ru.maxizenit.socialmediaapi.exception.UsernameAlreadyRegisteredException;
import ru.maxizenit.socialmediaapi.exception.UsersNotFriendsException;

/** Обработчик исключений для контроллеров. */
@ControllerAdvice
public class DefaultAdvice {

  @ExceptionHandler({UsersNotFriendsException.class, UserNotSubscribedException.class})
  public ResponseEntity<String> handleBadRequestException(Exception e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidAccessException.class)
  public ResponseEntity<String> handleForbiddenException(Exception e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler({UserNotFoundException.class, PostNotFoundException.class})
  public ResponseEntity<String> handleNotFoundException(Exception e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({
    UsernameAlreadyRegisteredException.class,
    UserAlreadySubscribedException.class
  })
  public ResponseEntity<String> handleConflictException(Exception e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
  }
}
