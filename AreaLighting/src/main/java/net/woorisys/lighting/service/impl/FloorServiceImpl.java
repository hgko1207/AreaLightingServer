package net.woorisys.lighting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.woorisys.lighting.domain.db.Floor;
import net.woorisys.lighting.repository.FloorRepository;
import net.woorisys.lighting.service.FloorService;

/**
 * 지하 층 관리 서비스
 * 
 * @author hgko
 *
 */
@Service
public class FloorServiceImpl implements FloorService {

	@Autowired
	private FloorRepository floorRepository;

	@Override
	public Floor get(Long id) {
		return floorRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Floor> getList() {
		return floorRepository.findAll();
	}

	@Override
	public boolean regist(Floor domain) {
		if (isNew(domain)) {
			return floorRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(Floor domain) {
		if (!isNew(domain)) {
			return floorRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Long id) {
		floorRepository.deleteById(id);
		return true;
	}

	private boolean isNew(Floor domain) {
		return !floorRepository.existsById(domain.getId());
	}
}
