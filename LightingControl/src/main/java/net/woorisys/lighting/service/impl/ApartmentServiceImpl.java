package net.woorisys.lighting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.woorisys.lighting.domain.db.Apartment;
import net.woorisys.lighting.repository.ApartmentRepository;
import net.woorisys.lighting.service.ApartmentService;

/**
 * 아파트 단지 관리 서비스
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class ApartmentServiceImpl implements ApartmentService {

	@Autowired
	private ApartmentRepository apartmentRepository;

	@Override
	public Apartment get(Long id) {
		return apartmentRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Apartment> getList() {
		return apartmentRepository.findAll();
	}

	@Override
	public boolean regist(Apartment domain) {
		if (isNew(domain)) {
			return apartmentRepository.save(domain) != null;
		} else {
			return false;
		}
	}

	@Override
	public boolean update(Apartment domain) {
		if (!isNew(domain)) {
			return apartmentRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Long id) {
		apartmentRepository.deleteById(id);
		return true;
	}

	private boolean isNew(Apartment domain) {
		return !apartmentRepository.existsById(domain.getId());
	}

	@Override
	public Apartment registToDomain(Apartment domain) {
		if (isNew(domain)) {
			return apartmentRepository.save(domain);
		} else {
			return null;
		}
	}

	@Override
	public Apartment updateToDomain(Apartment domain) {
		if (!isNew(domain)) {
			return apartmentRepository.save(domain);
		} else {
			return null;
		}
	}

	@Override
	public List<Apartment> getList(int cityId) {
		return apartmentRepository.findByCityId(cityId);
	}

	@Override
	public boolean deleteFromCity(int cityId) {
		apartmentRepository.deleteByCityId(cityId);
		return true;
	}

	@Override
	public boolean login(long id, String password) {
		return apartmentRepository.findByIdAndPassword(id, password) != null;
	}
}
