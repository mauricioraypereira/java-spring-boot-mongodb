package com.raymauricio.mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raymauricio.mongodb.domain.User;
import com.raymauricio.mongodb.dto.UserDTO;
import com.raymauricio.mongodb.repositories.UserRepository;
import com.raymauricio.mongodb.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException("Usuário não foi encontrado."));
	}
	
	public User insert(User user) {
		return userRepository.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User fromDTO(UserDTO userDTO) {
		return new User(
				userDTO.getId(),
				userDTO.getName(),
				userDTO.getEmail());
	}
}
