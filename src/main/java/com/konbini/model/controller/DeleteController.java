package com.konbini.model.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.konbini.model.dao.GoodsRepository;
import com.konbini.model.dao.OrderGoodsRepository;
import com.konbini.model.entity.Goods;
import com.konbini.model.entity.OrderGoods;


@Controller
public class DeleteController{
	
	static String orderName,orderNameImage;
	static int orderNum;
	
	
	@Autowired
	private GoodsRepository goodsRepos;
	
	@Autowired
	private OrderGoodsRepository orderGoodsRepos;
	
	
	LocalDateTime D = LocalDateTime.now();
	DateTimeFormatter df =  DateTimeFormatter.ofPattern("MM月dd日(E)",Locale.JAPANESE);
	
	@RequestMapping("/konbini/delete")
	public String delete(Model model) {
		List<Goods> goods = goodsRepos.findAll();
		List<OrderGoods> orderGoods = orderGoodsRepos.findAll();
		
		model.addAttribute("Goods",goods);
		model.addAttribute("OrderGoods",orderGoods);
		model.addAttribute("date",df.format(D));
		return "delete";
	}
	
	@PostMapping("/delete/confirm")
	public String deleteConfirm(@RequestParam("id") List<String> id,
			Model model) {
		for(String set :id) {
			if(set.startsWith("00")) {//"00"で始まる場合OrderGoodsのDelete
				long i = Long.parseLong(set);
				System.out.println(i);
				OrderGoods orderGoods = orderGoodsRepos.findById(i).get();
				orderGoodsRepos.delete(orderGoods);
			}else{						//そうでない場合GoodsのDelete
				long i = Long.parseLong(set);
				Goods goods = goodsRepos.findById(i).get();
				goodsRepos.delete(goods);
			}
		}
		model.addAttribute("id",id);
		
		return "redirect:/konbini/";
	}
}
