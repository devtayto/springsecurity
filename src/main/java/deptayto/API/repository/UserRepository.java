package deptayto.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deptayto.API.entity.Users;
@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	/**
	 * find by username from database
	 * @param userName
	 * @return Users
	 */
	public Users findOneByUserName(String userName);
}
