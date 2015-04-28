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
会议列表 </span>: <span class="subTitle"> </span></div>
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
		<td nowrap class="tdTitle" align="center" >会议人数</td>	
		<td nowrap class="tdTitle" align="center" >会议状态</td>
		<td nowrap class="tdTitle" align="center" >开始时间</td>
		<td nowrap class="tdTitle" align="center" >结束时间</td>
		<td nowrap class="tdTitle" align="center" >价格</td>
		<td nowrap class="tdTitle" align="center" >定金</td>
		<td nowrap class="tdTitle" align="center" >预定人姓名</td>
		<td nowrap class="tdTitle" align="center" >预定人电话</td>
		<td nowrap class="tdTitle" align="center" >操作</td>
	</tr>
<c:forEach items="${meetingList}" var="row">
<tr class="trValue">
			<td nowrap class="tdValue" align="left" ><c:out value="${row.roomid}" /></td>
			<td nowrap class="tdValue" align="left" ><c:out value="${row.percount}" /></td>			
			<td nowrap class="tdValue" align="left" ><h:showmsg key='${row.meetingstt}' namespace="meetingStt"/></td>
			<td nowrap class="tdValue" align="right" ><c:out value="${row.starttime }" default="" /></td>
			<td nowrap class="tdValue" align="right" ><c:out value="${row.endtime }" default=""/></td>
			<td nowrap class="tdValue" align="right" ><c:out value="${row.price}" default=""/></td>
			<td nowrap class="tdValue" align="right" ><c:out value="${row.subscription }" default=""/></td>
			<td nowrap class="tdValue" align="right" ><c:out value="${row.bookername }" default=""/></td>
			<td nowrap class="tdValue" align="right" ><c:out value="${row.bookerphone }" default=""/></td>
			<td nowrap class="tdValue" align="right" ><a href="#" onclick="qryMeetingDetail('${row.meetingseq}')">详情</a>
			<a href="#" onclick="changeRoom('${row.meetingseq}')">换会议室</a>
			<a href="#" onclick="cancelMeeting('${row.meetingseq}')">取消</a>
			</td>
		</tr>
</c:forEach>
</table>
</body>
</html>