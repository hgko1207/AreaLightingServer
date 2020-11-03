package net.woorisys.lighting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.woorisys.lighting.domain.db.Apartment;
import net.woorisys.lighting.domain.db.City;
import net.woorisys.lighting.service.ApartmentService;
import net.woorisys.lighting.service.CityService;

/**
 * 아파트 단지 화면 관리 Controller
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("apartment")
public class ApartmentController {
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private ApartmentService apartmentService;

	/**
	 * 목록 화면
	 * @param model
	 */
	@GetMapping("list")
    public void list(Model model) {
    }
	
	/**
	 * 등록 화면
	 * @param model
	 */
	@GetMapping("regist")
    public void regist(Model model, int id) {
		model.addAttribute("city", cityService.get(id));
    }
	
	/**
	 * 등록
	 * @param city
	 * @return
	 */
	@PostMapping("regist")
	@ResponseBody 
	public ResponseEntity<?> regist(Apartment apartment) {
		City city = cityService.get(apartment.getCityId());
		if (city != null) {
			apartment.setCity(city);
			if (apartmentService.regist(apartment)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
