package com.konbini.model.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.konbini.model.dao.OrderGoodsRepository;

import com.konbini.model.entity.OrderGoods;


@Controller
public class Controller1{
	
	static String orderName,orderNameImage;
	static int orderNum;
	
	
	@Autowired
	private OrderGoodsRepository OrderGoodsRepos;
	
	@Autowired
    ResourceLoader resourceLoader;
	
	LocalDateTime D = LocalDateTime.now();
	DateTimeFormatter df =  DateTimeFormatter.ofPattern("MM月dd日(E)",Locale.JAPANESE);
	
	@RequestMapping("/konbini/")
	public String index(Model model) {
		List<OrderGoods> orderGoods = OrderGoodsRepos.findAll();
		model.addAttribute("orderGoods",orderGoods);
		model.addAttribute("date",df.format(D));
		return "index";
	}
}
