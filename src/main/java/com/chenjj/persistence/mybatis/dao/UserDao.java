package com.chenjj.persistence.mybatis.dao;

import java.util.List;

import com.chenjj.persistence.mybatis.model.User;

public interface UserDao {

	public long addUser(User user);
	
	public User findUserById(long id);
	
	public List<User> findAllUsers();
}
