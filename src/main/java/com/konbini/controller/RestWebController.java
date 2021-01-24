package com.konbini.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.konbini.model.dao.OrderGoodsRepository;
import com.konbini.model.data.Event;
import com.konbini.model.entity.OrderGoods;


@RestController
@RequestMapping("konbini/api/event")
public class RestWebController {
	
	@Autowired
	private OrderGoodsRepository OrderGoodsRepos;
	
	
    /**カレンダーに表示するEvent情報を取得
     * @return Event情報をjsonエンコードした文字列*/
	
    @GetMapping(value = "/all")
    public String getEvents() {
        String jsonMsg = null;
        try {
        	List<Event> events = new ArrayList<Event>();
        	List<OrderGoods> orderGoods = OrderGoodsRepos.findAll();
        	Event event = new Event();
        	
        	/**イベントセット*/
        	for(int i = 0; i < orderGoods.size(); i++) {
    			event = new Event();
                event.setTitle(orderGoods.get(i).getOrderName() + " × " + orderGoods.get(i).getOrderNum());
                event.setStart(orderGoods.get(i).getOrderDate2());
                events.add(event);
    		}

            // FullCalendarにエンコード済み文字列を渡す
            ObjectMapper mapper = new ObjectMapper();
            jsonMsg =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(events);
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        return jsonMsg;
    }
}
