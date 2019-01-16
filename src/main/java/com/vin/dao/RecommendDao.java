package com.vin.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.vin.entity.Recommend;
import com.vin.entity.RecommendKey;

public interface RecommendDao extends JpaRepository<Recommend, RecommendKey> {
	
	   List<Recommend> findByUserId(Long userId);
	   List<Recommend> findByCourseIdAndUserId(Long userId,Long CourseId);
	   List<Recommend> findByUserIdAndSelectedOrderByScoreDesc(Long userId,char selected);
	   
	   @Transactional
	   List<Recommend> removeByUserId(Long userId);
	   
	   @Modifying
	   @Transactional
	   @Query("delete from Recommend where userId = ?1")
	   public void deleteByUserId(Long userId);

}