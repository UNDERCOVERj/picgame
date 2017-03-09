package com.dabai.daoImpl;

import org.junit.Test;

import com.dabai.dao.UserDao;
import com.dabai.util.ValueUtils;

public class UserDaoImplTest {

	@Test
	public void testUpdateGrade() {
		UserDao userDao = new UserDaoImpl();
		System.out.println(userDao.updateGrade("dabai", 
				ValueUtils.getInstance().getModelMap().get("easy"),20));
	}

}
