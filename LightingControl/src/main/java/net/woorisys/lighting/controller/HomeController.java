package net.woorisys.lighting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.woorisys.lighting.service.ApartmentService;
import net.woorisys.lighting.service.CityService;
import net.woorisys.lighting.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private ApartmentService apartmentService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
    public String index(Model model) {
		 return "redirect:login";
    }
	
	@GetMapping("login")
    public String login(Model model, @RequestParam(value = "error", required = false) String error,
    		@CookieValue(value = "saved_username", defaultValue = "") String username) {
		if (error != null) {
			model.addAttribute("error", "falied");
		}
		
		if (username != null) {
			model.addAttribute("username", username);
		}
        return "login";
    }
	
	/**
	 * 대시보드 화면
	 * @param model
	 */
	@GetMapping("home")
    public void home(Model model) {
		model.addAttribute("cityList", cityService.getList().size());
		model.addAttribute("apartmentList", apartmentService.getList().size());
		model.addAttribute("userList", userService.getList().size());
    }
}
