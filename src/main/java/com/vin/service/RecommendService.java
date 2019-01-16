package com.vin.service;

import java.util.List;

import com.vin.entity.Recommend;

public interface RecommendService {
	 public List<Recommend> findByUserId(long userId);
	 public List<Recommend> findByUserIdAndSelected(long userId,char selected);
	 public List<Recommend> fillAll();
	 public void deleteAll() ;
	 public void deleteByUserId(long userId);
	 public void save(Recommend recommend);
	 public void save(List<Recommend> recommends);
}