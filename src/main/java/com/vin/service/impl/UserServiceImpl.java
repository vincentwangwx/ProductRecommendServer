package com.vin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vin.dao.UserDao;
import com.vin.entity.User;
import com.vin.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	 @Autowired
	private UserDao  userDao ;
	 
	public List<User> findAll() {
		return userDao.findAll();
	}

}