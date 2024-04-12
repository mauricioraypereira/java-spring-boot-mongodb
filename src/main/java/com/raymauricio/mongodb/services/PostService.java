package com.raymauricio.mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raymauricio.mongodb.domain.Post;
import com.raymauricio.mongodb.repositories.PostRepository;
import com.raymauricio.mongodb.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException("Postagem não foi encontrada."));
	}
	
	public List<Post> findByTitle(String title) {
		return postRepository.findByTitle(title);
	}
}
