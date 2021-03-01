package am.itspace.demo.controller;

import am.itspace.demo.models.Author;
import am.itspace.demo.models.Book;
import am.itspace.demo.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepo bookRepo;

    @PostMapping("/books/add")
    public String addBook(@RequestParam("title") String title,
                          @RequestParam("description") String description,
                          @RequestParam("price") double price,
                          @RequestParam("author_id") Author author,
                          ModelMap modelMap){
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setPrice(price);
        book.setAuthor(author);
        bookRepo.save(book);
        String msg = "Book was added successfully!";
        modelMap.addAttribute("msg", msg);
        return "home";
    }

    @GetMapping("/books/all")
    public String seeAllBooks(ModelMap modelMap){
      List<Book> list = bookRepo.findAll();
      modelMap.addAttribute("allBooks", list);
      return "allBooks";
    }

    @GetMapping("/books/edit")
    public String editBook(@RequestParam(value = "id", required = false) Integer id, ModelMap modelMap){
        if (id != null) {
            modelMap.addAttribute("book", bookRepo.getOne(id));
        }
//        } else {
//            modelMap.addAttribute("book", new Book());
//        }
        return "editBook";
    }

    @GetMapping("/books/delete")
    public String deleteBook (@RequestParam("id") int id){
        bookRepo.deleteById(id);
        return "redirect:/";
    }






}
