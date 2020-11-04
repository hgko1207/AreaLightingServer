package net.woorisys.lighting.service;

import java.util.List;

import net.woorisys.lighting.domain.db.Apartment;

public interface ApartmentService extends CRUDService<Apartment, Long> {

	Apartment registToDomain(Apartment apartment);

	Apartment updateToDomain(Apartment apartment);

	List<Apartment> getList(int cityId);
	
	boolean deleteFromCity(int cityId);
}
