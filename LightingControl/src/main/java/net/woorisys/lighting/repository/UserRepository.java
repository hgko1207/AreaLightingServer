package net.woorisys.lighting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import net.woorisys.lighting.domain.db.User;

public interface UserRepository extends DefaultRepository<User, Long> {

	User findByUserIdAndPassword(String id, String password);

	List<User> findByUserIdContaining(String name);

	@Query(value = "SELECT * FROM tb_user WHERE city_id = ?1", nativeQuery = true)
	List<User> findByCityId(int cityId);

	@Query(value = "SELECT * FROM tb_user WHERE city_id = ?1 And user_id like %?2%", nativeQuery = true)
	List<User> findByCityIdAndUserIdContaining(int cityId, String userId);
}
