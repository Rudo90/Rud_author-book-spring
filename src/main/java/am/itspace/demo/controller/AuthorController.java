package am.itspace.demo.controller;

import am.itspace.demo.models.Author;
import am.itspace.demo.repository.AuthorRepo;
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
public class AuthorController {

    private final AuthorRepo authorRepo;

    @GetMapping("/")
    public String mainPage() {
        return "home";
    }

    @PostMapping("/author/add")
    public String addAuthor(@ModelAttribute Author author, ModelMap modelMap) {
        authorRepo.save(author);
        String msg = "Author was added successfully!";
        modelMap.addAttribute("message", msg);
        return "home";
    }

    @GetMapping("/author/all")
    public String seeAllAuthors(ModelMap modelMap) {
        List<Author> list = authorRepo.findAll();
        modelMap.addAttribute("allAuthors", list);
        return "allAuthors";
    }

    @GetMapping("/author/delete")
    public String deleteAuthor(@RequestParam("id") int id) {
        authorRepo.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/author/edit")
    public String editAuthor (@RequestParam(value = "id", required = false) Integer id, ModelMap modelMap){
        if (id != null){
            modelMap.addAttribute("author", authorRepo.getOne(id));
        }
        return "editAuthor";

    }


}
