package net.woorisys.lighting.service;

import net.woorisys.lighting.domain.db.User;

public interface UserService extends CRUDService<User, Integer> {

	User login(String userId, String password);
}
