package ru.maxizenit.socialmediaapi.service;

import java.util.Collection;
import ru.maxizenit.socialmediaapi.enm.PostSort;
import ru.maxizenit.socialmediaapi.entity.Post;
import ru.maxizenit.socialmediaapi.exception.InvalidAccessException;
import ru.maxizenit.socialmediaapi.exception.PostNotFoundException;
import ru.maxizenit.socialmediaapi.exception.UserNotFoundException;

/** Интерфейс для постов. */
public interface PostService {

  /**
   * Возвращает ленту постов для текущего пользователя.
   *
   * @param offset номер страницы
   * @param limit количество элементов на странице
   * @param sort сортировка
   * @return коллекция постов от пользователей, на которых подписан текущий
   * @throws UserNotFoundException
   */
  Collection<Post> getFeed(Integer offset, Integer limit, PostSort sort)
      throws UserNotFoundException;

  /**
   * Создаёт и возвращает новый пост.
   *
   * @param title заголовок
   * @param text текст поста
   * @return новый пост
   * @throws UserNotFoundException если текущий пользователь не найден
   */
  Post createNewPost(String title, String text) throws UserNotFoundException;

  /**
   * Возвращает пост по его идентификатору.
   *
   * @param id идентификатор
   * @return пост
   * @throws PostNotFoundException если пост не найден
   */
  Post getPostById(Integer id) throws PostNotFoundException;

  /**
   * Редактирует пост.
   *
   * @param id идентификатор
   * @param title заголовок поста
   * @param text текст поста
   * @return отредактированный пост
   * @throws PostNotFoundException если пост не найден
   * @throws UserNotFoundException если текущий пользователь не найден
   * @throws InvalidAccessException если текущий пользователь не имеет права редактировать данный
   *     пост
   */
  Post editPost(Integer id, String title, String text)
      throws PostNotFoundException, UserNotFoundException, InvalidAccessException;

  /**
   * Удаляет пост.
   *
   * @param id идентификатор
   * @throws UserNotFoundException если текущий пользователь не найден
   * @throws PostNotFoundException если пост не найден
   * @throws InvalidAccessException если текущий пользователь не имеет права удалять данный пост
   */
  void removePostById(Integer id)
      throws UserNotFoundException, PostNotFoundException, InvalidAccessException;
}
