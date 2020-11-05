package net.woorisys.lighting.service;

import java.util.List;

import net.woorisys.lighting.domain.db.City;
import net.woorisys.lighting.domain.param.SearchParam;

public interface CityService extends CRUDService<City, Integer> {

	List<City> getList(SearchParam param);

}
