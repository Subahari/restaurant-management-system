package com.restaurant.management.controller;

import java.security.Principal;
import java.time.LocalTime;
import java.util.*;

import com.restaurant.management.Logindomain.Login;
import com.restaurant.management.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.restaurant.management.model.Menu;
import com.restaurant.management.model.User;
import com.restaurant.management.repository.MenuRepository;
import com.restaurant.management.service.UserService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {
	private UserService userService;
	private MenuRepository menuRepository;
	
	@Autowired
	public MainController(UserService userService, MenuRepository menuRepository) {
		this.userService = userService;
		this.menuRepository = menuRepository;
	}

	@GetMapping(value = { "/", "/home" })
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "index";
	}



	@Autowired
	private LoginService userservice;

	@GetMapping("/adminloginsuccess")
	public String adminloginsuccess(){
		return "adminloginsuccess";
	}
	@GetMapping("/adminlogin")

	public ModelAndView adminlogin() {
		ModelAndView mav = new ModelAndView("adminlogin");
		mav.addObject("user", new Login());
		return mav;
	}

	@PostMapping("/adminlogin")
	public String login(@ModelAttribute("user") Login user ) {

		Login oauthUser = userservice.login(user.getUsername(), user.getPassword());


		System.out.print(oauthUser);
		if(Objects.nonNull(oauthUser))
		{

			return "redirect:/adminloginsuccess";


		} else {
			return "redirect:/adminlogin";


		}

	}
	@RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
	public String logoutDo(HttpServletRequest request, HttpServletResponse response)
	{


		return "redirect:/login";
	}
	
	@GetMapping("/loginPage")
	public String home(Model model, Principal principal) {
		String username = principal.getName();
		List<Menu> menus = menuRepository.findAll();
		User user = userService.findByUsername(username).get();
		model.addAttribute("user", user);
		
		LocalTime time = LocalTime.now();
		int hour = time.getHour();
		if(hour <= 12) {
			model.addAttribute("greetUser", "Good Morning, ");
		} else if(hour <= 18) {
			model.addAttribute("greetUser", "Good Afternoon, ");
		} else {
			model.addAttribute("greetUser", "Good Evening, ");
		}
		model.addAttribute("menus", menus);
		return "customerPage";
	}
	
	@PostMapping("/processOrder")
	public String processOrder(Model model, @RequestParam Map<String, String> requestParams, Principal  principal) {
		Map<String, Integer> order = new HashMap<>();
		String username = principal.getName();
		User user = userService.findByUsername(username).get();
		List<Menu> menus = menuRepository.findAll();
		List<Menu> customerOrder = new ArrayList<>();
		Double finalTotal = 0.0;
		
		requestParams.forEach((k, v) -> {
			try {
				if(Integer.parseInt(v) != 0) order.put(k, Integer.parseInt(v));
			} catch(NumberFormatException e) {
				e.getStackTrace();
			}});
		
		for(Menu menu : menus) {
			order.forEach((k, v) -> 
				{if(menu.getId().equals(k)) {
					menu.setQuantity(v);
					customerOrder.add(menu);
				};});	
		}
		
		for(Menu menu : customerOrder) {
			menu.setTotalPrice();
			finalTotal += menu.getTotalPrice();
		}
		
		model.addAttribute("finalTotal", finalTotal);
		model.addAttribute("user", user);
		model.addAttribute("customerOrder", customerOrder);
		return "processOrder";
	}


	
}
