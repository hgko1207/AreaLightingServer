package net.woorisys.lighting.repository;

import net.woorisys.lighting.domain.db.Admin;

public interface AdminRepository extends DefaultRepository<Admin, Integer> {

	Admin findByUserIdAndPassword(String userId, String password);

}
