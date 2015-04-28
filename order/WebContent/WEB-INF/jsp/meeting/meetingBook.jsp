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
<script type="text/javascript">
    function doIt() {
		document.form1.submit();
    }
</script>
<title>Insert title here</title>
</head>
<body>

<form name="form1" action="meetingAdd.do" method="post">
<input type="hidden" name="roomseq" value="${roomseq }"></input>
<input type="hidden" name="percount" value="${percount }"></input>
<input type="hidden" name="starttime" value="${starttimestr }"></input>
<input type="hidden" name="endtime" value="${endtimestr }"></input>
<input type="hidden" name="price" value="${price }"></input>
<input type="hidden" name="id" value="${id }"></input>
<input type="hidden" name="path" value="${path }"></input>
<input type="hidden" name="roomtype" value="${roomtype }"></input>
<input type="hidden" name="description" value="${description }"></input>
<div class="Title" align="center"><span class="mainTitle">
会议预约确认</span>: <span class="subTitle"> </span></div>
<table width="100%" border="1" cellpadding="2" cellspacing="0"
	class="submitTable">
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室编号</td>
		<td width="83%" align="left" nowrap class="tdValue"><c:out value="${id }"></c:out></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议人数</td>
		<td width="83%" align="left" nowrap class="tdValue"><c:out value="${percount }"></c:out></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室类型</td>
		<td width="83%" align="left" nowrap class="tdValue"><h:showmsg key='${roomtype}' namespace="roomType"/></td>
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
		<td width="17%" height="23" nowrap class="tdTitle">会议开始时间</td>
		<td width="83%" align="left" nowrap class="tdValue"><c:out value="${starttimestr }" default=""/></td>
	</tr>
	
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议结束时间</td>
		<td width="83%" align="left" nowrap class="tdValue"><c:out value="${endtimestr }" default=""/></td>
	</tr>
	
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">价格</td>
		<td width="83%" align="left" nowrap class="tdValue"><c:out value="${price }" default=""/></td>
	</tr>
	
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">定金</td>
		<td width="83%" align="left" nowrap class="tdValue"><input type="text" name="subscription" size="20"></input></td>
	</tr>
	
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">预定人姓名</td>
		<td width="83%" align="left" nowrap class="tdValue"><input type="text" name="bookername" size="20"></input></td>
	</tr>
	
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">预定人电话</td>
		<td width="83%" align="left" nowrap class="tdValue"><input type="text" name="bookerphone" size="20"></input></td>
	</tr>
</table>
<div class="Title" align="center"><span class="mainTitle">
会议人员</span>: <span class="subTitle"> </span></div>
<table width="100%" class="listTable" cellspacing="1" align="center">
<tr class="trTitle">
		<td nowrap class="tdTitle" align="center" >电话</td>
		<td nowrap class="tdTitle" align="center" >姓名</td>
		<td nowrap class="tdTitle" align="center" >id号</td>
	</tr>
	<%
	    int perCount = Integer.parseInt(request.getParameter("percount"));
	    for (int i = 0; i < perCount; i++) {
	%>
	<tr>
	    <td nowrap class="tdValue" align="left" ><input type="text" name="people[<%=i %>].cellphone" size="20"></input></td>
	    <td nowrap class="tdValue" align="left" ><input type="text" name="people[<%=i %>].name" size="20"></input></td>
	    <td nowrap class="tdValue" align="left" ><input type="text" name="people[<%=i %>].cardid" size="30"></input></td>
	</tr>
	<%    	
	    }
	
	%>
	
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