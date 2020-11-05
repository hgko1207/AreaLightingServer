package net.woorisys.lighting.repository;

import java.util.List;

import net.woorisys.lighting.domain.db.City;

public interface CityRepository extends DefaultRepository<City, Integer> {

	List<City> findByNameContaining(String name);

}
