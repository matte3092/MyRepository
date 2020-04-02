package com.hermestrade.corso.springboot.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Integer id;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(max = 10)
	private String name;

	@NotNull
	@Min(1)
	@Max(99)
	private Integer age;

}
