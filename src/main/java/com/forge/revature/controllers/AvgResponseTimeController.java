package com.forge.revature.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forge.revature.services.AdminChartService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/responseTime")
@AllArgsConstructor
public class AvgResponseTimeController {

	private AdminChartService adminChartService;
	
	@GetMapping
	public ResponseEntity<String> getAverageResponseTime(){
		return adminChartService.getAverageResponseTime();
	}
}
