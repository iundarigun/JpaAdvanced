package br.com.devcave.jpa.controller;

import br.com.devcave.jpa.domain.User;
import br.com.devcave.jpa.repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @PostMapping("random")
    public void createRandomUser(){
        Faker faker = new Faker();
        List<User> userList = IntStream.range(0, faker.number().numberBetween(5, 10))
                .boxed()
                .map(i -> User.builder()
                        .name(faker.name().fullName())
                        .build())
                .collect(Collectors.toList());
        userRepository.saveAll(userList);
    }

    @GetMapping("{id}")
    public HttpEntity<?> get(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);

        return ResponseEntity.ok(user);
    }

}
