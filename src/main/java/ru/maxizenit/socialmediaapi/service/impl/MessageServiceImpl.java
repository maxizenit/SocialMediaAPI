package ru.maxizenit.socialmediaapi.service.impl;

import java.sql.Timestamp;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maxizenit.socialmediaapi.entity.Message;
import ru.maxizenit.socialmediaapi.entity.User;
import ru.maxizenit.socialmediaapi.exception.UserNotFoundException;
import ru.maxizenit.socialmediaapi.exception.UsersNotFriendsException;
import ru.maxizenit.socialmediaapi.repository.MessageRepository;
import ru.maxizenit.socialmediaapi.service.MessageService;
import ru.maxizenit.socialmediaapi.service.SubscriptionService;
import ru.maxizenit.socialmediaapi.service.UserService;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

  private final MessageRepository messageRepository;

  private final UserService userService;

  private final SubscriptionService subscriptionService;

  @Override
  public Collection<Message> getConversation(Integer secondUserId) throws UserNotFoundException {
    User currentUser = userService.getCurrentUser();
    User secondUser = userService.getUserById(secondUserId);

    return messageRepository.findConversationByUsers(currentUser, secondUser);
  }

  @Override
  public Message sendMessage(Integer receiverId, String text)
      throws UserNotFoundException, UsersNotFriendsException {
    User currentUser = userService.getCurrentUser();
    User receiver = userService.getUserById(receiverId);

    if (!subscriptionService.areFriends(currentUser, receiver)) {
      throw new UsersNotFriendsException(currentUser.getUsername(), receiver.getUsername());
    }

    Message message = new Message();
    message.setTimestamp(new Timestamp(System.currentTimeMillis()));
    message.setSender(currentUser);
    message.setReceiver(receiver);
    message.setText(text);

    return messageRepository.save(message);
  }
}
