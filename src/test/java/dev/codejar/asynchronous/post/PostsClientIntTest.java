package dev.codejar.asynchronous.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsClientIntTest {

    @Autowired
    private PostClientService postClientService;

    @Test
    void testFindAll() {

        Flux<Post> postFlux = postClientService.findAll();
        StepVerifier.create(postFlux)
                .expectNextCount(100)
                .verifyComplete();

    }
}
