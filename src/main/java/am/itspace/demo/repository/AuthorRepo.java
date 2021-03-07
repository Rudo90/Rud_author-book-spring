package am.itspace.demo.repository;

import am.itspace.demo.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthorRepo extends JpaRepository<Author, Integer> {

    Optional<Author> findByUsername(String username);
}
