package com.hermestrade.corso.springboot.mapper;

import com.hermestrade.corso.springboot.dto.UserDTO;
import com.hermestrade.corso.springboot.entity.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-19T15:38:48+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDTOToUserEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setName( userDTO.getName() );
        user.setAge( userDTO.getAge() );

        return user;
    }

    @Override
    public UserDTO userEntityToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        if ( user.getId() != null ) {
            userDTO.setId( user.getId() );
        }
        userDTO.setName( user.getName() );
        userDTO.setAge( user.getAge() );

        return userDTO;
    }
}
