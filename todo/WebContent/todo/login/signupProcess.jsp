<%@page import="todo.LoginVO"%>
<%@page import="util.MD5"%>
<%@page import="todo.LoginDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String pwd = MD5.hash(request.getParameter("pwd")); 
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	String addr = request.getParameter("addr");

	LoginVO user = new LoginVO();
	user.setId(id);
	user.setPwd(pwd);
	user.setName(name);
	user.setPhone(phone);
	user.setAddr(addr);
	
	LoginDAO dao = new LoginDAO();
	dao.addUser(user);
	
	response.sendRedirect("loginform.jsp");
%>