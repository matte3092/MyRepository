package com.hermestrade.corso.springboot.contoller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hermestrade.corso.springboot.dto.UserDTO;
import com.hermestrade.corso.springboot.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private MockMvc mvc;

	@Test
	public void testGetUsers() throws Exception {
		mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void testGetUsersPaged() throws Exception {
		// declare a multivalue linkedlist to add query params for paging and filter and
		// add the two fields
		MultiValueMap<String, String> queryparams = new LinkedMultiValueMap<>();

		queryparams.add("pageSize", "1");
		queryparams.add("pageNumber", "0");

		mvc.perform(get("/users").queryParams(queryparams).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isPartialContent());

	}

	@Test
	public void testUserFiltered() throws Exception {
		MultiValueMap<String, String> queryparams = new LinkedMultiValueMap<>();

		queryparams.add("name", "Qui");

		mvc.perform(get("/users").queryParams(queryparams).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void tesDeleteAllUsers() throws Exception {

		mvc.perform(delete("/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());


	}

	@Test
	public void testCreateUser() throws Exception {

		UserDTO user = new UserDTO();
		user.setName("Pluto");
		user.setAge(70);

		String json = new ObjectMapper().writeValueAsString(user);
		
		// save return in object. not returning void but the reponse of post
		
		MvcResult result = mvc.perform(
				post("/users").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();
		
		// get response
		MockHttpServletResponse resposne = result.getResponse();
		
		// dalla responss eho preso il contentuto
		String  responseAsString = resposne.getContentAsString();
		
		// object mapper convertiamo la respons in uno User dto
		
		UserDTO userCreated = new ObjectMapper().readValue(responseAsString, UserDTO.class);
		
		// ora se proviamo a fare la get su creato possiamo controllare effetticmante che il ritorno è quello che ci aspettaimo
		
		mvc.perform(get("/users/{id}" ,  userCreated.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value("Pluto")).andExpect(jsonPath("$.age").value(70))
		.andExpect(jsonPath("$.id").value(userCreated.getId()));
		
		
		
	}

	@Test
	public void testGetUserByIDok() throws Exception {

		UserDTO user1 = new UserDTO();
		user1.setAge(23);
		user1.setName("Qui");

		MvcResult result = mvc.perform(post("/users").content(new ObjectMapper().writeValueAsString(user1))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

		MockHttpServletResponse resposne = result.getResponse();
		
		// dalla responss eho preso il contentuto
		String  responseAsString = resposne.getContentAsString();
		
		// object mapper convertiamo la respons in uno User dto
		
		UserDTO userCreated = new ObjectMapper().readValue(responseAsString, UserDTO.class);
		
		// ora se proviamo a fare la get su creato possiamo controllare effetticmante che il ritorno è quello che ci aspettaimo
		
		mvc.perform(get("/users/{id}" ,  userCreated.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value("Qui")).andExpect(jsonPath("$.age").value(23))
		.andExpect(jsonPath("$.id").value(userCreated.getId()));

	}

	@Test
	public void testGetUserByIDnotOK() throws Exception {

		mvc.perform(get("/users/100").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

	}

	@Test
	public void testCreateUserOkAndGet() throws JsonProcessingException, Exception {

		// create should give 201
		UserDTO user = new UserDTO();
		user.setAge(88);
		user.setName("Zio Papero");

		MvcResult result = mvc.perform(post("/users").content(new ObjectMapper().writeValueAsString(user))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

		MockHttpServletResponse resposne = result.getResponse();
		
		// dalla responss eho preso il contentuto
		String  responseAsString = resposne.getContentAsString();
		
		// object mapper convertiamo la respons in uno User dto
		
		UserDTO userCreated = new ObjectMapper().readValue(responseAsString, UserDTO.class);
		
		// ora se proviamo a fare la get su creato possiamo controllare effetticmante che il ritorno è quello che ci aspettaimo
		
		mvc.perform(get("/users/{id}" ,  userCreated.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value("Zio Papero")).andExpect(jsonPath("$.age").value(88))
		.andExpect(jsonPath("$.id").value(userCreated.getId()));

	}

	@Test
	public void testCreateUserAgeMustNotBeNull() throws JsonProcessingException, Exception {

		// create should give 201
		UserDTO user = new UserDTO();
		user.setAge(null);
		user.setName("Zio Papero");

		mvc.perform(post("/users").content(new ObjectMapper().writeValueAsString(user))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	@Test
	public void testCreateUserAgeMustNotBeVaidationValues() throws JsonProcessingException, Exception {

		// age should be from 0 to 99
		UserDTO user = new UserDTO();
		user.setAge(-1);
		user.setName("Zio Papero");

		mvc.perform(post("/users").content(new ObjectMapper().writeValueAsString(user))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

		UserDTO user1 = new UserDTO();
		user1.setAge(100);
		user1.setName("Zio Papero");

		mvc.perform(post("/users").content(new ObjectMapper().writeValueAsString(user1))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

		UserDTO user3 = new UserDTO();
		user3.setAge(25);
		user3.setName("Zio Papero");

		mvc.perform(post("/users").content(new ObjectMapper().writeValueAsString(user3))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

	}

	@Test
	public void testCreateUserNameMustBeValidated() throws JsonProcessingException, Exception {

		UserDTO user = new UserDTO();
		user.setAge(32);
		user.setName(null);

		mvc.perform(post("/users").content(new ObjectMapper().writeValueAsString(user))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

		UserDTO user1 = new UserDTO();
		user1.setAge(100);
		user1.setName("longtexttttttttttttttttttt");

		mvc.perform(post("/users").content(new ObjectMapper().writeValueAsString(user1))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

		UserDTO user2 = new UserDTO();
		user2.setAge(25);
		user2.setName("");

		mvc.perform(post("/users").content(new ObjectMapper().writeValueAsString(user2))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
	
	// we can create a method that after a deleteAll recreates the DB as start. and resets the seuqence ID
	
	public void resetData() throws JsonProcessingException, Exception {
		
		
		userRepository.restartSequence("user_table_id_sequence", 1);
		
		UserDTO user1 = new UserDTO();
		user1.setAge(23);
		user1.setName("Qui");

		UserDTO user2 = new UserDTO();
		user2.setAge(23);
		user2.setName("Quo");

		UserDTO user3 = new UserDTO();
		user3.setAge(67);
		user3.setName("Topolino");

		UserDTO user4 = new UserDTO();
		user4.setAge(89);
		user4.setName("Paperino");

		mvc.perform(post("/users").content(new ObjectMapper().writeValueAsString(user1))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
		
		mvc.perform(post("/users").content(new ObjectMapper().writeValueAsString(user2))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
		
		mvc.perform(post("/users").content(new ObjectMapper().writeValueAsString(user3))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
		
		mvc.perform(post("/users").content(new ObjectMapper().writeValueAsString(user4))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
		
		
	}

}
