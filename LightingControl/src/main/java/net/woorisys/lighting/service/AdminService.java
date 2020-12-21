package net.woorisys.lighting.service;

import net.woorisys.lighting.domain.db.Admin;

public interface AdminService extends CRUDService<Admin, Integer> {

	Admin login(String userId, String password);
}
