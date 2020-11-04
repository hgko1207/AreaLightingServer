package net.woorisys.lighting.service;

import net.woorisys.lighting.domain.db.Apartment;

public interface ApartmentService extends CRUDService<Apartment, Long> {

	Apartment registToDomain(Apartment apartment);

	Apartment updateToDomain(Apartment apartment);
}
