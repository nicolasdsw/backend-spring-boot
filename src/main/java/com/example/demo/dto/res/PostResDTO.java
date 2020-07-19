package com.example.demo.dto.res;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.demo.model.Post;

public class PostResDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3489553736491386943L;
	private Long id;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Long createdBy;
	private String title;
	private String body;

	public PostResDTO() {
	}

	public PostResDTO(Post post) {
		this.id = post.getId();
		this.createdAt = post.getCreatedAt();
		this.updatedAt = post.getUpdatedAt();
		this.createdBy = post.getCreatedBy();
		this.title = post.getTitle();
		this.body = post.getBody();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
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
