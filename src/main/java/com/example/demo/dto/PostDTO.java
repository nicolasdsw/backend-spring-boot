package com.example.demo.dto;

import com.example.demo.model.Post;

public class PostDTO {

	private Long id;
	private String title;
	private String body;

	public PostDTO() {
	}

	public PostDTO(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.body = post.getBody();
	}
	
	public Post toModel(Post post) {
		post.setId(id);
		post.setBody(body);
		post.setTitle(title);
		return post;
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
}
