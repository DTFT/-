package com.msg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.msg.model.MsgException;
import com.msg.model.Pager;
import com.msg.model.SystemContext;
import com.msg.model.User;
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
				  System.out.println("con is null,数据库连接失败");
				}
			ps=con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			rs=ps.executeQuery();
			while(rs.next()){
				if(rs.getInt(1)>0) throw new MsgException("添加的用户已经存在");
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
			if(u.getUsername().equals("admin"))throw new MsgException("admin不能被删除");
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

	@SuppressWarnings("resource")
	public Pager<User> list(String condition) {
		int pageOffset=SystemContext.getPageOffset();
		int pageSize=SystemContext.getPageSize();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
	
		Pager<User> pages=new Pager<User>();
		List<User> users=new ArrayList<User>();
		User u=null;
		try {

			con=DBUtil.getConnection();
			String sql="select * from t_user";
			String sqlCount="select count(*) from t_user";
			
			if(condition!=null||!"".equals(condition)){
				sql+=" where username like '%"+condition+"%' or nickname like '%"+condition+"%'";
				sqlCount+=" where username like '%"+condition+"%' or nickname like '%"+condition+"%'";
			}
			sql+=" limit ?,?";
			ps=con.prepareStatement(sql);
//			System.out.println("pageIndex"+pageIndex);
//			System.out.println("start"+start);
			ps.setInt(1, pageOffset);
			ps.setInt(2, pageSize);
//			System.out.println(sql);
//			if(condition==null||"".equals(condition)){
//				sql+="limit ?,?";
//				ps=con.prepareStatement(sql);
//				ps.setInt(1, start);
//				ps.setInt(2, pageSize);
//			}else{
//				sql+=" where username like ? or nickname like ? ";
////				System.out.println(sql);
//				ps=con.prepareStatement(sql);
//				ps.setString(1, "%"+condition+"%");
//				ps.setString(2, "%"+condition+"%");
//			}
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
			//分页
			ps=con.prepareStatement(sqlCount);
			rs=ps.executeQuery();
			int totalRecord=0;
			while(rs.next()){
				totalRecord=rs.getInt(1);
			}
//			totalRecords;
			int totalPage=(totalRecord-1)/pageSize+1;
		    pages.setPageOffset(pageOffset);
			pages.setPageSize(pageSize);
			pages.setTotalPage(totalPage);
			pages.setTotalRecord(totalRecord);
//			System.out.println("totalRecord"+totalRecord);
//			System.out.println("pageSize"+pageSize);
//			System.out.println("totalpage"+totalPage);
//			System.out.println(sqlCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		pages.setDatas(users);
		return pages;
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
			if(u==null)throw new MsgException("用户名不存在");
			if(!u.getPassword().equals(password)) throw new MsgException("用户名密码不正确");
			if(u.getStatus()==1) throw new MsgException("用户处于停用状态，不能登录");
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
