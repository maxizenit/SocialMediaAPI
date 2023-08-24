package ru.maxizenit.socialmediaapi.controller;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maxizenit.socialmediaapi.dto.MessageDto;
import ru.maxizenit.socialmediaapi.dto.request.SendMessageRequestDto;
import ru.maxizenit.socialmediaapi.entity.Message;
import ru.maxizenit.socialmediaapi.exception.UserNotFoundException;
import ru.maxizenit.socialmediaapi.exception.UsersNotFriendsException;
import ru.maxizenit.socialmediaapi.service.MessageService;

/** Контроллер сообщений. */
@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;

  private final ModelMapper modelMapper;

  /**
   * Возвращает переписку текущего пользователя по идентификатору собеседника.
   *
   * @param companionId идентификатор собеседника
   * @return ответ с коллекцией сообщений в переписке и HTTP-статусом 200
   * @throws UserNotFoundException если одного из пользователей не существует
   */
  @GetMapping
  public ResponseEntity<Collection<MessageDto>> getConversation(@RequestParam Integer companionId)
      throws UserNotFoundException {
    Collection<MessageDto> messages =
        messageService.getConversation(companionId).stream()
            .map(message -> modelMapper.map(message, MessageDto.class))
            .toList();

    return new ResponseEntity<>(messages, HttpStatus.OK);
  }

  /**
   * Отправляет сообщение от текущего пользователя другому, если они - друзья.
   *
   * @param sendMessageRequestDto DTO с информацией об отправке сообщения (идентификатор получателя,
   *     текст)
   * @return ответ с отправленным сообщением и HTTP-статусом 201
   * @throws UserNotFoundException если одного из пользователей не существует
   * @throws UsersNotFriendsException если пользователи не являются друзьями
   */
  @PostMapping
  public ResponseEntity<MessageDto> sendMessage(
      @RequestBody SendMessageRequestDto sendMessageRequestDto)
      throws UserNotFoundException, UsersNotFriendsException {
    Message message =
        messageService.sendMessage(
            sendMessageRequestDto.getReceiverId(), sendMessageRequestDto.getText());
    MessageDto messageDto = modelMapper.map(message, MessageDto.class);

    return new ResponseEntity<>(messageDto, HttpStatus.CREATED);
  }
}
