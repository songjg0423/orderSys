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
		var newPwd1 = document.getElementById("newPassword1").value;
		var newPwd2 = document.getElementById("newPassword2").value;
		if (newPwd1 != newPwd2) {
			alert("2次新密码不一致 ，请重新输入");
			return;
		}
		document.form1.submit();
	}
</script>
</head>
<body>
<form name="form1" action="pwdModify.do">
<div class="Title" align="center"><span class="mainTitle">
修改密码 </span>: <span class="subTitle"> </span></div>
<table width="100%" border="1" cellpadding="2" cellspacing="0"
	class="submitTable">
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">旧密码</td>
		<td width="83%" align="left" nowrap class="tdValue"><input
			type="password" size="20" name="oldPassword"></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">输入新密码</td>
		<td width="83%" align="left" nowrap class="tdValue"><input
			type="password" size="20" name="newPassword1"></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">确认新密码</td>
		<td width="83%" align="left" nowrap class="tdValue"><input
			type="password" size="20" name="newPassword2"></td>
	</tr>
</table>
<div class="errors">
<span id="EEE">
<c:if test="${errCode != null}">
	<h:showmsg key='${errCode}' />
</c:if>
<c:if test="${errCode == null && modifyFlag==1 }">
	密码修改成功，请牢记新密码。
</c:if>
</span></div>
<div align="center"><!-- InstanceBeginEditable name="buttons" -->
<input type="button" name="doItButton" class="submitButton" value="确定"
	onclick=
	doIt();
> <!-- InstanceEndEditable --></div>
</form>
</body>
</html>