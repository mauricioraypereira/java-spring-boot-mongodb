package com.raymauricio.mongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.raymauricio.mongodb.domain.Post;
import com.raymauricio.mongodb.domain.User;
import com.raymauricio.mongodb.dto.AuthorDTO;
import com.raymauricio.mongodb.dto.CommentDTO;
import com.raymauricio.mongodb.repositories.PostRepository;
import com.raymauricio.mongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com");
		User u2 = new User(null, "Alex Green", "alex@gmail.com");
		User u3 = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Post post1 = new Post(null, 
				sdf.parse("21/03/2024"), 
				"Partiu viagem", 
				"Vou viajar para Rio grande do Sul, Abraços!", 
				new AuthorDTO(u1));
		Post post2 = new Post(null, 
				sdf.parse("23/03/2024"), 
				"Bom dia", 
				"Acordei da aqui no RS!", 
				new AuthorDTO(u1));
		
		CommentDTO c1 = new CommentDTO("Boa viagem!",
				sdf.parse("21/03/2018"),
				new AuthorDTO(u2));
		CommentDTO c2 = new CommentDTO("Aproveite!",
				sdf.parse("22/03/2018"),
				new AuthorDTO(u3));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!",
				sdf.parse("23/03/2018"),
				new AuthorDTO(u2));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		u1.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(u1);
	}
}
