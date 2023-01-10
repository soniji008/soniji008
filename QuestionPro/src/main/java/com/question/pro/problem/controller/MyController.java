package com.question.pro.problem.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.question.pro.problem.entity.Items;

import com.question.pro.problem.service.ServiceLayer;

@RestController
public class MyController {

	@Autowired
	public final ServiceLayer serviceLayer;
	
	
	public MyController(ServiceLayer serviceLayer) {
		super();
		this.serviceLayer = serviceLayer;
	}

	
	
	@GetMapping("/top-stories")
	public ResponseEntity<ArrayList<Items>> getTopStories() {
	
		return new ResponseEntity<>(serviceLayer.consumeTopStories(), HttpStatus.OK);
		
	}
	
	@GetMapping("/past-stories")
	public ResponseEntity<ArrayList<Items>> getPastStories(){
		
		return new ResponseEntity<>(serviceLayer.consumePastStories(), HttpStatus.OK);
	}
	
	@GetMapping("/comments/{id}")
	public ResponseEntity<String> getComments(@PathVariable long id) {
		
		return new ResponseEntity<>(serviceLayer.consumeComments(id), HttpStatus.OK);
	} 

	
}
