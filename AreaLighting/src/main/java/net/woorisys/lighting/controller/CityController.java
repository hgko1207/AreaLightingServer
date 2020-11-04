package net.woorisys.lighting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.woorisys.lighting.domain.db.City;
import net.woorisys.lighting.domain.param.SearchParam;
import net.woorisys.lighting.service.ApartmentService;
import net.woorisys.lighting.service.CityService;

/**
 * 도시 화면 관리 Controller
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("city")
public class CityController {
	
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
	 * 정보 불러오기
	 * @param id
	 * @return
	 */
	@GetMapping("get")
	@ResponseBody
	public City get(int id) {
		return cityService.get(id);
	}
	
	/**
	 * 조회
	 * @param param
	 * @return
	 */
	@PostMapping("search")
	@ResponseBody 
	public ResponseEntity<?> search(@RequestBody SearchParam param) {
		return new ResponseEntity<>(cityService.getList(param), HttpStatus.OK);
	}
	
	/**
	 * 등록
	 * @param city
	 * @return
	 */
	@PostMapping("regist")
	@ResponseBody 
	public ResponseEntity<?> regist(City city) {
		if (cityService.regist(city)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 수정
	 * @param city
	 * @return
	 */
	@PutMapping("update")
	@ResponseBody 
	public ResponseEntity<?> update(City city) {
		if (cityService.update(city)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 삭제
	 * @param id
	 * @return
	 */
	@DeleteMapping("delete")
	@ResponseBody 
	public ResponseEntity<?> delete(int id) {
		if (cityService.delete(id)) {
			if (apartmentService.deleteFromCity(id)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
