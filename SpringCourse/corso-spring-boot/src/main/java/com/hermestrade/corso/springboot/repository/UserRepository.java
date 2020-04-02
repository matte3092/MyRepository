package com.hermestrade.corso.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hermestrade.corso.springboot.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value = "SELECT nextval(?1)", nativeQuery=true)
	Long getNextSequenceValue(String sequenceName);

	@Query(value = "alter sequence (?1) restart with (?2)", nativeQuery=true)
	void restartSequence(String sequenceName, Integer restartValue); 
		
		
	
}
