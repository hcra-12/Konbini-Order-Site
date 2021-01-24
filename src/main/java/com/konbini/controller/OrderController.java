package com.konbini.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class OrderController {
	
	static String orderName,orderNameImage;
	static int orderNum;
	
	@Autowired
	private GoodsRepository goodsRepos;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private OrderGoodsRepository OrderGoodsRepos;
	
	LocalDateTime D = LocalDateTime.now();
	LocalDate Date = LocalDate.now();
	DateTimeFormatter df =  DateTimeFormatter.ofPattern("MM月dd日(E)",Locale.JAPANESE);
	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MM/dd(E) HH:mm",Locale.JAPANESE);
	
	@RequestMapping("konbini/order")
	public String order(Model model) {
		List<String> dList = Arrays.asList(
				df.format(D),df.format(D.minusDays(1)),df.format(D.minusDays(2)),
				df.format(D.minusDays(3)),df.format(D.minusDays(4)),df.format(D.minusDays(5))
				);
		
		List<Goods> goods = goodsRepos.findAll();
		
		/**発注数表示*/
		Map<String,Integer> M = new HashMap<>();
		Map<String,Integer> M1 = new HashMap<>();
		Map<String,Integer> M2 = new HashMap<>();
		Map<String,Integer> M3 = new HashMap<>();
		Map<String,Integer> M4 = new HashMap<>();
		Map<String,Integer> M5 = new HashMap<>();
		
		List<OrderGoods> OG = OrderGoodsRepos.findAll();
		for(int i = 0; i < OG.size(); i++) {
			if(df.format(D).equals(OG.get(i).getOrderDate())){
				M.put(OG.get(i).getOrderName(), OG.get(i).getOrderNum());
			}else if(df.format(D.minusDays(1)).equals(OG.get(i).getOrderDate())) {
				M1.put(OG.get(i).getOrderName(), OG.get(i).getOrderNum());
			}else if(df.format(D.minusDays(2)).equals(OG.get(i).getOrderDate())) {
				M2.put(OG.get(i).getOrderName(), OG.get(i).getOrderNum());
			}else if(df.format(D.minusDays(3)).equals(OG.get(i).getOrderDate())) {
				M3.put(OG.get(i).getOrderName(), OG.get(i).getOrderNum());
			}else if(df.format(D.minusDays(4)).equals(OG.get(i).getOrderDate())) {
				M4.put(OG.get(i).getOrderName(), OG.get(i).getOrderNum());
			}else if(df.format(D.minusDays(5)).equals(OG.get(i).getOrderDate())) {
				M5.put(OG.get(i).getOrderName(), OG.get(i).getOrderNum());
			}
		}
		model.addAttribute("date",dList);
		model.addAttribute("map",M);
		model.addAttribute("map1",M1);
		model.addAttribute("map2",M2);
		model.addAttribute("map3",M3);
		model.addAttribute("map4",M4);
		model.addAttribute("map5",M5);
		model.addAttribute("GOODS",goods);
		return "order";
	}
	
	@PostMapping("/order/confirm")
	public String confirm(@RequestParam("GN") List<String> GN,
			@RequestParam("GI") List<String> GI,
			@RequestParam("ON") List<Integer> ON,
			Model model) {
		model.addAttribute("GN",GN);
		model.addAttribute("GI",GI);
		model.addAttribute("ON",ON);
		model.addAttribute("date",dateTime.format(D));
		
		/**発注SQL書き込み*/
		for(int i = 0; i < ON.size(); i++) {
			orderName  = GN.get(i);
			orderNameImage = GI.get(i);
			orderNum = ON.get(i);
			if(orderNum == 0) {
				continue;
			}
			jdbcTemplate.update("INSERT INTO ordergoods(Order_Name,Order_Image_Name,Order_Num,Order_Date_Time,Order_Date,Order_Date2) values (?,?,?,?,?,?)" ,
					orderName,orderNameImage,orderNum,dateTime.format(D),df.format(D),Date);
		}
		return "OrderConfirm";
	}
}
