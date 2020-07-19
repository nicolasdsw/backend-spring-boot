package com.example.demo.dto.req;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.util.StringUtils;

import com.example.demo.model.Post;

public class PostReqDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3976244092928391770L;
	@NotBlank
	private String title;
	@NotBlank
	private String body;

	public PostReqDTO() {
	}
	
	public Post toModel(Post post) {
		post.setBody(StringUtils.trimWhitespace(body));
		post.setTitle(StringUtils.trimWhitespace(title));
		return post;
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
