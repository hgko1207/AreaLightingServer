package net.woorisys.lighting.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import net.woorisys.lighting.domain.db.Apartment;

public interface ApartmentRepository extends DefaultRepository<Apartment, Long> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM tb_apartment WHERE id = ?1", nativeQuery = true)
	void deleteBy(Long id);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM tb_apartment WHERE id = ?1", nativeQuery = true)
	void deleteById(Long id);
}
