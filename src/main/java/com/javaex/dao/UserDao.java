package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/web_db";
	private String id = "web";
	private String pw = "1234";

	// 메소드 일반
	private void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);

		}

	}// getConnection()

	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}// close()

	public int insertUser(UserVo userVo) {

		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into users ";
			query += " values(null, ?, ?, ?, ?) ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPw());
			pstmt.setString(3, userVo.getName());
			pstmt.setString(4, userVo.getGender());

			// - 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록되었습니다");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();

		return count;
	}

	public UserVo selectUserByIdPw(UserVo userVo) {
		UserVo authUser = null; // 로그인 실패하면 null이 감

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select no, ";
			query += " 		  name ";
			query += " from users ";
			query += " where id=? ";
			query += " and password=? ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPw());

			// - 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				authUser = new UserVo(); // 인증용도로 쓸 계정
				authUser.setNo(no);
				authUser.setName(name);
			}

		} catch (Exception e) {
		}
		this.close();

		return authUser;
	}

	public int modifyUser(int no, String id, String password, String name, String gender) {
		int count = -1;
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update users ";
			query += " set name= ?, ";
			query += "     password= ?, ";
			query += "     gender=? ";
			query += " where id = ? ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			pstmt.setString(3, gender);
			pstmt.setString(3, id);

			// - 실행
			count = pstmt.executeUpdate();

		

		} catch (Exception e) {
		}
		this.close();
		return count;
	}

}
