package com.hermestrade.corso.springboot.model.filter;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.hermestrade.corso.springboot.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserFilter {

	private String name;
	private Integer age;
	private Integer pageNumber;
	private Integer pageSize;
	private String sortDirections;
	private String[] sortProperties;

	public Example<User> toExample() {
		User user = new User();

		user.setName(this.getName());
		user.setAge(this.getAge());

		return Example.of(user);

	}

	public Sort toSort() {
		// dinamic Sorting. Direction is a Enum, we have string casted

		Direction direction = Direction.fromString(this.getSortDirections());
		return Sort.by(direction, this.getSortProperties());

	}

}
