package abhi.code.springbootwebfluxjjwt.service;

import abhi.code.springbootwebfluxjjwt.model.User;
import abhi.code.springbootwebfluxjjwt.model.security.Role;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
/*
 * This is just an example, you can load the user from the database from the repository.
 * 
 */
@Service
public class UserService {

    private Map<String, User> data;

    @PostConstruct
    public void init() {
        data = new HashMap<>();
        //username:passwowrd -> globiva:bfl_api
     //   data.put("globiva", new User("globiva", "78acmGCEXSAs+HVNY/U6ye4ggpqyzr7vbi9LI/1uFKw=", true, Arrays.asList(Role.ROLE_USER)));

        //username:passwowrd -> admin:bfl_api
        data.put("admin", new User("admin", "78acmGCEXSAs+HVNY/U6ye4ggpqyzr7vbi9LI/1uFKw=", true, Arrays.asList(Role.ROLE_ADMIN)));
    }

    public Mono<User> findByUsername(String username) {
        return Mono.justOrEmpty(data.get(username));
    }
}
