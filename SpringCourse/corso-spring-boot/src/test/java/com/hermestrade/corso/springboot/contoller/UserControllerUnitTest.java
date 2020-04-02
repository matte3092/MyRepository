package com.hermestrade.corso.springboot.contoller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.hermestrade.corso.springboot.controller.UserController;
import com.hermestrade.corso.springboot.dto.UserDTO;
import com.hermestrade.corso.springboot.exceptions.UserNotFoundExecption;
import com.hermestrade.corso.springboot.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerUnitTest {

	@MockBean
	UserServiceImpl userServiceImpl;

	@Autowired
	private MockMvc mvc;

	// we have only to thest the single methods.. prepassed
	@Test
	public void testGetUsers() throws Exception {

		UserDTO userDTO = new UserDTO();
		userDTO.setId(1);
		userDTO.setAge(29);
		userDTO.setName("Parino");

		List<UserDTO> users = new ArrayList<>();
		users.add(userDTO);

		when(userServiceImpl.getUsers(Mockito.any())).thenReturn(users);

		mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void testGetUsersNotFoundExeception() throws Exception {

		when(userServiceImpl.getUsers(Mockito.any())).thenThrow(UserNotFoundExecption.class);

		mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

	}

}
