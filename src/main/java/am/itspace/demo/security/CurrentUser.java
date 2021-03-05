package am.itspace.demo.security;

import am.itspace.demo.models.Author;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {

    private Author author;

    public CurrentUser(Author author) {
        super(author.getUsername(), author.getPassword(), AuthorityUtils.createAuthorityList(author.getRole().name()));
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }
}
