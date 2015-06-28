package com.chenjj.persistence.mybatis;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.chenjj.persistence.mybatis.dao.UserDao;
import com.chenjj.persistence.mybatis.model.User;

public class TestUser {

	private static SqlSessionFactory sqlSessionFactory = null;

	@Before
	public void setSqlSessionFactory() {
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader("configuration.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddUser(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		User user = new User("zhangsan", "123456", 12, 0);
		long id = userDao.addUser(user);
		sqlSession.commit();
		Assert.assertTrue("添加用户失败", id>0);
	}
	
	@Test
	public void testFindAllUsers(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		List<User> users = userDao.findAllUsers();
		for(int i=0,size=users.size();i<size;i++){
			User user  = users.get(i);
			System.out.println(user.getName()+":"+user.getPassword());
		}
	}
}
