package com.linpeirou.www.view;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.linpeirou.www.service.ServiceForHotel;
import com.linpeirou.www.service.ServiceForRoom;
import com.linpeirou.www.util.Final;

/**
 * Servlet implementation class AddRoomServlet
 */
@WebServlet("/AddRoomServlet")
public class AddRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceForRoom svr = new ServiceForRoom();
		Map<String, String> map = new HashMap<>();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		int isNull = 0;

		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				// 设置上传文件的大小限制。
				sfu.setSizeMax(10 * 1024 * 1024);
				sfu.setHeaderEncoding("utf-8");
				List<FileItem> fileItemList = sfu.parseRequest(request);
				// 判断是否是上传文件
				for (FileItem fileItem : fileItemList) {
					if (fileItem.isFormField()) {
						// 检验是否为空，并保存非上传的值
						if (Final.wrongInput.equals(fileItem.getString("utf-8"))) {
							isNull = 1;
						}
						map.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
					} else {
						String contentType = fileItem.getContentType();
						if (isNull == 1) {
							request.setAttribute("tag", "信息不能为空");
							request.getRequestDispatcher("/ForAdministrator/addRoomInformation.jsp").forward(request, response);
							// 如果不是图片类型则不再对请求进行处理
							return;
						}else if (!contentType.startsWith("image/")) {
							request.setAttribute("hotelId", map.get("hotelId"));
							request.setAttribute("type", map.get("type"));
							request.setAttribute("area", map.get("area"));
							request.setAttribute("price", map.get("price"));
							request.setAttribute("bedWidth", map.get("bedWidth"));
							request.setAttribute("tag", "上传的文件格式不正确，请重新上传");
							request.getRequestDispatcher("/ForAdministrator/addRoomInformation.jsp").forward(request, response);
							// 如果不是图片类型则不再对请求进行处理
							return;
						}
						String fileName = fileItem.getName();
						String suffix = fileName.substring(fileName.lastIndexOf('.'));
						String newFileName = new Date().getTime() + suffix;
						// 写入文件
						File file = new File("E:/webContent/image/" + newFileName);
						fileItem.write(file);
						svr.addRoom(map, newFileName);
						request.setAttribute("hotelId", map.get("hotelId"));
						request.setAttribute("tag", "添加成功");
						request.getRequestDispatcher("/ForAdministrator/addRoomInformation.jsp").forward(request, response);
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
