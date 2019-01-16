package com.vin.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.vin.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

}