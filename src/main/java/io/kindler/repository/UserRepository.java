package io.kindler.repository;

import com.querydsl.core.types.Predicate;
import io.kindler.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

/**
 * Created by kyj on 2016-10-17.
 */
public interface UserRepository extends JpaRepository<User, Integer>, QueryDslPredicateExecutor<User> {
	List<User> findAll(Predicate predicate);
}
