package com.example.repository;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.util.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> { 
	 
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM Users u WHERE u.email = ?1")
    public Boolean existsByEmail(String email);
	Users findByEmail(String email);

	public Users findById(Integer integer);
	public Users deleteById(Integer integer);
/*	@Query("SELECT * FROM Users u WHERE u.status = 1 and  u.status=11-jun-202")*/
	/*@Query("SELECT u FROM Users  u WHERE u.status = 1")*/
	/*@Query("select NEW com.example.pojo.UsersPojo(u.id, u.firstName) from Users  u WHERE u.status = 1") */
	@Query("SELECT u FROM Users  u WHERE u.status = 1")
	Collection<Users> findAllActiveUsers();
	@Query("SELECT u FROM Users  u WHERE u.status = 0")
	Collection<Users> findAlldeActiveUsers();
	
	@Query("SELECT u FROM Users  u WHERE u.month = ?1")
	Collection<Users> currentmonthBdayUserList(String month);
	
	@Query("SELECT  COUNT(u) FROM Users  u WHERE u.status = 1 and u.month = ?1")
	long countByBday(String month);
	@Query("SELECT  COUNT(u) FROM Users  u WHERE u.id = ?1")
	public long chekId(Integer integer);
	
	
	
}
