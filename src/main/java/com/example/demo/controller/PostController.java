package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostDTO;
import com.example.demo.filter.PostFilter;
import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.specs.PostSpec;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@GetMapping
	public Page<Post> index(Optional<PostFilter> filter, @PageableDefault Pageable pageable) {
		Specification<Post> spec = PostSpec.specByFilter(filter);
		return this.postRepository.findAll(spec, pageable);
	}

	@GetMapping("/{id}")
	public Optional<PostDTO> show(@PathVariable Long id) {
		return this.postRepository.findById(id).map(PostDTO::new);
	}

	@PostMapping
	public Optional<PostDTO> store(@RequestBody PostDTO dto) {
		Post post = dto.toModel(new Post());
		return Optional.of(this.postRepository.save(post)).map(PostDTO::new);
	}

	@PutMapping("/{id}")
	public Optional<PostDTO> update(@PathVariable Long id, @RequestBody PostDTO dto) {
		Post post = dto.toModel(this.postRepository.findById(id).get());
		return Optional.of(this.postRepository.save(post)).map(PostDTO::new);
	}
}
