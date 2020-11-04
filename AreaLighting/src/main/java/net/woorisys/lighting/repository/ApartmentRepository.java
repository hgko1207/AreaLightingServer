package net.woorisys.lighting.repository;

import java.util.List;

import net.woorisys.lighting.domain.db.Apartment;

public interface ApartmentRepository extends DefaultRepository<Apartment, Long> {

//	@Transactional
//	@Modifying
//	@Query(value = "DELETE FROM tb_apartment WHERE id = ?1", nativeQuery = true)
//	void deleteById(Long id);

	List<Apartment> findByCityId(int cityId);

	void deleteByCityId(int cityId);
}
