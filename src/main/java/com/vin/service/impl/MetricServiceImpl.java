package com.vin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vin.dao.MetricDao;
import com.vin.entity.Metric;
import com.vin.service.MetricService;

@Service
public class MetricServiceImpl implements MetricService {
	@Autowired
	private MetricDao  metircDao ;
	 
	public List<Metric> findAll() {
		return metircDao.findAll();
	}
 

}