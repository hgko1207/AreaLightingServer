package net.woorisys.lighting.service;

import java.util.List;

import net.woorisys.lighting.domain.db.User;
import net.woorisys.lighting.domain.param.SearchParam;

public interface UserService extends CRUDService<User, Long> {

	List<User> getList(SearchParam param);

}
