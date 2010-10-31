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

import com.paranormal.pojo.SignBean;

public class SignDao {

	public ArrayList<SignBean> getAllSigns() throws NamingException, SQLException, ClassNotFoundException {
		
		ArrayList<SignBean> results = new ArrayList<SignBean>();

		
		
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/para");
		Connection conn = ds.getConnection();

		
		String sql = "SELECT sign_id, name_lat FROM sign ORDER BY sign_id";
		
		
		PreparedStatement stat = conn.prepareStatement(sql);
		ResultSet rs = stat.executeQuery();
		
		while(rs.next()) {
			
			SignBean sign = new SignBean();
			sign.setSign_id(rs.getInt("sign_id"));
			sign.setName_lat(rs.getString("name_lat"));
			
			results.add(sign);
		}
		
		conn.close();
		
		return results;
	}
}
