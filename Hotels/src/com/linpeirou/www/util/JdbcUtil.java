package com.linpeirou.www.util;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JdbcUtil {

	private static String url = "jdbc:mysql://localhost/login?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false";
	private static String username = "root";
	private static String password = "1234";
	private static String jdbcname = "com.mysql.cj.jdbc.Driver";

	/**
	 * 获取连接
	 * 
	 * @return
	 */
	public static Connection getCon() {
		try {
			Class.forName(jdbcname);

		} catch (ClassNotFoundException e) {
			System.out.println("加载数据库连接失败");
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 关闭资源
	 * @param pstmt
	 * @param conn
	 */
	public static void close(PreparedStatement pstmt, Connection conn) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 更新数据,增删减
	 * 
	 * @param sql
	 * @param params
	 * @return 是否成功
	 */
	public static Boolean executeUpdate(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getCon();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps, conn);
		}
		return true;
	}

	/**
	 * 
	 * @param <T>
	 * @param sql
	 * @param classTypes
	 * @param params
	 * @return 查询后的对象
	 */
	public static <T> T executeQueary(String sql, IResultSetHandler<T> rh, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			conn = getCon();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			res = ps.executeQuery();
			return rh.handel(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps, conn);
		}
		return null;
	}

	public static int totalPage(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			conn = JdbcUtil.getCon();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			res = ps.executeQuery();
			if (res.next()) {
				return res.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps, conn);
		}
		return 0;
	}
	
	public static void setTag(String tag, String url, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("tag", tag);
		request.getRequestDispatcher(url).forward(request, response);
		return;
	}
}
