import com.linpeirou.www.dao.HotelDaoImpl;
import com.linpeirou.www.dao.PageBean;
import com.linpeirou.www.po.Hotel;
import com.linpeirou.www.service.ServiceForHotel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class test {

	public  <T> PageBean<T> findSome(int pc, int ps, Class clazz,String name1, String name2) throws Exception {
		PageBean<T> pb = new PageBean<T>();
		pb.setPageCode(pc);
		pb.setPageSize(ps);
		pb.setTotalRecord((Integer)doMethod(clazz, name1)); 
		pb.setBeanList((List)doMethod(clazz,name2, pc, ps));
		double tp = 1.0*pb.getTotalRecord()/pb.getPageSize();
		pb.setTotalPage((int) Math.ceil(tp));
		System.out.println(pb);
		return (PageBean<T>) pb;
	}
	
	public Object doMethod(Class clazz,String name, Object... params) throws Exception{
		Method method = null;
		Constructor constructor = clazz.getConstructor();
		Object newInstance = constructor.newInstance();
		
		if(params.length == 0) {
			method = clazz.getMethod(name);
			return method.invoke(newInstance);
		}else {
			method = clazz.getMethod(name,Integer.class, Integer.class);
			return method.invoke(newInstance, params[0],params[1]);
		}
	}
	
	public static void main(String[] args) throws Exception {
		test test = new test();
		test.findSome(1,10,HotelDaoImpl.class, "totalPage", "findSomeHotel");
		ServiceForHotel serviceForHotel = new ServiceForHotel();
		System.out.println(serviceForHotel.findSomeHotel(1, 10));
		}
		

}
