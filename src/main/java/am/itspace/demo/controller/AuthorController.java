package am.itspace.demo.controller;

import am.itspace.demo.models.Author;
import am.itspace.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @Value("${author.upload.dir}")
    private String uploadDir;

    @GetMapping("/")
    public String mainPage() {
        return "home";
    }

    @GetMapping(value = "/author/image")
    public @ResponseBody byte[] getImage(@RequestParam ("photoUrl") String photoUrl) throws IOException {
        InputStream in = new FileInputStream(uploadDir + File.separator + photoUrl);
        return IOUtils.toByteArray(in);
    }

    @PostMapping("/author/add")
    public String addAuthor(@ModelAttribute Author author, @RequestParam("image") MultipartFile image) throws IOException {
        if (image != null) {
            String photoUrl = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            File file = new File(uploadDir + File.separator + photoUrl);
            image.transferTo(file);
            author.setPhotoUrl(photoUrl);
        }
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