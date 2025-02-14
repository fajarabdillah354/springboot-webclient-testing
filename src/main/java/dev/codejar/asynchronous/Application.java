package dev.codejar.asynchronous;

import dev.codejar.asynchronous.post.Post;
import dev.codejar.asynchronous.post.PostClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}



	@Bean
	public CommandLineRunner commandLineRunner(PostClientService client, PostClientService postClientService){
		return args -> {
			Flux<Post> clientAll = postClientService.findAll();
			clientAll.subscribe(System.out::println);
		};
	}

}
