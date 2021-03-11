package am.itspace.demo.controller;

import am.itspace.demo.models.Book;
import am.itspace.demo.service.entityService.AuthorService;
import am.itspace.demo.service.entityService.BookService;
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

    private final AuthorService authorService;
    private final BookService bookService;


    @PostMapping("/books/add")
    public String addBook(@ModelAttribute Book book){
        bookService.save(book);
        return "home";
    }

    @GetMapping("/books/all")
    public String seeAllBooks(ModelMap modelMap){
      List<Book> list = bookService.getBookList();
      modelMap.addAttribute("allBooks", list);
      return "allBooks";
    }

    @GetMapping("/books/edit/")
    public String editBook(@RequestParam(value = "id", required = false) Integer id, ModelMap modelMap){
        if (id != null) {
            modelMap.addAttribute("book", bookService.getOne(id));
            modelMap.addAttribute("allAuthors", authorService.getAllAuthors());
        } else {
            modelMap.addAttribute("book", new Book());
        }
        return "editBooks";
    }

    @GetMapping("/books/delete/")
    public String deleteBook (@RequestParam("id") int id){
        bookService.deleteBookById(id);
        return "redirect:/";
    }
}