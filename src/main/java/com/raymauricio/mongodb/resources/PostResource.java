package com.raymauricio.mongodb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raymauricio.mongodb.domain.Post;
import com.raymauricio.mongodb.resources.util.URL;
import com.raymauricio.mongodb.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

	@Autowired
	private PostService postService;
			
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post postFounded = postService.findById(id);
		return ResponseEntity.ok().body(postFounded);
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "title", defaultValue = "") String title) {
		title = URL.decodeParam(title);
		List<Post> listPostsFounded = postService.findByTitle(title);
		return ResponseEntity.ok().body(listPostsFounded);
	}
}
