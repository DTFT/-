package com.shop.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.shop.model.User;
import com.shop.util.MyBatisUtil;

public class TestMybatis {

	@Test
	public void testDelete() {
		try {
			InputStream is = Resources
					.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory factory = new SqlSessionFactoryBuilder()
					.build(is);
			SqlSession session = factory.openSession();
			session.delete(User.class.getName() + ".delete", 3);
			session.commit();
			session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAdd() {
		SqlSession session = null;
		try {
			session = MyBatisUtil.createSession();
			User u=new User();
			u.setNickname("°Ë½ä");
			u.setPassword("123");
			u.setType(0);
			u.setUsername("bajie");
			session.insert(User.class.getName()+".add", u);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			MyBatisUtil.closeSession(session);
		}
	}

	@Test
	public void testLoad() {
		SqlSession session = null;
		try {
			session = MyBatisUtil.createSession();
			User u = (User) session
					.selectOne(User.class.getName() + ".load", 1);
			System.out.println(u.getNickname());
		} finally {
			MyBatisUtil.closeSession(session);
		}
	}
	
	@Test
	public void testList(){
		SqlSession session = null;
		try {
			session = MyBatisUtil.createSession();
			List<User> us=session.selectList(User.class.getName() + ".list");
			System.out.println(us.size());
		} finally {
			MyBatisUtil.closeSession(session);
		}		
		
	}
}
