package am.itspace.demo.controller;

import am.itspace.demo.models.Author;
import am.itspace.demo.models.Role;
import am.itspace.demo.security.CurrentUser;
import am.itspace.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/login")
    public String login() {
        return "loginPage";
    }
/**
 * Ակտիվացնել երբ ունենանք /admin և /user
 * կոնտրոլյորները ու իրանց html-ները
 *
 * */
//    @GetMapping("/successLogin")
//    public String successLogin(@AuthenticationPrincipal CurrentUser currentUser) {
//        if (currentUser == null) {
//            return "redirect:/";
//        }
//        Author author = currentUser.getAuthor();
//        if (author.getRole() == Role.ADMIN) {
//            return "redirect:/admin";
//        } else {
//            return "redirect:/user";
//        }
//    }

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal Principal principal, Model model) {
       String userName = null;
        if (principal != null){
            userName = principal.getName();
        }
        model.addAttribute("allAuths", authorService.getAllAuthors());
        model.addAttribute("userName", userName);
        return "home";
    }

    @GetMapping("/accessIsDenied")
    public String deniedPage() {
        return "accessIsDenied";
    }

}
