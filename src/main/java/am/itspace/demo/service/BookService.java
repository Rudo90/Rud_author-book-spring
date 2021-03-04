package am.itspace.demo.service;

import am.itspace.demo.models.Book;
import am.itspace.demo.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;

    public void save(Book book){
        bookRepo.save(book);
    }

    public Book getOne(int id){
        return bookRepo.getOne(id);
    }

    public List<Book> getBookList (){
       return bookRepo.findAll();
    }

    public void deleteBookById(int id){
        bookRepo.deleteById(id);
    }




}
