package com.hermestrade.corso.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hermestrade.corso.springboot.dto.UserDTO;
import com.hermestrade.corso.springboot.dto.UserIdDTO;
import com.hermestrade.corso.springboot.exceptions.UserNotFoundExecption;
import com.hermestrade.corso.springboot.model.filter.UserFilter;
import com.hermestrade.corso.springboot.service.UserService;
import com.hermestrade.utility.library.exception.BadRequestException;
import com.hermestrade.utility.library.exception.NotFoundException;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	// simple get mapping

	// nel controller sortDirections e SortProperties vanno sempre settare di deault
	// per un tipo

	@GetMapping("/users")
	public <T> ResponseEntity<T> getUsers(@RequestParam(required = false) String name, Integer age,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNumber,
			@RequestParam(required = false, defaultValue = "desc") String sortDirections,
			@RequestParam(required = false, defaultValue = "id") String[] sortProperties) throws UserNotFoundExecption {

		UserFilter userFilter = new UserFilter(name, age, pageNumber, pageSize, sortDirections, sortProperties);

		T users = userService.getUsers(userFilter);
		
		
		ResponseEntity<T> response;

		if (pageNumber != null && pageSize != null) {
			response = new ResponseEntity<>(users, HttpStatus.PARTIAL_CONTENT);
		} else {
			response = new ResponseEntity<>(users, HttpStatus.OK);
		}
		return response;

	}

	// simple get mapping by id

	@GetMapping("/users/{id}")
	public UserDTO getUserById(@PathVariable(name = "id") Integer id) throws NotFoundException {
		return userService.findUserByID(id);
	}

	// create a new user

	@PostMapping("/users")
	public ResponseEntity<UserDTO> creareUser(@RequestBody(required = true) @Valid UserDTO user) {
		user = userService.createUser(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	// update a User by Id

	@PutMapping("/users/{id}")
	public UserDTO updateUser(@PathVariable(name = "id") Integer id,
			@RequestBody(required = true) @Valid UserDTO user) {
		return userService.updateUser(id, user);

	}

	// delete a user By id using responsentity dal costuttore oggetto.

	@DeleteMapping("/users/{id}")
	@ResponseBody
	public ResponseEntity<UserIdDTO> deleteUserById(@PathVariable(name = "id") Integer id) throws BadRequestException {

		UserIdDTO userdeleated = userService.deleteUserBYID(id);

		return new ResponseEntity<>(userdeleated, HttpStatus.OK);

	}

	// delete all . output is Ok. with a listOfUserIdDTO
	@DeleteMapping("/users")
	public ResponseEntity<List<UserIdDTO>> deleteAllUsers() {

		List<UserIdDTO> userdeleated = userService.deleatedAll();

		return ResponseEntity.ok(userdeleated);

	}
}
