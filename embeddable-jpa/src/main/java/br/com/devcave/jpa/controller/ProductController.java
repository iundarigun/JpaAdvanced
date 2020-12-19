package br.com.devcave.jpa.controller;


import br.com.devcave.jpa.domain.Product;
import br.com.devcave.jpa.repository.ProductRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@AllArgsConstructor
@RequestMapping("product")
public class ProductController {

    private ProductRepository productRepository;

    @PostMapping("random")
    public void createRandomProducts(){
        Faker faker = new Faker();
        List<Product> productList = IntStream.range(0, faker.number().numberBetween(5, 10))
                .boxed()
                .map(i -> Product.builder()
                        .name(faker.commerce().productName())
                        .price(faker.number().randomDouble(2,10, 3000))
                        .build())
                .collect(Collectors.toList());
        productRepository.saveAll(productList);
    }
}
