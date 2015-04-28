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

<script type="text/javascript">
	function doIt() {
		var roomId = document.getElementsByName("id")[0].value;
		if (roomId == null || roomId=="") {
			alert("会议室编号不能为空");
			return;
		}

		var roomLevel = document.getElementsByName("roomLevel")[0].value;
		if (roomLevel == null || roomLevel=="") {
			alert("会议室大小不能为空");
			return;
		}

		var roomType = document.getElementsByName("roomType")[0].value;
		if (roomType == null || roomType=="") {
			alert("会议室类型不能为空");
			return;
		}
		document.form1.submit();
	}
</script>
</head>
<body>
<form name="form1" action="meetingRoomAdd.do" method="post">
<div class="Title" align="center"><span class="mainTitle">
新增会议室 </span>: <span class="subTitle"> </span></div>
<table width="100%" border="1" cellpadding="2" cellspacing="0"
	class="submitTable">
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室编号</td>
		<td width="83%" align="left" nowrap class="tdValue"><input
			type="text" size="20" name="id"><font color="#FF0000">*</font></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室大小</td>
		<td width="83%" align="left" nowrap class="tdValue"><h:radio key="roomLevel"></h:radio><font color="#FF0000">*</font></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室类型</td>
		<td width="83%" align="left" nowrap class="tdValue"><h:radio key="roomType"></h:radio><font color="#FF0000">*</font></td>
	</tr>
	
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室路径</td>
		<td width="83%" align="left" nowrap class="tdValue"><input
			type="text" size="20" name="path"></td>
	</tr>
	
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室描述</td>
		<td width="83%" align="left" nowrap class="tdValue"><input
			type="text" size="20" name="description"></td>
	</tr>
	
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室日价</td>
		<td width="83%" align="left" nowrap class="tdValue"><input
			type="text" size="20" name="dayPrice"></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室时价</td>
		<td width="83%" align="left" nowrap class="tdValue"><input
			type="text" size="20" name="hourPrice"></td>
	</tr>
</table>
<div class="errors">
<span id="EEE">
<h:showmsg key="${errCode}" defaultValue=""/>
</span></div>
<div align="center"><!-- InstanceBeginEditable name="buttons" -->
<input type="button" name="doItButton" class="submitButton" value="确定"
	onclick=
	doIt();
> <!-- InstanceEndEditable --></div>
</form>
</body>
</html>