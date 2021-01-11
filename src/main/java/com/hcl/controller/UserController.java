package com.hcl.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.dao.UserRepository;
import com.hcl.model.User;

@Controller
public class UserController {

	@Autowired
	private UserRepository repo;
	
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	
	@PostMapping("/login")
	private String validateUser(User user) {
		if(repo.findById(user.getId())!= null) {
			return "redirect:/list";
		}
		return "/login";
	}
	
	@GetMapping("/list")
	public ModelAndView display(User u) {
		List<User>users = (List<User>) repo.findAll();
		return new ModelAndView("list","users",users);
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView editUserForm(User u) {
		ModelAndView mav = new ModelAndView("update");
		User user = repo.findById(u.getId()).get();
		mav.addObject("update",user);
		return mav;
	}
	
	@PostMapping("/update/{id}")
	public String editUser(User u) {
		repo.save(u);
		return "redirect:/list";
	}
	
}
