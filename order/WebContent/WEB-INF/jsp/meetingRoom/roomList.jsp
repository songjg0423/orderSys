<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="h" uri="/WEB-INF/tld/mytag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
<title>Insert title here</title>
</head>
<body>
<input type="hidden" name="pageNum" value="<c:out value='${pageNum}'/>"></input>
<input type="hidden" name="pageCount" value="<c:out value='${pageCount}'/>"></input>
<div class="Title" align="center"><span class="mainTitle">
会议室查询结果 </span>: <span class="subTitle"> </span></div>
<table cellspacing="0" border="0" width="100%">
<tr>
    <td align="left">当前第<c:out value="${pageNum}"></c:out>页&nbsp;共<c:out value="${pageCount}"></c:out>页&nbsp;<c:out value="${recordCount}"/>条记录</td>
	<td align="right">
	<a href="#" onclick="getFirstPage()">首页</a>&nbsp;&nbsp;
	<c:if test="${pageNum==1}"><a href="#" disabled>上页</a>&nbsp;&nbsp;</c:if><c:if test="${pageNum!=1}"><a href="#" onclick="getPrePage()">上页</a>&nbsp;&nbsp;</c:if>
	<c:if test="${pageNum==pageCount}"><a href="#" disabled>下页</a>&nbsp;&nbsp;</c:if><c:if test="${pageNum!=pageCount}"><a href="#" onclick="getNextPage()">下页</a>&nbsp;&nbsp;</c:if>
	<a href="#" onclick="getBottomPage()">尾页</a></td>
</tr>
</table>
<table width="100%" class="listTable" cellspacing="1" align="center">
<tr class="trTitle">
		<td nowrap class="tdTitle" align="center" >会议室编号</td>
		<td nowrap class="tdTitle" align="center" >会议室大小</td>
	
		<td nowrap class="tdTitle" align="center" >会议室类型</td>
		<td nowrap class="tdTitle" align="center" >路径</td>
		<td nowrap class="tdTitle" align="center" >描述</td>
		<td nowrap class="tdTitle" align="center" >日价</td>
		<td nowrap class="tdTitle" align="center" >时价</td>
		<td nowrap class="tdTitle" align="center" >操作</td>
		
	</tr>
<c:forEach items="${roomList}" var="row">
<tr class="trValue">
			<td nowrap class="tdValue" align="left" ><c:out value="${row.id}" /></td>
			<td nowrap class="tdValue" align="left" ><h:showmsg key='${row.roomlevel}' namespace="roomLevel"/></td>
				
			<td nowrap class="tdValue" align="left" ><h:showmsg key='${row.roomtype}' namespace="roomType"/></td>
			<td nowrap class="tdValue" align="right" ><c:out value="${row.path }" default="" /></td>
			<td nowrap class="tdValue" align="right" ><c:out value="${row.description }" default=""/></td>
			<td nowrap class="tdValue" align="right" ><c:out value="${row.dayprice }" default=""/></td>
			<td nowrap class="tdValue" align="right" ><c:out value="${row.hourprice }" default=""/></td>
			<td nowrap class="tdValue" align="right" ><a href="#" onclick="deleteRoom('${row.roomseq}')">删除</a>&nbsp;&nbsp;<a href="#" onclick="modifyRoom('${row.roomseq}')">修改</a></td>
		</tr>
</c:forEach>
</table>
</body>
</html>