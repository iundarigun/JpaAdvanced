package br.com.devcave.jpa.controller;

import br.com.devcave.jpa.domain.User;
import br.com.devcave.jpa.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private UserRepository userRepository;

    @PostMapping
    public HttpEntity<?> create(@RequestBody User user){
        user.setId(null);
        user = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(user.getId());

        return ResponseEntity.created(location).build();
    }
}
