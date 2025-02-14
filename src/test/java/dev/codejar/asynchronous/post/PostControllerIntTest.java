package dev.codejar.asynchronous.post;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerIntTest {


    @Autowired
    WebClient webClient;


    @Test
    void testShouldBePosts() {

        webClient
                .get()
                .uri("/posts")
                .retrieve()
                .onStatus(HttpStatusCode::is2xxSuccessful, ClientResponse::createException)
                .bodyToFlux(Post.class)
                .subscribe();
    }



}
