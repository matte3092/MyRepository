package com.hermestrade.corso.springboot.mapper;

import org.mapstruct.Mapper;

import com.hermestrade.corso.springboot.dto.UserDTO;
import com.hermestrade.corso.springboot.entity.User;

@Mapper
public interface UserMapper {

	/**
	 * User DTO to User entity.
	 *
	 * @param userDTO the user DTO
	 * @return the user entity
	 */
	User userDTOToUserEntity(UserDTO userDTO);

	/**
	 * User entity to user DTO.
	 *
	 * @param userEntity the user entity
	 * @return the user DTO
	 */
	UserDTO userEntityToUserDTO(User userEntity);
}
