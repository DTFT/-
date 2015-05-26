package com.msg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.msg.model.Comment;
import com.msg.model.MsgException;
import com.msg.model.Pager;
import com.msg.model.SystemContext;
import com.util.DBUtil;

public class CommentDao implements ICommentDAO {
	private IUserDao userDao;
	private IMessageDAO msgDao;

	public CommentDao() {
		userDao = DAOFactory.getUserDao();
		msgDao = DAOFactory.getMessageDao();
	}

	@Override
	public void add(Comment comment, int userId, int msgId) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			if (userDao.load(userId) == null)
				throw new MsgException("添加留言的用户不存在");
			if (msgDao.load(msgId) == null)
				throw new MsgException("添加评论的留言不存在");
			con = DBUtil.getConnection();
			String sql = "insert into t_comment (content,post_date,user_id,msg_id) values (?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, comment.getContent());
			ps.setTimestamp(2, new Timestamp(new Date().getTime()));
			ps.setInt(3, userId);
			ps.setInt(4, msgId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}

	}

	@Override
	public void delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			String sql = "delete from t_comment where id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}

	}

	@Override
	public Comment load(int id) {
		Comment comment = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from t_comment where id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setContent(rs.getString("comment"));
				comment.setMsgId(rs.getInt("msg_id"));
				comment.setPostDate(new Date(rs.getTimestamp("post_date")
						.getTime()));
				comment.setUserId(rs.getInt("user_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return comment;
	}

	@SuppressWarnings("resource")
	@Override
	public Pager<Comment> list(int msgId) {
		Pager<Comment> pages = new Pager<Comment>();
		Comment comment = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Comment> datas = new ArrayList<Comment>();
		int pageOffset = SystemContext.getPageOffset();
		int pageSize = SystemContext.getPageSize();
		pages.setDatas(datas);
		pages.setPageOffset(pageOffset);
		pages.setPageSize(pageSize);
		try {
			con = DBUtil.getConnection();
			String sql = "select * from t_comment where msg_id=? order by post_date asc limit ?,?";
			String sqlCount = "select count(*) from t_comment where msg_id=? order by post_date asc";
			ps = con.prepareStatement(sql);
			ps.setInt(1, msgId);
			ps.setInt(2, pageOffset);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setContent(rs.getString("comment"));
				comment.setMsgId(rs.getInt("msg_id"));
				comment.setPostDate(new Date(rs.getTimestamp("post_date")
						.getTime()));
				comment.setUserId(rs.getInt("user_id"));
				datas.add(comment);
			}
			ps = con.prepareStatement(sqlCount);
			ps.setInt(1, msgId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int totalRecord = rs.getInt(1);
				int totalPage = (totalRecord - 1) / pageSize + 1;
				pages.setTotalRecord(totalRecord);
				pages.setTotalPage(totalPage);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}

		return pages;
	}

}
