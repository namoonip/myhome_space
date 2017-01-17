<%@page import="todo.TodoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<% 
	TodoDAO dao = new TodoDAO();
	dao.completeTodo(Integer.parseInt(request.getParameter("no")));
	response.sendRedirect("index.jsp");
%>