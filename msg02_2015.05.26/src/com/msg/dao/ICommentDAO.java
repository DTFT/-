package com.msg.dao;

import com.msg.model.Comment;
import com.msg.model.Pager;

public interface ICommentDAO {
	public void add(Comment comment,int userId,int msgId);
	public void delete(int id);
	public Comment load(int id);
	public Pager<Comment> list(int msgId);
}
