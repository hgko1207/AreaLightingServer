package net.woorisys.lighting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 아파트 단지 화면 관리 Controller
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("apartment")
public class ApartmentController {

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
		System.err.println(id);
    }
}
