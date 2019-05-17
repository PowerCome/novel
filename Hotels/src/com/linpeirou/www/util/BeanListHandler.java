package com.linpeirou.www.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandler<T> implements IResultSetHandler<List<T>> {
	private Class<T> classType;
	private List<T> list = new ArrayList<T>();
	public BeanListHandler(Class<T> classType) {
		this.classType = classType;
	}
	
	
	@Override
	public List<T> handel(ResultSet rs) throws Exception {
			while(rs.next()) {
				T obj = this.classType.newInstance();
				BeanInfo beanInfo = Introspector.getBeanInfo(classType,Object.class);
				PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
				for(PropertyDescriptor pd: pds) {
					Object val = rs.getObject(pd.getName());
					pd.getWriteMethod().invoke(obj, val);
				}
				list.add(obj);
			}
			return list;
		}
	}

