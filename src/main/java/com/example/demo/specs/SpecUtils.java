package com.example.demo.specs;

import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public abstract class SpecUtils {

	public static <T> Predicate likePredicate(CriteriaBuilder builder, Path<?> path, Optional<String> filterOpt) {
		if (filterOpt.isEmpty() || filterOpt.get().isBlank()) {
			return builder.and();
		}
		String filterValue = filterOpt.get().replace("*", "%");
		return builder.like(builder.upper(builder.trim(path.as(String.class))),
				builder.upper(builder.trim(builder.literal(filterValue))));
	}

	public static <T> Predicate containsPredicate(CriteriaBuilder builder, Path<?> path, Optional<String> filterOpt) {
		if (filterOpt.isEmpty() || filterOpt.get().isBlank()) {
			return builder.and();
		}
		return likePredicate(builder, path, filterOpt.map(val -> "%" + val + "%"));
	}

}
