package ru.maxizenit.socialmediaapi.controller;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maxizenit.socialmediaapi.dto.PostDto;
import ru.maxizenit.socialmediaapi.dto.request.CreateOrEditPostRequestDto;
import ru.maxizenit.socialmediaapi.enm.PostSort;
import ru.maxizenit.socialmediaapi.entity.Post;
import ru.maxizenit.socialmediaapi.exception.InvalidAccessException;
import ru.maxizenit.socialmediaapi.exception.PostNotFoundException;
import ru.maxizenit.socialmediaapi.exception.UserNotFoundException;
import ru.maxizenit.socialmediaapi.service.PostService;

/** Контроллер постов. */
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  private final ModelMapper modelMapper;

  /**
   * Возвращает ленту активности текущего пользователя (посты пользователей, на которых он
   * подписан).
   *
   * @param offset номер страницы
   * @param limit количество элементов на странице
   * @param sort сортировка
   * @return ответ с коллекцией постов в ленте активностии HTTP-статусом 200
   * @throws UserNotFoundException если текущий пользователь не найден
   */
  @GetMapping
  public ResponseEntity<Collection<PostDto>> getFeed(
      @RequestParam Integer offset, @RequestParam Integer limit, @RequestParam PostSort sort)
      throws UserNotFoundException {
    Collection<PostDto> feed =
        postService.getFeed(offset, limit, sort).stream()
            .map(p -> modelMapper.map(p, PostDto.class))
            .toList();

    return new ResponseEntity<>(feed, HttpStatus.OK);
  }

  /**
   * Возвращает пост по его идентификатору.
   *
   * @param id идентификатор
   * @return ответ с постом и HTTP-статусом 200
   * @throws PostNotFoundException если пост не найден
   */
  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getPost(@PathVariable Integer id) throws PostNotFoundException {
    Post post = postService.getPostById(id);
    PostDto postDto = modelMapper.map(post, PostDto.class);

    return new ResponseEntity<>(postDto, HttpStatus.OK);
  }

  /**
   * Создаёт новый пост от текущего пользователя.
   *
   * @param createOrEditPostRequestDto DTO с информацией о создании поста (заголовок, текст)
   * @return ответ с новым постом и HTTP-статусом 201
   * @throws UserNotFoundException если текущий пользователь не найден
   */
  @PostMapping
  public ResponseEntity<PostDto> createPost(
      @RequestBody CreateOrEditPostRequestDto createOrEditPostRequestDto)
      throws UserNotFoundException {
    Post post =
        postService.createNewPost(
            createOrEditPostRequestDto.getTitle(), createOrEditPostRequestDto.getText());

    return new ResponseEntity<>(modelMapper.map(post, PostDto.class), HttpStatus.CREATED);
  }

  /**
   * Редактирует пост с заданным идентификатором.
   *
   * @param id идентификатор
   * @param createOrEditPostRequestDto DTO с информацией о редактировании поста (заголовок, текст)
   * @return ответ с отредактированным постом и HTTP-статусом 200
   * @throws UserNotFoundException если текущий пользователь не найден
   * @throws PostNotFoundException если пост не найден
   * @throws InvalidAccessException если текущий пользователь не имеет права редактировать пост
   */
  @PutMapping("/{id}")
  public ResponseEntity<PostDto> editPost(
      @PathVariable Integer id, @RequestBody CreateOrEditPostRequestDto createOrEditPostRequestDto)
      throws UserNotFoundException, PostNotFoundException, InvalidAccessException {
    Post post =
        postService.editPost(
            id, createOrEditPostRequestDto.getTitle(), createOrEditPostRequestDto.getText());
    PostDto postDto = modelMapper.map(post, PostDto.class);

    return new ResponseEntity<>(postDto, HttpStatus.OK);
  }

  /**
   * Удаляет пост с заданным идентификатором.
   *
   * @param id идентификатор
   * @return ответ с HTTP-статусом 204
   * @throws UserNotFoundException если текущий пользователь не найден
   * @throws PostNotFoundException если пост не найден
   * @throws InvalidAccessException если текущий пользователь не имеет права удалять пост
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<?> removePost(@PathVariable Integer id)
      throws UserNotFoundException, PostNotFoundException, InvalidAccessException {
    postService.removePostById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
