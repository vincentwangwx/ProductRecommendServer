package com.vin.controller;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class PRSApplicationRunner  implements ApplicationRunner{
	@Autowired
    private AsyncCalculateTask calculatetask;
	
	@Override
    public void run(ApplicationArguments var1) throws Exception{
		//this method will run just after the spring boot load completely.
        Future<String> initTask = calculatetask.initUserCourseMetrics();
        while(true) {
        	if(initTask.isDone()) {
        		break;
        	}
        }
        calculatetask.calculateCourse();
    }
}
