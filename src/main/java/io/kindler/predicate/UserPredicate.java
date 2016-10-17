package io.kindler.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import io.kindler.entity.QUser;
import io.kindler.entity.User;

/**
 * Created by kyj on 2016-10-17.
 */
public class UserPredicate {

	public static Predicate findByNameLike(String name) {
		QUser user = QUser.user;

		BooleanBuilder builder = new BooleanBuilder();
		builder.and(user.name.like("%" + name + "%"));

		return builder;
	}

	public static Predicate search(User userParam) {
		QUser user = QUser.user;

		BooleanBuilder builder = new BooleanBuilder();
		if (userParam.getName() != null) builder.and(user.name.eq(userParam.getName()));
		if (userParam.getPasswd() != null) builder.and(user.passwd.eq(userParam.getPasswd()));
		if (userParam.getTelephone() != null) builder.and(user.telephone.eq(userParam.getTelephone()));

		return builder;
	}
}
