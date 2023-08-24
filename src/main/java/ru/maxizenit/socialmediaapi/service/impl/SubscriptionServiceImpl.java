package ru.maxizenit.socialmediaapi.service.impl;

import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maxizenit.socialmediaapi.entity.Subscription;
import ru.maxizenit.socialmediaapi.entity.User;
import ru.maxizenit.socialmediaapi.exception.UserAlreadySubscribedException;
import ru.maxizenit.socialmediaapi.exception.UserNotFoundException;
import ru.maxizenit.socialmediaapi.exception.UserNotSubscribedException;
import ru.maxizenit.socialmediaapi.repository.SubscriptionRepository;
import ru.maxizenit.socialmediaapi.service.SubscriptionService;
import ru.maxizenit.socialmediaapi.service.UserService;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;

  private final UserService userService;

  @Override
  public boolean areFriends(User first, User second) {
    return subscriptionRepository.findBySubscriberAndAuthor(first, second).isPresent()
        && subscriptionRepository.findByAuthorAndSubscriber(first, second).isPresent();
  }

  @Override
  public Subscription subscribe(Integer authorId)
      throws UserNotFoundException, UserAlreadySubscribedException {
    User currentUser = userService.getCurrentUser();
    User author = userService.getUserById(authorId);

    if (subscriptionRepository.findBySubscriberAndAuthor(currentUser, author).isPresent()) {
      throw new UserAlreadySubscribedException(currentUser.getUsername(), author.getUsername());
    }

    Subscription subscription = new Subscription();
    subscription.setSubscriber(currentUser);
    subscription.setAuthor(author);

    return subscriptionRepository.save(subscription);
  }

  @Override
  public void unsubscribe(Integer authorId)
      throws UserNotFoundException, UserNotSubscribedException {
    User currentUser = userService.getCurrentUser();
    User author = userService.getUserById(authorId);

    Optional<Subscription> subscription =
        subscriptionRepository.findBySubscriberAndAuthor(currentUser, author);

    if (subscription.isPresent()) {
      subscriptionRepository.delete(subscription.get());
    } else {
      throw new UserNotSubscribedException(currentUser.getUsername(), author.getUsername());
    }
  }

  @Override
  public Collection<Subscription> getSubscriptionsBySubscriber(User subscriber) {
    return subscriptionRepository.findAllBySubscriber(subscriber);
  }
}
