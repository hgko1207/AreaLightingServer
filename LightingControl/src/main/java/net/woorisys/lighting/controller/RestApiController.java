package net.woorisys.lighting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.woorisys.lighting.service.ApartmentService;
import net.woorisys.lighting.service.CityService;

/**
 * REST API 통신 Controller
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("api")
public class RestApiController {
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private ApartmentService apartmentService;

	/**
	 * 도시 조회
	 * @return
	 */
	@GetMapping("city/list")
	@ResponseBody 
	public ResponseEntity<?> getCityList() {
		return new ResponseEntity<>(cityService.getList(), HttpStatus.OK);
	}
	
	/**
	 * 단지 조회
	 * @return
	 */
	@GetMapping("apartment/list")
	@ResponseBody 
	public ResponseEntity<?> getApartmentList(int cityId) {
		return new ResponseEntity<>(apartmentService.getList(cityId), HttpStatus.OK);
	}
	
	/**
	 * 단지 조회
	 * @return
	 */
	@PostMapping("login")
	@ResponseBody 
	public ResponseEntity<?> login(long id, String password) {
		return new ResponseEntity<>(apartmentService.login(id, password), HttpStatus.OK);
	}
}
