package com.konbini.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CalendarController {

	LocalDateTime D = LocalDateTime.now();
	DateTimeFormatter df =  DateTimeFormatter.ofPattern("MM月dd日(E)",Locale.JAPANESE);
	
    @RequestMapping(path = "konbini/calendar", method = RequestMethod.GET)
    String index(Model model) {
    	
    	model.addAttribute("date",df.format(D));
        return "calendar";
    }
}
