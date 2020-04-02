package com.hermestrade.corso.springboot.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hermestrade.corso.springboot.dto.UserDTO;
import com.hermestrade.corso.springboot.dto.UserIdDTO;
import com.hermestrade.corso.springboot.dto.UserPagedDTO;
import com.hermestrade.corso.springboot.entity.User;
import com.hermestrade.corso.springboot.exceptions.UserNotFoundExecption;
import com.hermestrade.corso.springboot.mapper.UserMapper;
import com.hermestrade.corso.springboot.model.filter.UserFilter;
import com.hermestrade.corso.springboot.repository.UserRepository;
import com.hermestrade.corso.springboot.service.UserService;
import com.hermestrade.utility.library.exception.BadRequestException;
import com.hermestrade.utility.library.exception.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

	UserMapper templateMapper = Mappers.getMapper(UserMapper.class);

	@Autowired
	UserRepository userRepository;

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getUsers(UserFilter userFilter) throws UserNotFoundExecption {

		// add example

		Example<User> exampletest = userFilter.toExample();

		// get Sort object
		Sort sort = userFilter.toSort();

		// sort age desc properties -> campo Statico!

		// Sort sort = Sort.by(Direction.ASC, "age");

		// get body becasue we have a object response entity. to get from it the list

		T response;

		List<User> users;

		// check if paged
		if (userFilter.getPageNumber() != null && userFilter.getPageSize() != null) {
			Pageable pageable = PageRequest.of(userFilter.getPageNumber(), userFilter.getPageSize(), sort);
			Page<User> userPaged = userRepository.findAll(exampletest, pageable);
			users = userPaged.getContent();

			List<UserDTO> usersDTOs = userPaged.get().map(u -> templateMapper.userEntityToUserDTO(u))
					.collect(Collectors.toList());

			response = (T) new UserPagedDTO(usersDTOs, userFilter.getPageNumber(), userFilter.getPageSize());

		} else {
			users = userRepository.findAll(exampletest, sort);

			List<UserDTO> usersDTOs = users.stream().map(u -> templateMapper.userEntityToUserDTO(u))
					.collect(Collectors.toList());

			response = (T) usersDTOs;

		}

		if (users.isEmpty())
			throw new UserNotFoundExecption("nothing Found");

		return response;
	}

	@Override
	public UserDTO findUserByID(Integer id) throws NotFoundException {
		Optional<User> userEntity = userRepository.findById(id);
		if (!userEntity.isPresent()) {
			throw new NotFoundException("The User with id " + id + " was not found");
		}
		User user = userEntity.get();
		UserDTO userDTOfound = templateMapper.userEntityToUserDTO(user);
		return userDTOfound;
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {

		User userEntity = templateMapper.userDTOToUserEntity(userDTO);

		// when create get the next INT id avaiable from the DB and set it on the new
		// USER
		// Long nextId = userRepository.getNextSequenceValue(userEntity.SEQUENCE_NAME);

		// userEntity.setId(nextId.intValue());

		userRepository.save(userEntity);

		return templateMapper.userEntityToUserDTO(userEntity);
	}

	// delete molto semplice
	@Override
	public UserIdDTO deleteUserBYID(Integer id) {
		Optional<User> userFound = userRepository.findById(id);
		UserIdDTO userIdDeleated = null;
		if (userFound.isPresent()) {
			userRepository.deleteById(id);
			userIdDeleated = new UserIdDTO(id);
		} else {
			throw new BadRequestException("User with id " + id + " was not found");
		}
		return userIdDeleated;
	}

	// update of resource, if not there return BadRequestException.

	@Override
	public UserDTO updateUser(Integer id, UserDTO user) throws BadRequestException {
		Optional<User> userFound = userRepository.findById(id);
		User userUpdated = null;

		if (userFound.isPresent()) {
			userUpdated = userFound.get();
			userUpdated.update(user);
			userRepository.save(userUpdated);

		} else {
			throw new BadRequestException("User with id " + id + " was not found");
		}
		return templateMapper.userEntityToUserDTO(userUpdated);

	}

	@Override
	public List<UserIdDTO> deleatedAll() {
		List<User> users = userRepository.findAll();
//		List<Integer> usersIds = useres.stream().map(User::getId).collect(Collectors.toList());
//		List<UserIdDTO> finalList = usersIds.stream().map(UserIdDTO::new).collect(Collectors.toList());

		List<UserIdDTO> finalList = users.stream().map(user -> new UserIdDTO(user.getId()))
				.collect(Collectors.toList());

		userRepository.deleteAll(users);
		return finalList;
	}

	// ____________________________________________________________________________________________________//

}
