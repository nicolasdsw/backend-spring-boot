package com.example.demo.specs;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public abstract class BaseSpecs {

	public static <T> Predicate toAndArray(CriteriaBuilder builder, Collection<Predicate> predicates) {
		predicates.removeIf(item -> item == null);
		if (predicates.isEmpty()) {
			return null;
		}
		return builder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	public static <T> Predicate equal(CriteriaBuilder builder, Path<?> path, Optional<?> filterOpt) {
		if (filterOpt.isEmpty()) {
			return null;
		}
		return builder.equal(path, filterOpt.get());
	}

	public static <T> Predicate like(CriteriaBuilder builder, Path<?> path, Optional<String> filterOpt) {
		if (filterOpt.isEmpty() || filterOpt.get().isBlank()) {
			return null;
		}
		String filterValue = filterOpt.get().replace("*", "%").trim().toUpperCase();
		return builder.like(builder.upper(builder.trim(path.as(String.class))), filterValue);
	}

	public static <T> Predicate contains(CriteriaBuilder builder, Path<?> path, Optional<String> filterOpt) {
		return like(builder, path, filterOpt.map(val -> val.isBlank() ? val : "%" + val.trim() + "%"));
	}

}
