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
@Table(name="goods")
public class Goods {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="Goods_Name")
	private String goodsName;
	
	@Column(name="Goods_Image_Name")
	private String goodsImage;
	
	@Column(name="Registry_Date")
	private String registryDate;
	
	@Column(name="goods_price")
	private int goodsPrice;

	
}
