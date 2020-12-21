package net.woorisys.lighting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.woorisys.lighting.domain.db.Apartment;
import net.woorisys.lighting.domain.db.City;
import net.woorisys.lighting.domain.db.Floor;
import net.woorisys.lighting.service.ApartmentService;
import net.woorisys.lighting.service.CityService;
import net.woorisys.lighting.service.FloorService;

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
	
	@Autowired
	private FloorService floorService;

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
	 * 조회
	 * @param model
	 */
	@GetMapping("getList")
	@ResponseBody
    public ResponseEntity<?> getList(int cityId) {
		return new ResponseEntity<>(apartmentService.getList(cityId), HttpStatus.OK);
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
			apartment.setFloors(checkFloors(apartment));
			Apartment response = apartmentService.registToDomain(apartment);
			if (response != null) {
				for (Floor floor : apartment.getFloors()) {
					floor.setApartment(response);
					floorService.update(floor);
				}
				
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 수정 화면
	 * @param model
	 */
	@GetMapping("update")
    public void update(Model model, long id) {
		model.addAttribute("apartment", apartmentService.get(id));
    }
	
	/**
	 * 수정
	 * @param city
	 * @return
	 */
	@PutMapping("update")
	@ResponseBody 
	public ResponseEntity<?> update(Apartment apartment) {
		Apartment temp = apartmentService.get(apartment.getId());
		temp.setName(apartment.getName());
		temp.setFloors(checkFloors(apartment));
		
		if (floorService.deleteFromApartment(temp.getId())) {
			Apartment response = apartmentService.updateToDomain(temp);
			if (response != null) {
				for (Floor floor : apartment.getFloors()) {
					floor.setApartment(response);
					floorService.update(floor);
				}
				
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 삭제
	 * @param apartment
	 * @return
	 */
	@DeleteMapping("delete")
	@ResponseBody 
	public ResponseEntity<?> delete(long id) {
		if (apartmentService.delete(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	private List<Floor> checkFloors(Apartment apartment) {
		List<Floor> floors = new ArrayList<>();
		for (Floor floor : apartment.getFloors()) {
			if (floor.getName() != null) {
				floors.add(floor);
			}
		}
		
		return floors;
	}
}
