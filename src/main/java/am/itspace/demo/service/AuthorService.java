package am.itspace.demo.service;


import am.itspace.demo.models.Author;
import am.itspace.demo.repository.AuthorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;

    public void save (Author author){
        authorRepo.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    public void deleteById(int id) {
        authorRepo.deleteById(id);
    }

    public Author getOne(int id) {
        return authorRepo.getOne(id);
    }

}
