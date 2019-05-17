package com.linpeirou.www.dao;

import java.util.List;

import com.linpeirou.www.po.User;
import com.linpeirou.www.util.BeanHandler;
import com.linpeirou.www.util.BeanListHandler;
import com.linpeirou.www.util.JdbcUtil;

public class UserDaoImpl  {
	
	private User userOne = null;

	public  UserDaoImpl() {
	}
	
	public User getUser(String name, String pwd, String phone) {
		String sql = "select * from user where name=? and pwd=? or phone=?";
		userOne = JdbcUtil.executeQueary(sql, new BeanHandler<User>(User.class), name, pwd, phone);
		return userOne;
	}

	public User getUser(String name, String pwd) {
		String sql = "select * from user where name=? and pwd=?";
		userOne = JdbcUtil.executeQueary(sql, new BeanHandler<User>(User.class), name, pwd);
		return userOne;
	}

	public Boolean add(String name, String pwd, String idcard, String phone, Integer roleId) {
		String sql = "insert into user (name, pwd, idcard,phone,roleid) values(?,?,?,?,?)";
		JdbcUtil.executeUpdate(sql, name, pwd, idcard, phone, roleId);
		return true;
	}

	public Boolean resetPwd(String newPwd, String name, String oldPwd) {
		String sql = "update user set pwd=? where name=? and pwd=?";
		JdbcUtil.executeUpdate(sql, newPwd, name, oldPwd);
		return true;
	}

	public User forgetPwd(String phone, String idcard) {
		String sql = "select * from user where phone=? and idcard=?";
		userOne = JdbcUtil.executeQueary(sql, new BeanHandler<User>(User.class), phone, idcard);
		return userOne;
	}

	public List<User> findSomeUser(Integer pc, Integer ps) {
		String sql = "select * from user where roleId=1 limit ?,?";
		return JdbcUtil.executeQueary(sql, new BeanListHandler<User>(User.class), (pc - 1) * ps, ps);
	}

	public int totalUserPage() {
		String sql = "select count(*) from user ";
		return JdbcUtil.totalPage(sql);
	}
	
	public int totalPage() {
		String sql = "select count(*) from user where roleId = 1";
		return JdbcUtil.totalPage(sql);
	}

	public List<User> findAllUser(Integer pc, Integer ps) {
		String sql = "select * from user limit ?,?";
		List<User> users = null;
		return JdbcUtil.executeQueary(sql, new BeanListHandler<User>(User.class), (pc - 1) * ps, ps);
	}

	public Boolean resetLevel(String id, int roleId) {
		String sql = "update user set roleId=? where id=?";
		return JdbcUtil.executeUpdate(sql, roleId, id);
	}
}
