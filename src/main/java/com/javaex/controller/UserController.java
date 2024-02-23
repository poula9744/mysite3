package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			System.out.println("user>login");
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			System.out.println(id);
			System.out.println(password);
			
			UserVo userVo = new UserVo(id, password); //id pw
			
			UserDao userDao = new UserDao();
			UserVo authUser = userDao.selectUserByIdPw(userVo); //no name
			
			if(authUser != null) {//로그인 성공 
				HttpSession session = request.getSession();
				session.setAttribute("authUser", authUser);
				session.setAttribute("userVo", userVo);
				
				WebUtil.redirect(request, response, "/mysite3/main");
				
				System.out.println(authUser);
				System.out.println(userVo);

				
			} else { //로그인 실패
				System.out.println("로그인 실패");
				WebUtil.redirect(request, response, "/mysite3/user?action=loginform");
			}
		} else if("logout".equals(action)) {
			System.out.println("user>logout");
			
			HttpSession session = request.getSession();
			session.invalidate(); //session 지우기
			
			WebUtil.redirect(request, response, "/mysite3/main");
		} else if("modifyform".equals(action)) {
			System.out.println("modifyform: 수정");
			
			//수정폼
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyForm.jsp");
			
		} else if("modify".equals(action)) {
			System.out.println("user>modify");
			
			//파라미터 가져오기
			int no = Integer.parseInt(request.getParameter("no"));
			String id = request.getParameter("id"); 
			String name = request.getParameter("name"); 
			String password = request.getParameter("password");
		    String gender = request.getParameter("gender");
		    
		    System.out.println(no);
		    System.out.println(id);
		    System.out.println(name);
		    System.out.println(password);
		    System.out.println(gender);
		    
		    UserDao userDao = new UserDao();
		    userDao.modifyUser(no, id, password, name, gender);
		    
		    // 수정된 정보로 사용자 객체 업데이트
		    UserVo modifiedUser = new UserVo(no, id, password, name, gender);

		    // 세션에서 기존 사용자 정보 가져오기
		    HttpSession session = request.getSession();
		    UserVo authUser = (UserVo) session.getAttribute("authUser");

		    // 세션에 수정된 사용자 정보 업데이트	    
		    session.setAttribute("authUser", modifiedUser);
			
			WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
			
		} else {
			System.out.println("action값을 다시 확인해주세요.");
		}
		
	}
	
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
