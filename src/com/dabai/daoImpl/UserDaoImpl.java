package com.dabai.daoImpl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.dabai.dao.UserDao;
import com.dabai.entity.User;
import com.dabai.util.DBUtils;
import com.dabai.util.ValueUtils;

public class UserDaoImpl implements UserDao {
	
	
	@Override
	public User getUser(String name) {
		String sql = "select * from users where username like ?";
		PreparedStatement pstmt = DBUtils.getPrePareStatement(sql);
		User user = new User();
		try {
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				//System.out.println(rs.getString("1"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEasyGrade(rs.getInt("easy_grade"));
				user.setDiffGrade(rs.getInt("diff_grade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int IsRegister(String name, String password) {
		String sql = "insert into users(username,password) values(?,?)";
		PreparedStatement pstmt = DBUtils.getPrePareStatement(sql);
		int affectLine = 0;
		try {
			pstmt.setString(1,name);
			pstmt.setString(2, password);
			affectLine =  pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return affectLine;
	}

	@Override
	public int updateGrade(String userName, int grade,int model) {
		String sql = "call update_grade(?,?,?,?)";
		int affectLine = -1;
		try {
			CallableStatement cstmt = DBUtils.getConn().prepareCall(sql);
			cstmt.setString(1,userName);
			cstmt.setInt(2,grade);
			cstmt.setInt(3,model);
			cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
			cstmt.executeQuery();
			affectLine = cstmt.getInt(4);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return affectLine;
	}

	
	/**
	 * 获取最高成绩
	 * @return
	 */
	public int getHighestGrade() {
		String sql = "select MIN(grade) from users";
		int highestGrade = Integer.MAX_VALUE;
		PreparedStatement pstmt = DBUtils.getPrePareStatement(sql);
		ResultSet rs;
		try {
			rs = pstmt.executeQuery();
			highestGrade = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return highestGrade;
	}
}
