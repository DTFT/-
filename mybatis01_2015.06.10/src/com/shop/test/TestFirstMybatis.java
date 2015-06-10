package com.shop.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.shop.model.User;

public class TestFirstMybatis {

	public static void main(String[] args) {
	testupdate();
	}
	
	private static void testupdate(){
		try {
			//1�����������ļ���mybatis-config.xml����������
			InputStream  is = Resources.getResourceAsStream("mybatis-config.xml");
			//2������SqlSessionFactory
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
			//3������SqlSession
			SqlSession session=factory.openSession();
			//4������mapper�ļ��������ݣ�����֮ǰ��Ҫ��mapper�ļ����뵽mybatis-config�������ļ��ڣ�
			User u=new User();
			u.setNickname("�����");
			u.setPassword("333");
			u.setType(0);
			u.setUsername("wukong");
			u.setId(2);
			session.update("com.shop.model.User.update",u);
			session.commit();
			session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void testadd(){
		try {
			//1�����������ļ���mybatis-config.xml����������
			InputStream  is = Resources.getResourceAsStream("mybatis-config.xml");
			//2������SqlSessionFactory
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
			//3������SqlSession
			SqlSession session=factory.openSession();
			//4������mapper�ļ��������ݣ�����֮ǰ��Ҫ��mapper�ļ����뵽mybatis-config�������ļ��ڣ�
			User u=new User();
			u.setNickname("�����");
			u.setPassword("123");
			u.setType(0);
			u.setUsername("wukong");
			session.insert("com.shop.model.User.add",u);
			session.commit();
			session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
