<%@page import="todo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<% 
	request.setCharacterEncoding("utf-8");

	HttpSession session = request.getSession(false);
	
	if (session == null) {
		response.sendRedirect("loginform.jsp?err=deny");
		return;
	}
	
	LoginVO user = (LoginVO) session.getAttribute("LOGINED_USER");
	
	if(user == null) {
		response.sendRedirect("loginform.jsp?err=deny");
		return;
	}
	
%>