package dev.codejar.asynchronous.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.io.IOException;

public class PostsClientMockWebServerTest {

    private static MockWebServer mockWebServer;

    private PostClientService postClientService;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @BeforeAll
    static void beforeAll() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void afterAll() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void inialize(){
        String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
        postClientService = new PostClientService(WebClient.builder().baseUrl(baseUrl).build());
    }


    @Test
    void testFindAllPosts() throws JsonProcessingException, InterruptedException {

        var hello = new Post(1, 1, "this is hello word", "hello body");
        var goodBye = new Post(2, 2, "this is goodbye", "goodbye body");
        var jsonResponse = objectMapper.writeValueAsString(new Post[]{hello, goodBye});

        //setup mock
        mockWebServer.enqueue(new MockResponse()
                .setBody(jsonResponse)
                .addHeader("Content-Type", "application/json")
        );

        //memanggil service
        StepVerifier.create(postClientService.findAll())
                .expectNext(hello)
                .expectNext(goodBye)
                .verifyComplete();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals("/posts", recordedRequest.getPath());

    }
}
