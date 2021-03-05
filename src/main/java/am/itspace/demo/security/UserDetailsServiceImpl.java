package am.itspace.demo.security;

import am.itspace.demo.models.Author;
import am.itspace.demo.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AuthorRepo authorRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       Author author = authorRepo.findByUsername(s).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new CurrentUser(author);
    }
}
