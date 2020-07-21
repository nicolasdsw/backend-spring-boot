package com.example.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.filter.PostFilter;
import com.example.demo.dto.req.PostReqDTO;
import com.example.demo.dto.res.PostResDTO;
import com.example.demo.model.Post;
import com.example.demo.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping
	public Page<Post> index(Optional<PostFilter> filter, @PageableDefault Pageable pageable) {
		return this.postService.index(filter, pageable);
	}

	@GetMapping("/{id}")
	public Optional<PostResDTO> show(@PathVariable Long id) {
		return this.postService.show(id);
	}

	@PostMapping
	public Optional<PostResDTO> store(@Valid @RequestBody PostReqDTO dto) {
		return this.postService.store(dto);
	}

	@PutMapping("/{id}")
	public Optional<PostResDTO> update(@PathVariable Long id, @Valid @RequestBody PostReqDTO dto) {
		return this.postService.update(id, dto);
	}

	@DeleteMapping("/{id}")
	public void destroy(@PathVariable Long id) {
		this.postService.destroy(id);
	}
}
