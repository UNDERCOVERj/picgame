package com.dabai.daoImpl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dabai.dao.UserDao;

public class UserDaoImplTest {

	@Test
	public void testGetUser() {
		UserDao userDao = new UserDaoImpl();
		String name = "dabai";
		System.out.println(userDao.getUser(name).getEasyGrade());
		System.out.println(userDao.getUser(name).getDiffGrade());
		assertEquals("123",userDao.getUser(name).getEasyGrade());
	}
	

	@Test
	public void testIsRegister() {
		UserDao userDao = new UserDaoImpl();
		String name = "lejunj";
		String password = "123";
		//System.out.println(userDao.IsRegister(name, password));;
	}

	@Test
	public void testUpdateGrade(){
		UserDao userDao = new UserDaoImpl();
		String userName = "laoban";
		int grade = 18;
		System.out.println(userDao.updateGrade(userName, grade,"easy"));
	}
}
