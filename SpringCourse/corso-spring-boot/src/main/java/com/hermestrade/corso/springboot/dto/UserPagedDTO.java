package com.hermestrade.corso.springboot.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserPagedDTO {

	private List<UserDTO> userList;
	
	private Integer pageNumber;

	private Integer pageSize;
	
	}

