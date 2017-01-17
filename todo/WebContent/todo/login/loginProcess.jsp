<%@page import="todo.LoginVO"%>
<%@page import="todo.LoginDAO"%>
<%@page import="util.MD5"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
    
 <% 
 	request.setCharacterEncoding("utf-8");
 	String id = request.getParameter("id");
 	String pwd = request.getParameter("pwd");
 	
 	LoginDAO dao = new LoginDAO();
 	LoginVO user = dao.loginCheckById(id);
 	
 	if (user == null) {
 		response.sendRedirect("loginform.jsp?err=fail");
 		return;
 	}
 	
 	String secuPwd = MD5.hash(pwd);
 	if (secuPwd.equals(user.getPwd())) {
 		HttpSession session = request.getSession(true);
 		session.setAttribute("LOGINED_USER", user);
 		
 		response.sendRedirect("../index.jsp");
 	} else {
 		response.sendRedirect("loginform.jsp?err=fail");
 		return;
 	}
 
 %>