package com.example.demo.specs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.dto.filter.PostFilter;
import com.example.demo.model.Post;
import com.example.demo.model.Post_;

public abstract class PostSpecs extends BaseSpecs {

	public static Specification<Post> specByFilter(Optional<PostFilter> filter) {
		return filter.isEmpty() ? null : (root, query, builder) -> {
			Collection<Predicate> predicates = new ArrayList<>();
			predicates.add(equal(builder, root.get(Post_.ID), filter.map(PostFilter::getId)));
			predicates.add(contains(builder, root.get(Post_.TITLE), filter.map(PostFilter::getTitle)));
			predicates.add(contains(builder, root.get(Post_.BODY), filter.map(PostFilter::getBody)));
			return toAndArray(builder, predicates);
		};
	}
}
