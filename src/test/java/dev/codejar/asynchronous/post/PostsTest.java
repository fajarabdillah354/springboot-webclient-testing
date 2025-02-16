package dev.codejar.asynchronous.post;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
public class PostsTest {

    @Mock
    WebClient webClient;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    private PostClientService postClientService;


    @BeforeEach
    void beforeEach() {
        postClientService = new PostClientService(webClient);
    }

    @Test
    void testFindAllPosts() {

        var hello = new Post(1, 1, "this is hello word", "hello body");
        var goodBye = new Post(2, 2, "this is goodbye", "goodbye body");

        Mockito.when(webClient.get()).thenReturn(requestHeadersUriSpec);
        Mockito.when(requestHeadersUriSpec.uri("/posts")).thenReturn(requestHeadersSpec);
        Mockito.when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        Mockito.when(responseSpec.bodyToFlux(Post.class)).thenReturn(Flux.just(hello, goodBye));

        Flux<Post> result = postClientService.findAll();

        StepVerifier.create(result)
                .expectNext(hello)
                .expectNext(goodBye)
                .verifyComplete();

    }


}
