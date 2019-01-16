package com.vin.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.vin.entity.Metric;

public interface MetricDao extends JpaRepository<Metric, Long> {

}