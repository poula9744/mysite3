package com.javaex.vo;

public class UserVo {
	
	//필드
	private int no;
	private String id;
	private String pw;
	private String name;
	private String gender;
	
	//생성자
	public UserVo() {
		super();
	}
	
	

	public UserVo(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}


	public UserVo(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	
	public UserVo(String pw, String name, String gender) {
		super();
		this.pw = pw;
		this.name = name;
		this.gender = gender;
	}



	public UserVo(String id, String pw, String name, String gender) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
	}


	public UserVo(int no, String id, String pw, String name, String gender) {
		super();
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
	}

	
	//메소드 - g/s
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	//메소드 - 일반
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender + "]";
	}
	
	
}
