package io.kindler.controller;

import io.kindler.entity.User;
import io.kindler.predicate.UserPredicate;
import io.kindler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by kyj on 2016-10-17.
 */
@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		User result = userRepository.findOne(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userRepository.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public ResponseEntity postUser(@RequestBody User user) {
		User result = userRepository.save(user);
		Integer id = result.getId();
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.LOCATION, UriComponentsBuilder.fromUriString("/user/{id}").buildAndExpand(id).toUriString());

		return new ResponseEntity(null, headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
	public ResponseEntity deleteUser(@PathVariable Integer id) {
		User result = userRepository.findOne(id);
		if (result != null) {
			userRepository.delete(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity(HttpStatus.NOT_MODIFIED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user/search")
	public ResponseEntity findUserByName(@RequestBody User user) {
		List<User> users = userRepository.findAll(UserPredicate.search(user));
		return new ResponseEntity(users, HttpStatus.OK);
	}
}
