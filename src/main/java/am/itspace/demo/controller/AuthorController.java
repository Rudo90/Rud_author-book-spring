package am.itspace.demo.controller;

import am.itspace.demo.models.Author;
import am.itspace.demo.repository.AuthorRepo;
import am.itspace.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorRepo authorRepo;
    private final PasswordEncoder passwordEncoder;

    @Value("${author.upload.dir}")
    private String uploadDir;

    @GetMapping(value = "/author/image")
    public @ResponseBody byte[] getImage(@RequestParam ("photoUrl") String photoUrl) throws IOException {
        InputStream in = new FileInputStream(uploadDir + File.separator + photoUrl);
        return IOUtils.toByteArray(in);
    }

    @PostMapping("/author/add")
    public String addAuthor(@ModelAttribute Author author, @RequestParam("image") MultipartFile image) throws IOException {
       Optional<Author> byUsername = authorRepo.findByUsername(author.getUsername());
       if (byUsername.isPresent()){
           return "redirect:/?message=User already exist";
       }
        if (image != null) {
            String photoUrl = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            File file = new File(uploadDir + File.separator + photoUrl);
            image.transferTo(file);
            author.setPhotoUrl(photoUrl);
        }
        author.setPassword(passwordEncoder.encode(author.getPassword()));
        authorService.save(author);
        return "home";
    }

    @GetMapping("/author/all")
    public String seeAllAuthors(ModelMap modelMap) {
        List<Author> list = authorService.getAllAuthors();
        modelMap.addAttribute("allAuthors", list);
        return "allAuthors";
    }

    @GetMapping("/author/delete/")
    public String deleteAuthor(@RequestParam("id") int id) {
        authorService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/author/edit/")
    public String editAuthor (@RequestParam(value = "id", required = false) Integer id, ModelMap modelMap){
        if (id != null){
            modelMap.addAttribute("author", authorService.getOne(id));
        }else {
            modelMap.addAttribute("author", new Author());
        }
        return "editAuthor";
    }

}