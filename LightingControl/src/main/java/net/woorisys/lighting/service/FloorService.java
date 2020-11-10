package net.woorisys.lighting.service;

import java.util.List;

import net.woorisys.lighting.domain.db.Floor;

public interface FloorService extends CRUDService<Floor, Long> {

	boolean deleteFromApartment(long id);

	List<Floor> getList(long apartmentId);

}
