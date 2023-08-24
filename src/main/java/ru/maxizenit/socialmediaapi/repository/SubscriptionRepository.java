package ru.maxizenit.socialmediaapi.repository;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxizenit.socialmediaapi.entity.Subscription;
import ru.maxizenit.socialmediaapi.entity.User;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

  Optional<Subscription> findBySubscriberAndAuthor(User subscriber, User author);

  Optional<Subscription> findByAuthorAndSubscriber(User author, User subscriber);

  Collection<Subscription> findAllBySubscriber(User subscriber);
}
