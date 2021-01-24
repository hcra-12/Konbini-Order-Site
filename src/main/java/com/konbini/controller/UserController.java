package com.konbini.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.konbini.model.dao.UserRepository;
import com.konbini.model.entity.User;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepos;
	
	LocalDateTime D = LocalDateTime.now();
	DateTimeFormatter df =  DateTimeFormatter.ofPattern("MM月dd日(E)",Locale.JAPANESE);
	
	@RequestMapping("konbini/user")
	public String user(Model model) {
		
		List<User> users = userRepos.findAll();
		
		model.addAttribute("date",df.format(D));
		model.addAttribute("users",users);
		return "user";
	}
}
