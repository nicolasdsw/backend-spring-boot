package com.example.demo.specs;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

public abstract class BaseSpecs {

	public static <T> Predicate toAndArray(CriteriaBuilder builder, Collection<Predicate> predicates) {
		predicates.removeIf(item -> item == null);
		if (predicates.isEmpty()) {
			return null;
		}
		return builder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	public static <T> Predicate toOrArray(CriteriaBuilder builder, Collection<Predicate> predicates) {
		predicates.removeIf(item -> item == null);
		if (predicates.isEmpty()) {
			return null;
		}
		return builder.or(predicates.toArray(new Predicate[predicates.size()]));
	}

	public static <T> Predicate equal(CriteriaBuilder builder, Expression<?> path, Optional<?> filterOpt) {
		if (filterOpt.isEmpty()) {
			return null;
		}
		return builder.equal(path, filterOpt.get());
	}

	public static <T> Predicate like(CriteriaBuilder builder, Expression<?> path, Optional<String> filterOpt) {
		if (filterOpt.isEmpty() || filterOpt.get().isBlank()) {
			return null;
		}
		String filterValue = filterOpt.get().replace("*", "%").trim().toUpperCase();
		return builder.like(builder.upper(builder.trim(path.as(String.class))), filterValue);
	}

	public static <T> Predicate contains(CriteriaBuilder builder, Expression<?> path, Optional<String> filterOpt) {
		return like(builder, path, filterOpt.map(val -> val.isBlank() ? val : "%" + val.trim() + "%"));
	}

	public static <T> Expression<String> concatAll(CriteriaBuilder builder, Expression<?>... x) {
		Expression<String> result = x[0].as(String.class);
		for (int i = 1; i < x.length; i++) {
			result = builder.concat(result, x[i].as(String.class));
		}
		return result;
	}
}
