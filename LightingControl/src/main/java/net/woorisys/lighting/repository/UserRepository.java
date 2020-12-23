package net.woorisys.lighting.repository;

import net.woorisys.lighting.domain.db.User;

public interface UserRepository extends DefaultRepository<User, Long> {

	User findByUserIdAndPassword(String id, String password);

}
