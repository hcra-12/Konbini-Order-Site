package com.konbini.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ordergoods")
public class OrderGoods {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="Order_Name")
	private String orderName;
	
	@Column(name="Order_Image_Name")
	private String orderImageName;
	
	@Column(name="Order_Num")
	private int orderNum;
	
	@Column(name="Order_Date_Time")
	private String orderDateTime;
	
	@Column(name="Order_Date")
	private String orderDate;
	
	@Column(name="Order_Date2")
	private String orderDate2;
}
