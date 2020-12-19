package br.com.devcave.jpa.controller;


import br.com.devcave.jpa.domain.Product;
import br.com.devcave.jpa.domain.Purchase;
import br.com.devcave.jpa.domain.User;
import br.com.devcave.jpa.repository.ProductRepository;
import br.com.devcave.jpa.repository.PurchaseRepository;
import br.com.devcave.jpa.repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@AllArgsConstructor
@RequestMapping("purchase")
public class PurchaseController {

    private PurchaseRepository purchaseRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    @GetMapping("history")
    public HttpEntity<?> getHistory(){
        return ResponseEntity.ok(purchaseRepository.findHistory());
    }

    @PostMapping("random")
    public void createRandomPurchase(){

        Faker faker = new Faker();

        List<User> userList = userRepository.findAll();
        List<Product> productList = productRepository.findAll();

        IntStream.range(1, faker.number().numberBetween(1, userList.size() - 1))
                .boxed()
                .forEach(i ->
                        IntStream.range(1, faker.number().numberBetween(2, 20))
                                .boxed()
                                .forEach(j ->
                                        purchaseRepository.save(
                                        Purchase.builder()
                                                .purchaseDate(faker.date().past(300, TimeUnit.DAYS).toInstant()
                                                        .atZone(ZoneId.systemDefault()).toLocalDateTime())
                                                .productList(
                                                        IntStream.range(1, faker.number().numberBetween(1, productList.size() - 1))
                                                                .boxed().map(x -> productList.get(x))
                                                                .collect(Collectors.toList())
                                                ).user(userList.get(i))
                                                .build())
                                )
                );


    }
}
