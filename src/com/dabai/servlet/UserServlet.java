package com.dabai.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dabai.dao.UserDao;
import com.dabai.daoImpl.UserDaoImpl;
import com.dabai.entity.User;
import com.dabai.util.ValueUtils;
import com.google.gson.Gson;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name="UserServlet",urlPatterns="/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserDao userDao = new UserDaoImpl();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("login"))
			this.login(request,response);
		else if(method.equalsIgnoreCase("register"))
			this.register(request,response);
		else if(method.equalsIgnoreCase("updateGrade"))
			this.updateGrade(request,response);
	}

	/**
	 * 更新用户最高分，并且得到全部用户最高分
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateGrade(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//从session中取出user对象
		User user = (User) request.getSession().getAttribute("user");
		//获得游戏分数
		int tempGrade = Integer.valueOf(request.getParameter("grade"));	
		//获得游戏模式
		String mode = request.getParameter("mode");	
		//获取用户最高分
		tempGrade  = user.getEasyGrade()>tempGrade?tempGrade:user.getEasyGrade();	
		//更新用户最高分数据
		userDao.updateGrade(user.getUsername(), tempGrade, 
					ValueUtils.getInstance().getModelMap().get(mode));
		//返回json字符串
		String returnJson = "{\"mode\":\""+mode+"\",\"highestGrade\":"+tempGrade+"}";
		System.out.println(returnJson);
		response.setContentType("text/json");
		response.getWriter().print(returnJson);
		response.getWriter().flush();
	}

	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String data  = request.getParameter("data");
		Gson gson = new Gson();
		User user = gson.fromJson(data,User.class);
		if(userDao.IsRegister(user.getUsername(), user.getPassword())>0){
			request.getSession().setAttribute("user", userDao.getUser(user.getUsername()));
			response.setContentType("text/json");
			response.getWriter().print(gson.toJson(user));
			response.getWriter().flush();
		}
	}
  
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void  login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String data  = request.getParameter("data");
		Gson gson = new Gson();
		String userName = gson.fromJson(data, User.class).getUsername();
		User user = userDao.getUser(userName);
		if(user != null){
			//如果登陆成功，则session设置userName
			request.getSession().setAttribute("user", user);
			//返回user的json字符串
			String returnJson = gson.toJson(user);
			response.setContentType("text/json");
			response.getWriter().print(returnJson);
			response.getWriter().flush();
		}

	}

}
