<?xml version="1.0" encoding="UTF-8" ?>

<%@ page import="com.paranormal.pojo.SignBean" %>
<%@ page import="com.paranormal.pojo.ElementBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.paranormal.dao.SignDao" %>
<%@ page import="com.paranormal.dao.ElementDao" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%
	SignDao sDao = new SignDao();
	ElementDao eDao = new ElementDao();

	ArrayList<SignBean> signs = sDao.getAllSigns();
	ElementBean element = null;
	
	String sign_id = request.getParameter("z_sign");
	
	if(sign_id != null) {
		
		int sign_id_l = Integer.parseInt(sign_id);
		
		element = eDao.getElement(sign_id_l);
	}
	
%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>paranormal: welcome</title>
	</head>
	<body>
		<div>
			<form action="<%=request.getContextPath()%>/pages/welcome.jsp" method="get">
				<div>
					<select name="z_sign">
						<% for(SignBean sign: signs) { %>
							<option value="<%=sign.getSign_id()%>"><%=sign.getName_lat()%></option>
						<% } %>
					</select>
				</div>
				<div>
					<button type="submit">get elements</button>
				</div>
			</form>
		</div>
		<% if(element != null) { %>
		id: <%=element.getElement_id() %> <br />
		element: <%=element.getName_eng() %>
		<% } %>
	</body>
</html>