package ru.maxizenit.socialmediaapi.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.maxizenit.socialmediaapi.entity.Message;
import ru.maxizenit.socialmediaapi.entity.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

  @Query(
      "SELECT m FROM Message m WHERE ((m.sender = :first AND m.receiver = :second) OR (m.sender = :second AND m.receiver = :first)) ORDER BY m.timestamp DESC")
  Collection<Message> findConversationByUsers(User first, User second);
}
