package net.woorisys.lighting.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import net.woorisys.lighting.domain.db.Floor;

public interface FloorRepository extends DefaultRepository<Floor, Long> {

	@Transactional
	@Modifying
	void deleteByApartmentId(long id);

}
