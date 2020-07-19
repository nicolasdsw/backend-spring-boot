package com.example.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.specs.PostSpec;


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
	public Optional<PostResDTO> show(@PathVariable Long id) {
		return this.postRepository.findById(id).map(PostResDTO::new);
	}

	@PostMapping
	public Optional<PostResDTO> store(@Valid @RequestBody PostReqDTO dto) {
		Post post = dto.toModel(new Post());
		return Optional.of(this.postRepository.save(post)).map(PostResDTO::new);
	}

	@PutMapping("/{id}")
	public Optional<PostResDTO> update(@PathVariable Long id, @Valid @RequestBody PostReqDTO dto) {
		Post post = dto.toModel(this.postRepository.findById(id).get());
		return Optional.of(this.postRepository.save(post)).map(PostResDTO::new);
	}

	@DeleteMapping("/{id}")
	public void destroy(@PathVariable Long id) {
		Post post = this.postRepository.findById(id).get();
		this.postRepository.delete(post);
	}
}
