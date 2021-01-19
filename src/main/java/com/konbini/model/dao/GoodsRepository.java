package com.konbini.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.konbini.model.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long>{

}
