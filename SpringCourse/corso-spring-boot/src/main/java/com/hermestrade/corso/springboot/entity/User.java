package com.hermestrade.corso.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.hermestrade.corso.springboot.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "table_user")

@Entity
public class User {

	// crea la seuqenza name dal DB
	
	//public static final String SEQUENCE_NAME = "user_table_id_sequence";
			
			
			
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_table_id_sequence")
	@SequenceGenerator(name = "user_table_id_sequence", sequenceName = "user_table_id_sequence", allocationSize = 1)
	@Column(name = "id_user", nullable = false, updatable = false)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "age", nullable = false)
	private Integer age;
	
	public void update(UserDTO newuser) {
		this.id = newuser.getId();
		this.name = newuser.getName();
		this.age = newuser.getAge();
		
	}
	
	
}
