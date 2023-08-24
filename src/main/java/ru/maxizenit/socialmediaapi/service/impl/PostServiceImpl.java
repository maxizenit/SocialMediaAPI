package ru.maxizenit.socialmediaapi.service.impl;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.maxizenit.socialmediaapi.enm.PostSort;
import ru.maxizenit.socialmediaapi.entity.Post;
import ru.maxizenit.socialmediaapi.entity.Subscription;
import ru.maxizenit.socialmediaapi.entity.User;
import ru.maxizenit.socialmediaapi.exception.InvalidAccessException;
import ru.maxizenit.socialmediaapi.exception.PostNotFoundException;
import ru.maxizenit.socialmediaapi.exception.UserNotFoundException;
import ru.maxizenit.socialmediaapi.repository.PostRepository;
import ru.maxizenit.socialmediaapi.service.PostService;
import ru.maxizenit.socialmediaapi.service.SubscriptionService;
import ru.maxizenit.socialmediaapi.service.UserService;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

  private final SubscriptionService subscriptionService;

  private final UserService userService;

  @Override
  public Collection<Post> getFeed(Integer offset, Integer limit, PostSort sort)
      throws UserNotFoundException {
    User subscriber = userService.getCurrentUser();
    Collection<User> authors =
        subscriptionService.getSubscriptionsBySubscriber(subscriber).stream()
            .map(Subscription::getAuthor)
            .toList();

    return postRepository
        .findAllByAuthorIn(authors, PageRequest.of(offset, limit, sort.getSortValue()))
        .getContent();
  }

  @Override
  public Post createNewPost(String title, String text) throws UserNotFoundException {
    User author = userService.getCurrentUser();

    Post post = new Post();
    post.setTimestamp(new Timestamp(System.currentTimeMillis()));
    post.setAuthor(author);
    post.setTitle(title);
    post.setText(text);

    return postRepository.save(post);
  }

  @Override
  public Post getPostById(Integer id) throws PostNotFoundException {
    return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
  }

  @Override
  public Post editPost(Integer id, String title, String text)
      throws PostNotFoundException, UserNotFoundException, InvalidAccessException {
    User user = userService.getCurrentUser();
    Post post = getPostById(id);

    if (!user.equals(post.getAuthor())) {
      throw new InvalidAccessException(user.getUsername(), "post", post.getId());
    }

    Optional.of(title).ifPresent(post::setTitle);
    Optional.of(text).ifPresent(post::setText);

    return postRepository.save(post);
  }

  @Override
  public void removePostById(Integer id)
      throws UserNotFoundException, PostNotFoundException, InvalidAccessException {
    User currentUser = userService.getCurrentUser();
    Post post = getPostById(id);

    if (currentUser.equals(post.getAuthor())) {
      postRepository.delete(post);
    } else {
      throw new InvalidAccessException(currentUser.getUsername(), "post", post.getId());
    }
  }
}
