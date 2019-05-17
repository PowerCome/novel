package com.linpeirou.www.util;
import com.linpeirou.www.dao.PageBean;
import com.linpeirou.www.po.Hotel;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ServiceUtil {
	
//	public static Object doMethod(Class clazz,String name, Object... params) throws Exception{
//		Method method = null;
//		Constructor constructor = clazz.getConstructor();
//		Object newInstance = constructor.newInstance();
//		if(params.length == 0) {
//			method = clazz.getMethod(name);
//			return method.invoke(newInstance);
//		}else if(params.length == 1){
//			method = clazz.getMethod(name,String.class);
//			return method.invoke(newInstance, params[0]);
//		}else if(params.length == 2){
//			method = clazz.getMethod(name,Integer.class, Integer.class);
//			return method.invoke(newInstance, params[0],params[1]);
//		}else {
//			method = clazz.getMethod(name, String.class, Integer.class, Integer.class);
//			return method.invoke(newInstance, params[0],params[1], params[2]);
//		}
//	}
//	
//	public static <T> void getPb(HttpServletRequest request, Class<?> clazz,  String name, PageBean<T> pb ,String...params){
//		try {
//			Integer ps = 10;
//			Integer pc = getPc(request);
//			request.setCharacterEncoding("UTF-8");
//			String name1 = "totalPage";
//			pb.setPageCode(pc);
//			pb.setPageSize(ps);
//			//查找总页数
//			if(params.length != 0) {
//				pb.setTotalRecord((Integer)doMethod(clazz, name1, params[0])); 
//				pb.setBeanList((List<T>)doMethod(clazz, name, params[0], pc, ps));
//			}else {
//				pb.setTotalRecord((Integer)doMethod(clazz, name1)); 
//				pb.setBeanList((List<T>)doMethod(clazz, name, pc, ps));
//			}
//			double tp = 1.0*pb.getTotalRecord()/pb.getPageSize();
//			pb.setTotalPage((int) Math.ceil(tp));
//			request.setAttribute("pb", pb);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static int getPc(HttpServletRequest request) {
//		String value = request.getParameter("pc");
//		if (value == null || value.trim().isEmpty()) {
//			return 1;
//		}
//		return Integer.parseInt(value);
//	}
//	
//	public <T> PageBean<T> getRecord(Integer pageCurrent, Integer pageSize, Object... params) throws Exception {
//		String methodOne = "getTotalRecord";
//		String methodTwo = "getRecord";
//		PageBean<T> record = new PageBean<T>();
//		// 一页几条记录
//		record.setPageSize(pageSize);
//		// 根据参数长度执行不同方法获取总记录数，记录的list集合
//		if (params.length == 1) {
//			record.setTotalRecord(
//					(int) chatRecord.getClass().getMethod(methodOne, Integer.class).invoke(chatRecord, params[0]));
//			record.setBeanList((List<T>) chatRecord.getClass()
//					.getMethod(methodTwo, Integer.class, Integer.class, Integer.class, Integer.class)
//					.invoke(chatRecord, pageCurrent, pageSize, params[0]));
//		} else {
//			record.setTotalRecord((int) chatRecord.getClass().getMethod(methodOne, Integer.class, Integer.class)
//					.invoke(chatRecord, params[0], params[1]));
//			record.setBeanList((List<T>) chatRecord.getClass()
//					.getMethod(methodTwo, Integer.class, Integer.class, Integer.class, Integer.class)
//					.invoke(chatRecord, pageCurrent, pageSize, params[0], params[1]));
//		}
//		// 总页数
//		Double totalPage = 1.0 * record.getTotalRecord() / record.getPageSize();
//		record.setTotalPage((int) Math.ceil(totalPage));
//		// 如果当前页码为0，则默认显示最后一页
//		if (pageCurrent == 0) {
//			pageCurrent = record.getTotalPage();
//		}
//		record.setPageCurrent(pageCurrent);
//		System.out.println("pageCurrent:" + pageCurrent);
//		return record;
//	}
//	
//	/**
//	 * 批处理
//	 * @param sql
//	 * @param number 一个语句未知参数的个数
//	 * @param params 数组，匹配所有执行语句的问号
//	 */
//	
//	public static void executeQuearyBatch(String sql, int number, String[] params) {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		long begin = System.currentTimeMillis();
//		try {
//			conn = getCon();
//			ps = conn.prepareStatement(sql);
//			//执行语句的次数为参数长度/每个语句需要的参数
//			for (int i = 0, k = 0, frequency = params.length/number; i < frequency;) {
//				for(int j = 0; j < number; j++, k += frequency) {
//					ps.setObject(j + 1, params[k]);
//				}
//				k = ++i;
//				ps.addBatch();
//			}
//			ps.executeBatch();
//			long end = System.currentTimeMillis();
//			long time = end - begin;
//			System.out.println(time);
//			ps.clearBatch();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(ps, conn, null);
//		}
//	}

}
