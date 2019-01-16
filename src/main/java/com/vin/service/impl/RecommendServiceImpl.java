package com.vin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vin.dao.RecommendDao;
import com.vin.entity.Recommend;
import com.vin.service.RecommendService;

@Service
public class RecommendServiceImpl implements RecommendService {
	@Autowired
	private RecommendDao  recommendDao ;
	 
	public void deleteAll() {
		recommendDao.deleteAll();
	}
	
	public void deleteByUserId(long userId) {	
		recommendDao.deleteByUserId(userId);
	}
	
	public void save(List<Recommend> recommends) {
		recommendDao.saveAll(recommends);
	}
	
	public void save(Recommend recommend){
		recommendDao.save(recommend);
	}
	
    public List<Recommend> findByUserId(long userId){
    	return recommendDao.findByUserId(userId);
    }
    public List<Recommend> findByUserIdAndSelected(long userId,char selected){
    	return recommendDao.findByUserIdAndSelectedOrderByScoreDesc(Long.valueOf(userId),selected);
    }
    
    public List<Recommend> fillAll(){
    	return recommendDao.findAll();
    }
}