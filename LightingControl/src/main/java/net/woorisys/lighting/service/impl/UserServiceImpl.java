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
		if (param.getCity().equals("전체")) {
			if (param.getName() != null && !param.getName().isEmpty()) {
				return userRepository.findByUserIdContaining(param.getName());
			}
		} else {
			if (param.getName() != null && !param.getName().isEmpty()) {
				return userRepository.findByCityIdAndUserIdContaining(Integer.parseInt(param.getCity()), param.getName());
			} else {
				return userRepository.findByCityId(Integer.parseInt(param.getCity()));
			}
		}
		return getList();
	}

	@Override
	public User login(String id, String password) {
		return userRepository.findByUserIdAndPassword(id, password);
	}
}
