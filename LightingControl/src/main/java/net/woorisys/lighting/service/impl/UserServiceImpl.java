package net.woorisys.lighting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.woorisys.lighting.domain.db.User;
import net.woorisys.lighting.domain.param.SearchParam;
import net.woorisys.lighting.repository.UserRepository;
import net.woorisys.lighting.service.UserService;

/**
 * 사용자 관리 서비스
 * 
 * @author hgko
 *
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User get(Long id) {
		return userRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> getList() {
		return userRepository.findAll();
	}

	@Override
	public boolean regist(User domain) {
		if (isNew(domain)) {
			return userRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(User domain) {
		if (!isNew(domain)) {
			return userRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Long id) {
		userRepository.deleteById(id);
		return true;
	}

	private boolean isNew(User domain) {
		return !userRepository.existsById(domain.getId());
	}

	@Override
	public List<User> getList(SearchParam param) {
		return getList();
	}

	@Override
	public User login(String id, String password) {
		return userRepository.findByUserIdAndPassword(id, password);
	}
}
