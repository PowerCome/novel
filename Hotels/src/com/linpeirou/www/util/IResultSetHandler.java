package com.linpeirou.www.util;

import java.sql.ResultSet;

public interface IResultSetHandler<T> {
	T handel(ResultSet rs) throws Exception;
}
