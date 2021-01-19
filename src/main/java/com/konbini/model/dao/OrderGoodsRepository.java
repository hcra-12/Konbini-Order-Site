package com.konbini.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.konbini.model.entity.OrderGoods;

public interface OrderGoodsRepository extends JpaRepository<OrderGoods, Long>{

}
