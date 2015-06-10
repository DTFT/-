package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.model.ShopException;
import com.shop.model.User;
import com.util.DBUtil;

public class UserDao implements IUserDao {

	@SuppressWarnings("resource")
	public void add(User user) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DBUtil.getConnection();
			String sql="select count(*) from t_user where username=?";
			if(con==null){
				  System.out.println("con is null,���ݿ�����ʧ��");
				}
			ps=con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			rs=ps.executeQuery();
			while(rs.next()){
				if(rs.getInt(1)>0) throw new ShopException("��ӵ��û��Ѿ�����");
			}
			sql="insert into t_user values (null,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
			ps.setInt(4, user.getStatus());
			ps.setInt(5, user.getType());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}

	}

	public void delete(int id) {
		
		Connection con=null;
		PreparedStatement ps=null;

		try {
			con=DBUtil.getConnection();
			User u=this.load(id);
			if(u.getUsername().equals("admin"))throw new ShopException("admin���ܱ�ɾ��");
			String sql="delete from t_user where id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

			DBUtil.close(ps);
			DBUtil.close(con);
		}


	}

	public void update(User user) {
		Connection con=null;
		PreparedStatement ps=null;

		try {
			con=DBUtil.getConnection();
			String sql="update t_user set password=?,nickname=? ,type=?,status=? where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getNickname());
			ps.setInt(3, user.getType());
			ps.setInt(4, user.getStatus());
			ps.setInt(5, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

			DBUtil.close(ps);
			DBUtil.close(con);
		}

	}

	public User load(int id) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User u=null;
		try {
			con=DBUtil.getConnection();
			String sql="select * from t_user where id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				u=new User();
				u.setId(rs.getInt("id"));
				u.setNickname(rs.getString("nickname"));
				u.setPassword(rs.getString("password"));
				u.setUsername(rs.getString("username"));
				u.setType(rs.getInt("type"));
				u.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return u;
	}

	public List<User> list() {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<User> users=new ArrayList<User>();
		User u=null;
		try {
			con=DBUtil.getConnection();
			String sql="select * from t_user";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				u=new User();
				u.setId(rs.getInt("id"));
				u.setNickname(rs.getString("nickname"));
				u.setPassword(rs.getString("password"));
				u.setUsername(rs.getString("username"));
				u.setType(rs.getInt("type"));
				u.setStatus(rs.getInt("status"));
				users.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return users;
	}

	public User login(String username, String password) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User u=null;
		try {
			con=DBUtil.getConnection();
			String sql="select * from t_user where username=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			while(rs.next()){
				u=new User();
				u.setId(rs.getInt("id"));
				u.setNickname(rs.getString("nickname"));
				u.setPassword(rs.getString("password"));
				u.setUsername(rs.getString("username"));
				u.setStatus(rs.getInt("status"));
				u.setType(rs.getInt("type"));
			}
			if(u==null)throw new ShopException("�û���������");
			if(!u.getPassword().equals(password)) throw new ShopException("�û������벻��ȷ");
			if(u.getStatus()==1) throw new ShopException("�û�����ͣ��״̬�����ܵ�¼");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return u;
	}

}
