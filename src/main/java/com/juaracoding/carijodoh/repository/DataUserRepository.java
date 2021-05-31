package com.juaracoding.carijodoh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.juaracoding.carijodoh.entity.DataUser;

public interface DataUserRepository extends JpaRepository<DataUser, Long> {
	@Query(value = "SELECT *\n"
			+ "from user\n"
			+ "Where (CASE "
			+ "WHEN 'username'=:type THEN username LIKE %:value% "
			+ "WHEN 'name'=:type THEN name LIKE %:value% "
			+ "WHEN 'gender'=:type THEN gender LIKE %:value% "
			+ "WHEN 'phone'=:type THEN phone LIKE %:value% "
			+ "WHEN 'age'=:type THEN age LIKE %:value% "
			+ "END)",nativeQuery=true)
	List<DataUser> findBySearchBy(@Param("type")String type,@Param("value")String value);

	@Query(value="SELECT * from user where name=?1 and phone=?2",nativeQuery = true)
	DataUser findByLogin(String name, String phone);
	
	DataUser findByName(String name); 
}
