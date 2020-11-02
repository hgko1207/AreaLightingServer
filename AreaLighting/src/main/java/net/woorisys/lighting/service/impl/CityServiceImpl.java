package net.woorisys.lighting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.woorisys.lighting.domain.db.City;
import net.woorisys.lighting.domain.param.SearchParam;
import net.woorisys.lighting.repository.CityRepository;
import net.woorisys.lighting.service.CityService;

/**
 * 도시 관리 서비스
 * 
 * @author hgko
 *
 */
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Override
	public City get(Integer id) {
		return cityRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<City> getList() {
		return cityRepository.findAll();
	}

	@Override
	public boolean regist(City domain) {
		if (isNew(domain)) {
			return cityRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(City domain) {
		if (!isNew(domain)) {
			return cityRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		cityRepository.deleteById(id);
		return true;
	}

	private boolean isNew(City domain) {
		return !cityRepository.existsById(domain.getId());
	}

	@Override
	public List<City> getList(SearchParam param) {
		if (param.getName() != null && !param.getName().isEmpty()) {
			return cityRepository.findByNameContaining(param.getName());
		}
		return getList();
	}
}
