package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.LoadTestDataService;

@RestController
public class IndexController {

	@Autowired
	private LoadTestDataService loadTestDataService;

	@GetMapping
	public String index() {
		return "Hello World!";
	}

	@PostMapping("/api/private/load-test-data")
	public void loadPosts() {
		this.loadTestDataService.loadPosts();
	}

	@DeleteMapping("/api/private/delete-test-data")
	public void deletePosts() {
		this.loadTestDataService.deletePosts();
	}
}
