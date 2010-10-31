package com.paranormal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.paranormal.pojo.ElementBean;

public class ElementDao {

	public ElementBean getElement(int sign_id) throws NamingException, SQLException, ClassNotFoundException {
		
		ElementBean result = null;

		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/para");
		Connection conn = ds.getConnection();

		String sql = "SELECT e.element_id, e.name_eng ";
		sql += "FROM element e ";
		sql += "JOIN sign_element se ON se.element_id = e.element_id ";
		sql += "JOIN sign s  ON se.sign_id = s.sign_id ";
		sql += "WHERE s.sign_id = ? ";
		
		
		PreparedStatement stat = conn.prepareStatement(sql);
		
		stat.setInt(1, sign_id);
		
		ResultSet rs = stat.executeQuery();
		
		rs.next();
		result = new ElementBean();
		result.setElement_id(rs.getInt("element_id"));
		result.setName_eng(rs.getString("name_eng"));
		
		conn.close();
		
		return result;
	}
}
