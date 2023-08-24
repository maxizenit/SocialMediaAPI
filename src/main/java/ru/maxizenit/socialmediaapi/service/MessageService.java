package ru.maxizenit.socialmediaapi.service;

import java.util.Collection;
import ru.maxizenit.socialmediaapi.entity.Message;
import ru.maxizenit.socialmediaapi.exception.UserNotFoundException;
import ru.maxizenit.socialmediaapi.exception.UsersNotFriendsException;

/** Сервис для сообщений. */
public interface MessageService {

  /**
   * Возвращает переписку текущего пользователя с другим.
   *
   * @param secondUserId идентификатор собеседника
   * @return список сообщений
   * @throws UserNotFoundException если один из пользователей не найден
   */
  Collection<Message> getConversation(Integer secondUserId) throws UserNotFoundException;

  /**
   * Отправляет сообщение от текущего пользователя другому, если они друзья.
   *
   * @param receiverId идентификатор собеседника
   * @param text текст сообщения
   * @return отправленное сообщение
   * @throws UserNotFoundException если один из пользователей не найден
   * @throws UsersNotFriendsException если пользователи не являются друзьями
   */
  Message sendMessage(Integer receiverId, String text)
      throws UserNotFoundException, UsersNotFriendsException;
}
