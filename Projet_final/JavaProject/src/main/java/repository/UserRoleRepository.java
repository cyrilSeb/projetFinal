package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

	@Query("select ur.role from UserRole ur where ur.user.username=?1")
	List<String> findCustomRoleByUserName(String username);
}
