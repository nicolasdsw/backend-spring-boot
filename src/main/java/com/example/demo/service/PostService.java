package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.dto.filter.PostFilter;
import com.example.demo.dto.req.PostReqDTO;
import com.example.demo.dto.res.PostResDTO;
import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.specs.PostSpec;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public Page<Post> index(Optional<PostFilter> filter, Pageable pageable) {
		Specification<Post> spec = PostSpec.specByFilter(filter);
		return this.postRepository.findAll(spec, pageable);
	}

	public Optional<PostResDTO> show(Long id) {
		return this.postRepository.findById(id).map(PostResDTO::new);
	}

	public Optional<PostResDTO> store(PostReqDTO dto) {
		Post post = dto.toModel(new Post());
		post = this.postRepository.save(post);
		return this.show(post.getId());
	}

	public Optional<PostResDTO> update(Long id, PostReqDTO dto) {
		Post post = dto.toModel(this.postRepository.findById(id).get());
		post = this.postRepository.save(post);
		return this.show(post.getId());
	}

	public void destroy(Long id) {
		this.postRepository.deleteById(id);
	}
}
