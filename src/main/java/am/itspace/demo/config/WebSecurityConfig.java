package am.itspace.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/books/edit/").authenticated()
                .antMatchers(HttpMethod.GET, "/books/delete/").authenticated()
                .antMatchers(HttpMethod.GET, "/author/edit/").authenticated()
                .antMatchers(HttpMethod.GET, "/author/delete/").authenticated();
    }
}