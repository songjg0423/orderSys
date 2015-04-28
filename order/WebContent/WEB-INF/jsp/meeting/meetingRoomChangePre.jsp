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
	function doIt(targetseq) {
		document.forms[0].targetseq.value=targetseq;
		document.forms[0].submit();
	}
</script>
</head>
<body>
<form name="form1" action="changeConfirm.do" method="post">
<input type="hidden" name="srcseq" value="${meetingseq }"/>
<input type="hidden" name="targetseq" value=""/>
<input type="hidden" name="starttime" value="${starttime }"/>
<input type="hidden" name="endtime" value="${endtime }"/>
<div class="Title" align="center"><span class="mainTitle">
会议信息 </span>: <span class="subTitle"> </span></div>
<table width="100%" border="1" cellpadding="2" cellspacing="0"
	class="submitTable">
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室编号</td>
		<td width="33%" align="left" nowrap class="tdValue"><c:out value="${roomid }"></c:out></td>
		<td width="17%" height="23" nowrap class="tdTitle">会议人数</td>
		<td width="33%" align="left" nowrap class="tdValue"><c:out value="${percount }"></c:out></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议状态</td>
		<td width="33%" align="left" nowrap class="tdValue"><c:out value="${meetingstt }"></c:out></td>
		<td width="17%" height="23" nowrap class="tdTitle">开始时间</td>
		<td width="33%" align="left" nowrap class="tdValue"><c:out value="${starttime }"></c:out></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">结束时间</td>
		<td width="33%" align="left" nowrap class="tdValue"><c:out value="${endtime }"></c:out></td>
		<td width="17%" height="23" nowrap class="tdTitle">价格</td>
		<td width="33%" align="left" nowrap class="tdValue"><c:out value="${price }"></c:out></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">定金</td>
		<td width="33%" align="left" nowrap class="tdValue"><c:out value="${subscription }"></c:out></td>
		<td width="17%" height="23" nowrap class="tdTitle">预订人姓名</td>
		<td width="33%" align="left" nowrap class="tdValue"><c:out value="${bookername }"></c:out></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">预订人电话</td>
		<td width="83%" align="left" nowrap colspan='3' class="tdValue"><c:out value="${bookerphone }"></c:out></td>
	</tr>
</table>
<table width="100%" class="listTable" cellspacing="1" align="center">
<tr class="trTitle">
		<td nowrap class="tdTitle" align="center" >会议室编号</td>
		<td nowrap class="tdTitle" align="center" >会议室大小</td>
	
		<td nowrap class="tdTitle" align="center" >会议室类型</td>
		<td nowrap class="tdTitle" align="center" >路径</td>
		<td nowrap class="tdTitle" align="center" >描述</td>
		<td nowrap class="tdTitle" align="center" >操作</td>
	</tr>
<c:forEach items="${roomList}" var="row">
<tr class="trValue">
			<td nowrap class="tdValue" align="left" ><c:out value="${row.id}" /></td>
			<td nowrap class="tdValue" align="left" ><h:showmsg key='${row.roomlevel}' namespace="roomLevel"/></td>
				
			<td nowrap class="tdValue" align="left" ><h:showmsg key='${row.roomtype}' namespace="roomType"/></td>
			<td nowrap class="tdValue" align="right" ><c:out value="${row.path }" default="" /></td>
			<td nowrap class="tdValue" align="right" ><c:out value="${row.description }" default=""/></td>
			<td nowrap class="tdValue" align="right" ><a href="#" onclick="doIt('${row.roomseq}')">预定</a></td>
		</tr>
</c:forEach>
</table>
<div class="errors">
<span id="EEE">
<h:showmsg key="${errCode}" defaultValue=""/>
</span></div>
<div align="center"><!-- InstanceBeginEditable name="buttons" -->
<input type="button" name="doItButton" class="submitButton" value="返回"
	onclick="history.go(-1)"> <!-- InstanceEndEditable --></div>
</form>
</body>
</html>