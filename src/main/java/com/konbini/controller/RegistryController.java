package com.konbini.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class RegistryController {
	
	static String orderName,orderNameImage;
	static int orderNum;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Autowired
    ResourceLoader resourceLoader;
	
	LocalDateTime D = LocalDateTime.now();
	DateTimeFormatter df =  DateTimeFormatter.ofPattern("MM月dd日(E)",Locale.JAPANESE);
	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MM/dd(E) HH:mm",Locale.JAPANESE);
	
	
	@RequestMapping("konbini/orderregistry")
	public String orderregistry(Model model) {
		
		model.addAttribute("date",df.format(D));
		return "orderregistry";
	}
	@PostMapping("konbini/order-regi-conf")
	public String orderRC(String goodsName,String goodsPrice,
			MultipartFile multipartFile,Model model) {
		try {
            File FileName = new File(multipartFile.getOriginalFilename());
            
            model.addAttribute("Gimage",FileName.toString());
            // 保存先を定義
            String FilePath = "static/image/";
            Resource resource = resourceLoader.getResource("classpath:" + FilePath);
            byte[] bytes = multipartFile.getBytes();
            
            //log
            File file = resource.getFile();
            System.out.println("file.exists(): " + file.exists());
            System.out.println("file.getAbsolutePath(): " + file.getAbsolutePath());
            
            //指定ファイルへ読み込みファイルを書き込み
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(new File(file.getAbsolutePath() + "/" + FileName)));
            stream.write(bytes);
            stream.close();
            
            //SQL
            jdbcTemplate.update("INSERT INTO goods(Goods_Name,Goods_Image_Name,Registry_Date,goods_price) values (?,?,?,?)" ,
            		goodsName,FileName.toString(),dateTime.format(D),goodsPrice);
            
        } catch (Exception e) {
            System.out.println(e);
        }
		model.addAttribute("date",dateTime.format(D));
		return "order-regi-conf";
	}

}
