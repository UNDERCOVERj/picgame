package com.dabai.dao;

import com.dabai.entity.User;

public interface UserDao {
	
	/**
	 * 根据用户名密码查找该用户
	 * @param name
	 * @param password
	 * @return
	 */
	User getUser(String name);

	/**
	 * 注册用户
	 * @param name
	 * @param password
	 * @return
	 */
	int IsRegister(String name,String password);
	
	/**
	 * 更新用户分数,判断有无更新
	 * @param userName
	 * @param grade
	 * @param model 游戏模式
	 * @return
	 */
	int updateGrade(String userName,int grade,int model);
	
	/**
	 * 获取所有用户最高分
	 * @return
	 */
	public int getHighestGrade();
}
