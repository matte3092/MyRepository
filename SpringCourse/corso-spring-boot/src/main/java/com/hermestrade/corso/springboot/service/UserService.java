package com.hermestrade.corso.springboot.service;

import java.util.List;

import com.hermestrade.corso.springboot.dto.UserDTO;
import com.hermestrade.corso.springboot.dto.UserIdDTO;
import com.hermestrade.corso.springboot.exceptions.UserNotFoundExecption;
import com.hermestrade.corso.springboot.model.filter.UserFilter;

public interface UserService {
	public <T> T getUsers(UserFilter userFilter) throws UserNotFoundExecption;

	public  UserDTO findUserByID(Integer id);
	
	public UserDTO createUser(UserDTO user);
	
	public UserIdDTO deleteUserBYID(Integer id);
	
	public UserDTO updateUser(Integer id, UserDTO user);
	
	public List<UserIdDTO>  deleatedAll();
	
	
}
