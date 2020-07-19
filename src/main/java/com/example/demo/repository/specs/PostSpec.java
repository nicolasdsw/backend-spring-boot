package com.example.demo.repository.specs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.dto.filter.PostFilter;
import com.example.demo.model.Post;
import com.example.demo.model.Post_;
import com.example.demo.utils.SpecUtils;

public abstract class PostSpec {

	public static Specification<Post> specByFilter(Optional<PostFilter> filter) {
		return filter.isEmpty() ? null : (root, query, builder) -> {
			Collection<Predicate> predicates = new ArrayList<>();
			predicates.add(SpecUtils.containsPredicate(builder, root.get(Post_.TITLE), filter.map(PostFilter::getTitle)));
			predicates.add(SpecUtils.containsPredicate(builder, root.get(Post_.BODY), filter.map(PostFilter::getBody)));
			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
}
