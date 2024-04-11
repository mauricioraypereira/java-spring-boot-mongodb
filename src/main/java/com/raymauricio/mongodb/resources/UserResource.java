package com.raymauricio.mongodb.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raymauricio.mongodb.domain.User;
import com.raymauricio.mongodb.dto.UserDTO;
import com.raymauricio.mongodb.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> userList = userService.findAll();
		List<UserDTO> userListDTO = userList.stream()
				.map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(userListDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User userFounded = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(userFounded));
	}
}
