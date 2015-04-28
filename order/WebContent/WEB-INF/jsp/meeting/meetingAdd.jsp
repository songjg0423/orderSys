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
<script language="javascript"
	src="<%=request.getContextPath()%>/js/calendar.js"></script>
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

	function doIt(pageDate) {
		var beginDate = document.getElementsByName('BeginDate')[0].value;
		var endDate = document.getElementsByName('EndDate')[0].value;
		if (beginDate == null || beginDate == "") {
			alert("开始日期不能为空");
			return;
		}
		if (endDate == null || endDate == "") {
			alert("结束日期不能为空");
			return;
		}

		getXmlHttp();
		var roomId = document.getElementsByName('id')[0].value;
		var percount = document.getElementsByName('percount')[0].value;
		var roomTypeRadio = document.getElementsByName('roomType');
		var roomType = getRadioValue(roomTypeRadio);
		var beginHour = document.getElementsByName('beginHour')[0].value;
		var beginMinute = document.getElementsByName('beginMinute')[0].value;
		var endHour = document.getElementsByName('endHour')[0].value;
		var endMinute = document.getElementsByName('endMinute')[0].value;

		var url = "<%=request.getContextPath()%>/availableRoomQry.do";
		var data = "id=" + roomId + "&percount=" + percount + "&roomType=" + roomType + "&beginDate=" + beginDate + "&endDate=" + endDate + "&beginHour=" + beginHour + "&beginMinute=" + beginMinute + "&endHour=" + endHour + "&endMinute=" + endMinute;
		url = url + "?" + data + "&" + pageDate;
		xmlHttp.open("POST", url, true);
		xmlHttp.onreadystatechange = updatePage;
		xmlHttp.send(null);
	}

	function updatePage() {
    	if (xmlHttp.readyState == 4) {
   	     if (xmlHttp.status == 200) {
   	      var response = xmlHttp.responseText;
   	      document.getElementById('AAAA').innerHTML=response;
   	     } else{

       	 }
   	   }
    }

	function getRadioValue(radio) {
		for (var i = 0; i < radio.length; i++) {
			if (radio[i].checked) {
				return radio[i].value;
			}
		}
		return "";
    }


    function getFirstPage() {
		var pageData = "pageNum=1";
		doIt(pageData);
    }

    function getNextPage() {
		var pageNum = document.getElementsByName("pageNum")[0].value;
		var newPageNum = parseInt(pageNum) + 1;
		var pageData = "pageNum=" + newPageNum;
		doIt(pageData);
    }

    function getBottomPage() {
    	var pageNum = document.getElementsByName("pageCount")[0].value;
		var newPageNum = parseInt(pageNum);
		var pageData = "pageNum=" + newPageNum;
		doIt(pageData);
    }

    function getPrePage() {
    	var pageNum = document.getElementsByName("pageNum")[0].value;
		var newPageNum = parseInt(pageNum) - 1;
		var pageData = "pageNum=" + newPageNum;
		doIt(pageData);
    }

    function bookMeeting(roomseq) {
		document.getElementsByName("roomseq")[0].value=roomseq;
		document.form1.action="meetingBook.do";
		document.form1.submit();
    }
    
</script>
<title>Insert title here</title>
</head>
<body>

<form name="form1" action="availableRoomQry.do" method="post">
<input type="hidden" name="roomseq" value=""/>
<div class="Title" align="center"><span class="mainTitle">
会议预约 </span>: <span class="subTitle"> </span></div>
<table width="100%" border="1" cellpadding="2" cellspacing="0"
	class="submitTable">
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室编号</td>
		<td width="83%" align="left" nowrap class="tdValue"><input
			type="text" size="20" name="id"></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议人数</td>
		<td width="83%" align="left" nowrap class="tdValue"><input
			type="text" size="20" name="percount"></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室类型</td>
		<td width="83%" align="left" nowrap class="tdValue"><h:radio
			key="roomType"></h:radio></td>
	</tr>

	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">预定开始时间</td>
		<td width="83%" align="left" nowrap class="tdValue"><input
			name="BeginDate" id="BeginDate" type="text" maxlength="10" size="22"
			DateFormatString="yyyy-MM-dd" AUTOCOMPLETE="off" onclick="calendar();">
		<h:hour name="beginHour"></h:hour>&nbsp;&nbsp;<h:time name="beginMinute"></h:time>
		<font color="#FF0000">*</font></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">预定结束时间</td>
		<td width="83%" align="left" nowrap class="tdValue"><input
			name="EndDate" id="EndDate" type="text" maxlength="10" size="22"
			DateFormatString="yyyy-MM-dd" AUTOCOMPLETE="off" onclick="calendar();">
		<h:hour name="endHour"></h:hour>&nbsp;&nbsp;<h:time name="endMinute"></h:time>
		<font color="#FF0000">*</font></td>
	</tr>
</table>
<div class="errors"><span id="EEE"> <h:showmsg
	key="${errCode}" defaultValue="" /> </span></div>
<div align="center"><!-- InstanceBeginEditable name="buttons" -->
<input type="button" name="doItButton" class="submitButton" value="查询可用"
	onclick='doIt("pageNum=1")'> <!-- InstanceEndEditable --></div>
<span id="AAAA"></span>
</form>
</body>
</html>