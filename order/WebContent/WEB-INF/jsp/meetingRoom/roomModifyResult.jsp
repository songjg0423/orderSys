<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="h" uri="/WEB-INF/tld/mytag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
<script type="text/javascript">
	function doIt() {
		document.form1.submit();
	}
</script>
</head>
<body>
<form name="form1" action="meetingRoomQryPre.do" method="post">
<div class="Title" align="center"><span class="mainTitle">
修改会议室结果 </span>: <span class="subTitle"> </span></div>
<table width="100%" border="1" cellpadding="2" cellspacing="0"
	class="submitTable">
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室修改结果</td>
		<td width="83%" align="left" nowrap class="tdValue">成功</td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室编号</td>
		<td width="83%" align="left" nowrap class="tdValue"><c:out value="${id }"></c:out></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室大小</td>
		<td width="83%" align="left" nowrap class="tdValue"><h:showmsg key='${roomLevel}' namespace="roomLevel"/></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室类型</td>
		<td width="83%" align="left" nowrap class="tdValue"><h:showmsg key='${roomType}' namespace="roomType"/></td>
	</tr>
	
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室路径</td>
		<td width="83%" align="left" nowrap class="tdValue"><c:out value="${path }" default="" /></td>
	</tr>
	
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室描述</td>
		<td width="83%" align="left" nowrap class="tdValue"><c:out value="${description }" default=""/></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室日价</td>
		<td width="83%" align="left" nowrap class="tdValue"><c:out value="${dayprice }" default=""/></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室时价</td>
		<td width="83%" align="left" nowrap class="tdValue"><c:out value="${hourprice }" default=""/></td>
	</tr>
</table>
<div class="errors">
<span id="EEE">
</span></div>
<div align="center"><!-- InstanceBeginEditable name="buttons" -->
<input type="button" name="doItButton" class="submitButton" value="确定"
	onclick=
	doIt();
> <!-- InstanceEndEditable --></div>
</form>
</body>
</html>