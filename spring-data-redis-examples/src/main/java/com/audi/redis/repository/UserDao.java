package com.audi.redis.repository;

import com.audi.redis.model.User;

public interface UserDao {
	public void saveUser(final User user);
	public User getUser( final long id);
}
