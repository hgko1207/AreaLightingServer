package net.woorisys.lighting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.woorisys.lighting.domain.db.Admin;
import net.woorisys.lighting.repository.AdminRepository;
import net.woorisys.lighting.service.AdminService;

/**
 * 웹에서 사용자 관리 서비스
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin get(Integer id) {
		return adminRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Admin> getList() {
		return adminRepository.findAll();
	}

	@Override
	public boolean regist(Admin domain) {
		if (isNew(domain)) {
			return adminRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(Admin domain) {
		if (!isNew(domain)) {
			return adminRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		adminRepository.deleteById(id);
		return true;
	}

	private boolean isNew(Admin domain) {
		return !adminRepository.existsById(domain.getId());
	}

	@Override
	public Admin login(String userId, String password) {
		return adminRepository.findByUserIdAndPassword(userId, password);
	}
}
