package am.itspace.demo.repository;

import am.itspace.demo.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AuthorRepo extends JpaRepository<Author, Integer> {

    List<Author> findAllById(Integer author);
    List<Author> findAuthorByNameContaining(String name);
}
