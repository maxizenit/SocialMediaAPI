package ru.maxizenit.socialmediaapi.service;

import java.util.Collection;
import ru.maxizenit.socialmediaapi.entity.Subscription;
import ru.maxizenit.socialmediaapi.entity.User;
import ru.maxizenit.socialmediaapi.exception.UserAlreadySubscribedException;
import ru.maxizenit.socialmediaapi.exception.UserNotFoundException;
import ru.maxizenit.socialmediaapi.exception.UserNotSubscribedException;

/** Сервис для подписок. */
public interface SubscriptionService {

  /**
   * Возвращает {@code true}, если пользователи являются друзьями.
   *
   * @param first первый пользователь
   * @param second второй пользователь
   * @return {@code true}, если пользователи являются друзьями
   */
  boolean areFriends(User first, User second);

  /**
   * Подписывает текущего пользователя на другого.
   *
   * @param authorId идентификатор пользователя, на которого подписываются
   * @return подписка
   * @throws UserNotFoundException если один из пользователей не найден
   * @throws UserAlreadySubscribedException если пользователь уже подписан на другого
   */
  Subscription subscribe(Integer authorId)
      throws UserNotFoundException, UserAlreadySubscribedException;

  /**
   * Отписывает текущего пользователя от другого.
   *
   * @param authorId идентификатор пользователя, от которого отписываются
   * @throws UserNotFoundException если один из пользователей не найден
   * @throws UserNotSubscribedException если пользователь не подписан на другого
   */
  void unsubscribe(Integer authorId) throws UserNotFoundException, UserNotSubscribedException;

  Collection<Subscription> getSubscriptionsBySubscriber(User subscriber);
}
