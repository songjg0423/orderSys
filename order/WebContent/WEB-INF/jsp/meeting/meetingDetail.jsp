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
var xmlHttp = false;

function getXmlHttp() {
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e2) {
			xmlHttp = false;
		}
	}
}

	function doIt() {
		document.forms[0].submit();
	}

	function deletePerson(peopleSeq) {
		getXmlHttp();
		var url = "<%=request.getContextPath()%>/peopleDelete.do";
		var data = "peopleseq=" + peopleSeq + "&meetingseq=" + document.form1.meetingseq.value;
		url = url + "?" + data;
		xmlHttp.open("POST", url, true);
		xmlHttp.onreadystatechange = updatePage;
		xmlHttp.send(null);
	}

	function updatePage() {
    	if (xmlHttp.readyState == 4) {
   	     if (xmlHttp.status == 200) {
   	    	document.form1.action='qryMeetingDetail.do';
   			document.form1.submit();
   	     } else{

       	 }
   	   }
    }

    function showInsertTable(){
		document.getElementById("inserttable").style.display="";
    }

    function addPerson(){
    	getXmlHttp();
    	var url = "<%=request.getContextPath()%>/peopleAdd.do";
    	var name = document.form1.name.value;
    	var cellphone = document.form1.cellphone.value;
    	var cardid = document.form1.cardid.value;
    	var meetingseq = document.form1.meetingseq.value;
    	var data = "name=" + name + "&cellphone=" + cellphone + "&cardid=" + cardid + "&meetingseq=" + meetingseq;
    	url = url + "?" + data;
    	url = encodeURI(url);
    	url = encodeURI(url);
    	xmlHttp.open("POST", url, true);
    	xmlHttp.onreadystatechange = updatePage;
    	xmlHttp.setRequestHeader('Content-type','application/x-www-form-urlencoded;charset=UTF-8');
		xmlHttp.send(null);
    }
</script>
</head>
<body>
<form name="form1" action="meetingQryPre.do" method="post">
<input type="hidden" name="meetingseq" value="${meetingseq }">
<div class="Title" align="center"><span class="mainTitle">
会议详情 </span>: <span class="subTitle"> </span></div>
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
<div class="Title" align="center"><span class="mainTitle">
会议人员列表 </span>: <span class="subTitle"> </span></div>
<table width="100%" class="listTable" cellspacing="1" align="center">
<tr class="trTitle">
		<td nowrap class="tdTitle" align="center" >姓名</td>
		<td nowrap class="tdTitle" align="center" >电话</td>	
		<td nowrap class="tdTitle" align="center" >卡id</td>
		<td nowrap class="tdTitle" align="center" >操作</td>
	</tr>
<c:forEach items="${peopleList}" var="row">
<tr class="trValue">
			<td nowrap class="tdValue" align="left" ><c:out value="${row.name}" /></td>
			<td nowrap class="tdValue" align="left" ><c:out value="${row.cellphone}" /></td>			
			<td nowrap class="tdValue" align="left" ><c:out value="${row.cardid}" /></td>
			<td nowrap class="tdValue" align="left" ><a href="#" onclick="deletePerson('${row.peopleseq}');">删除</a></td>
		</tr>
</c:forEach>
</table>

<table id="inserttable" width="100%" class="listTable" cellspacing="1" align="center" style="display:none">
<tr class="trTitle">
		<td nowrap class="tdTitle" align="center" >姓名</td>
		<td nowrap class="tdTitle" align="center" >电话</td>	
		<td nowrap class="tdTitle" align="center" >卡id</td>
		<td nowrap class="tdTitle" align="center" >操作</td>
	</tr>
<tr class="trValue">
			<td nowrap class="tdValue" align="left" ><input type="text" name="name"></input></td>
			<td nowrap class="tdValue" align="left" ><input type="text" name="cellphone"></input></td>			
			<td nowrap class="tdValue" align="left" ><input type="text" name="cardid"></input></td>
			<td nowrap class="tdValue" align="left" ><input type="button" value="确定" onclick="addPerson();"></input></td>
		</tr>
</table>
<div class="errors">
<span id="EEE">
<h:showmsg key="${errCode}" defaultValue=""/>
</span></div>
<div align="center"><!-- InstanceBeginEditable name="buttons" -->
<input type="button" name="doItButton" class="submitButton" value="返回"
	onclick=
	doIt();
>
<input type="button" name="doItButton" class="submitButton" value="增加与会人员"
	onclick="showInsertTable();";
> <!-- InstanceEndEditable --></div>
</form>
</body>
</html>