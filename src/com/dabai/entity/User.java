package com.dabai.entity;

public class User {
	private String username;
	private String password;
	private int easyGrade;
	private int diffGrade;
	
	
	public int getEasyGrade() {
		return easyGrade;
	}
	public void setEasyGrade(int easyGrade) {
		this.easyGrade = easyGrade;
	}
	public int getDiffGrade() {
		return diffGrade;
	}
	public void setDiffGrade(int diffGrade) {
		this.diffGrade = diffGrade;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}
