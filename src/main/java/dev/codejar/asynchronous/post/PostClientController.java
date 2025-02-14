package dev.codejar.asynchronous.post;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/posts")
public class PostClientController {

    private final PostClientService postClientService;


    public PostClientController(PostClientService postClientService) {
        this.postClientService = postClientService;
    }

    @GetMapping("")
    public Flux<Post> findAll(){
        return postClientService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Post> findById(@PathVariable Integer id){
        return postClientService.findById(id);
    }

}
