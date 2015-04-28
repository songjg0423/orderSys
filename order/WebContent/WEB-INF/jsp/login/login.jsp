<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="h" uri="/WEB-INF/tld/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登陆会议管理系统</title>
</head>
<body>
<form name="loginForm" action="login.do" method="post">
用户名：<input type="text" name="username" size="20"><br>
密 &nbsp; &nbsp;码：<input type="password" name="password" size="20"><br>
<c:if test="${errCode != null}">
<h:showmsg key='${errCode}'/>
</c:if>
<input type="submit" value="提交">
</form>
</body>
</html>