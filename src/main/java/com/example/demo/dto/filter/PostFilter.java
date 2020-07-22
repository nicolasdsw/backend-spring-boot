package com.example.demo.dto.filter;

public class PostFilter {

	private Long id;
	private String title;
	private String body;
	private String any;
	
	public PostFilter() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAny() {
		return any;
	}

	public void setAny(String any) {
		this.any = any;
	}
}
