package net.woorisys.lighting.repository;

import net.woorisys.lighting.domain.db.User;

public interface UserRepository extends DefaultRepository<User, Integer> {

	User findByUserIdAndPassword(String userId, String password);

}
