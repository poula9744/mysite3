package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserController");
		
		//user에서 업무구분
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("joinform".equals(action)) {
			System.out.println("joinform: 등록");
			
			//회원가입 폼
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");
			
		}else if("join".equals(action)) {
			System.out.println("user>join");
			
			//파라미터에서 데이터 꺼내기
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			//데이터를 Vo로 묶기
			UserVo userVo = new UserVo(id, password, name, gender);
			System.out.println(userVo);

			//dao의 메소드로 회원가입
			UserDao userDao = new UserDao();
			userDao.insertUser(userVo);
			
			//joinOk.jsp 포워드
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinOk.jsp");
			
		} else if("loginform".equals(action)) {
			System.out.println("login: 로그인");
			
			//로그인 폼
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");
		}else if("login".equals(action)) {
			
			
			
		} else {
			System.out.println("action값을 다시 확인해주세요.");
		}
		
	}
	
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
