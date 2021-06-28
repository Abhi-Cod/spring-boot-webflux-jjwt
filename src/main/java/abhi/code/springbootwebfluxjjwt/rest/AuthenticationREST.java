package abhi.code.springbootwebfluxjjwt.rest;

import abhi.code.springbootwebfluxjjwt.model.security.AuthRequest;
import abhi.code.springbootwebfluxjjwt.security.JWTUtil;
import abhi.code.springbootwebfluxjjwt.security.PBKDF2Encoder;
import abhi.code.springbootwebfluxjjwt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class AuthenticationREST {

    private final JWTUtil jwtUtil;
    private final PBKDF2Encoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody AuthRequest ar) {
        return userService.findByUsername(ar.getUsername())
            .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()) && "password".equals(ar.getGrant_type()))
            .map(userDetails -> ResponseEntity.ok(jwtUtil.generateToken(userDetails)))
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));  
    }

}
