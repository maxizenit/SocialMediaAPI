package ru.maxizenit.socialmediaapi.repository;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxizenit.socialmediaapi.entity.Post;
import ru.maxizenit.socialmediaapi.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

  Page<Post> findAllByAuthorIn(Collection<User> authors, Pageable pageable);
}
