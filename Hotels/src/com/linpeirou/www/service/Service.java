package com.linpeirou.www.service;

import com.linpeirou.www.dao.PageBean;
import com.linpeirou.www.dao.RoomDaoImpl;
import com.linpeirou.www.dao.UserDaoImpl;
import com.linpeirou.www.po.User;
import com.linpeirou.www.util.Final;
import com.linpeirou.www.util.PwdEncryption;

public class Service {

	private UserDaoImpl user = new UserDaoImpl();
	/**
	 * 登陆时验证用户是否存在
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public User isExist(String name, String pwd) throws Exception {
		pwd = PwdEncryption.getMD5(pwd);
		User userOne = user.getUser(name, pwd);
		if (userOne == null) {
			return null;
		}
		return userOne;
	}

	/**
	 * 验证注册用户是否存在,同一个电话只能注册一个账号
	 * 
	 * @param name 名字
	 * @param pwd1	密碼
	 * @param pwd2 确认密码
	 * @param idcard 身份证
	 * @param phone 电话
	 * @param roleid 角色
	 * @return 注册用户是否存在
	 * @throws Exception
	 */
	public Boolean register(String name, String pwd1, String pwd2, String idcard, String phone, Integer roleId)
			throws Exception {
		if (pwd1.equals(pwd2)) {
			pwd1 = PwdEncryption.getMD5(pwd1);
			User userOne = user.getUser(name, pwd1, phone);
			if (userOne == null) {
				user.add(name, pwd1, idcard, phone, roleId);
				return true;
			} else {
				throw new RuntimeException("该用户已存在");
			}
		} else {
			throw new RuntimeException("两次输入的密码不一致");
		}
	}

	/**
	 * 判断密码格式是否正确
	 * 
	 * @param 密码
	 * @return 密码格式是否正确
	 */
	public int isRightPassword(String password) {
		// 密码 6-25位数字英文组合
		if (password.length() >= 6 && password.length() <= 25
				&& password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,25}$")) {
			return Final.PSWRIGHT;
		} else {
			return Final.PSWERROR;
		}

	}

	/**
	 * 验证手机格式
	 * 
	 * @param 电话
	 * @return
	 */
	public int isRightPhone(String phone) {
		if (phone.matches("^1(3|4|5|7|8|9)\\d{9}$")) {
			return Final.PSWRIGHT;
		} else {
			return Final.PSWERROR;
		}
	}

	/**
	 * 重设密码
	 * 
	 * @param oldPwd 旧密码
	 * @param newPwd 新密码
	 * @param newPwd2 确认密码
	 * @param name 用户名
	 * @return 重设成功与否
	 */
	public Boolean reSetPwd(String oldPwd, String newPwd, String newPwd2, String name) {
		oldPwd = PwdEncryption.getMD5(oldPwd);
		if (newPwd.equals(newPwd2)) {
			if (user.getUser(name, oldPwd) != null) {
				newPwd = PwdEncryption.getMD5(newPwd);
				user.resetPwd(newPwd, name, oldPwd);
				return true;
			} else {
				throw new RuntimeException("旧密码不存在");
			}
		} else {
			throw new RuntimeException("两次输入的密码不一致");
		}

	}

	/**
	 * 忘记密码
	 * 
	 * @param phone 
	 * @param idcard
	 * @param newPwd
	 * @param newPwd2 确认密码
	 * @return
	 */
	public Boolean forgetPwd(String phone, String idcard, String newPwd, String newPwd2) {
		if (newPwd.equals(newPwd2)) {
			User userOne = user.forgetPwd(phone, idcard);
			if (userOne != null) {
				newPwd = PwdEncryption.getMD5(newPwd);
				user.resetPwd(newPwd, userOne.getName(), userOne.getPwd());
				return true;
			} else {
				throw new RuntimeException("该用户不存在");
			}
		} else {
			throw new RuntimeException("两次输入的密码不一致");
		}

	}

	/**
	 * 分页查找用户（不包含管理员和超级管理员）
	 * @param pc 当前页
	 * @param ps 一页多少记录
	 * @return 当前页的用户
	 */
	public PageBean<User> findSomeUser(int pc, int ps) {
		PageBean<User> pb = new PageBean<User>();
		pb.setPageCode(pc);
		pb.setPageSize(ps);
		pb.setTotalRecord(user.totalPage());
		pb.setBeanList(user.findSomeUser(pc, ps));
		double tp = 1.0 * pb.getTotalRecord() / pb.getPageSize();
		pb.setTotalPage((int) Math.ceil(tp));
		return pb;
	}

	/**
	 * 分页查找所有用户
	 * @param pc
	 * @param ps
	 * @return
	 */
	public PageBean<User> findAllUser(int pc, int ps) {
		PageBean<User> pb = new PageBean<User>();
		pb.setPageCode(pc);
		pb.setPageSize(ps);
		pb.setTotalRecord(user.totalUserPage());
		pb.setBeanList(user.findAllUser(pc, ps));
		double tp = 1.0 * pb.getTotalRecord() / pb.getPageSize();
		pb.setTotalPage((int) Math.ceil(tp));
		return pb;

	}
	
	/**
	 * 处理用户级别
	 * @param method 提升还是下降级别
	 * @param id 用户id
	 * @param roleId 用户角色id
	 */
	public void handleLevel(String method, String id, String roleId) {
		if (Final.UP.equals(method)) {
			user.resetLevel(id, Integer.parseInt(roleId) + 1);
		} else {
			user.resetLevel(id, Integer.parseInt(roleId) - 1);
		}
	}
}
