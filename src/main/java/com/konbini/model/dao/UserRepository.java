package com.konbini.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konbini.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
