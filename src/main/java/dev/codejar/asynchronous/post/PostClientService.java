package dev.codejar.asynchronous.post;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class PostClientService {

    private final WebClient webClient;

    public PostClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    //FindALL
    public Flux<Post> findAll(){
        return webClient
                .get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(Post.class);
    }


    //FINDBYID
    public Mono<Post> findById(Integer id){
        return webClient
                .get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(Post.class);
    }


    //CREATE
    public Mono<Post> create(Post post){
        return webClient
                .post()
                .uri("/posts")
                .bodyValue(post)
                .retrieve()
                .bodyToMono(Post.class);
    }


    //UPDATE
    public Mono<Post> update(Integer id, Post post){
        return webClient
                .put()
                .uri("/posts/{id}", id)
                .bodyValue(post)
                .retrieve()
                .bodyToMono(Post.class);
    }


    //DELETE
    public Mono<Post> detele(Integer id){
        return webClient
                .delete()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(Post.class);
    }




}
