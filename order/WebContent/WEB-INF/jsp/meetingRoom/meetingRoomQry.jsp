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
    function doIt(pageDate) {
		getXmlHttp();
		var roomId = document.getElementsByName('id')[0].value;
		var roomLevelRadio = document.getElementsByName('roomLevel');
		var roomLevel = getRadioValue(roomLevelRadio);
		var roomTypeRadio = document.getElementsByName('roomType');
		var roomType = getRadioValue(roomTypeRadio);

		var url = "<%=request.getContextPath()%>/meetingRoomQry.do";
		var data = "id=" + roomId + "&roomLevel=" + roomLevel + "&roomType=" + roomType;
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

    var xmlHttp = false;

    function getXmlHttp(){
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

    function refreshPage() {
    	if (xmlHttp.readyState == 4) {
      	     if (xmlHttp.status == 200) {
      	    	var pageNum = document.getElementsByName("pageNum")[0].value;
      	    	var pageData = "pageNum=" + pageNum;
      	    	doIt(pageData);
      	     } else{

          	 }
      	   }
        
    	
    }

    function deleteRoom(roomseq) {
        if (confirm("确定删除？")) {
        	getXmlHttp();

    		var url = "<%=request.getContextPath()%>/meetingRoomDel.do";
    		url = url + "?" + "roomseq=" + roomseq;
    		xmlHttp.open("POST", url, true);
    		
    		xmlHttp.onreadystatechange = refreshPage;
    		xmlHttp.send(null);
        }
    }

    function modifyRoom(roomseq) {
		document.getElementsByName('roomseq')[0].value=roomseq;
		document.form1.action="meetingRoomModifyPre.do";
		document.form1.submit();
    }
</script>
</head>
<body>
<form name="form1" action="meetingRoomQry.do" method="post">
<input type="hidden" name="roomseq" value="" />
<div class="Title" align="center"><span class="mainTitle">
会议室查询 </span>: <span class="subTitle"> </span></div>
<table width="100%" border="1" cellpadding="2" cellspacing="0"
	class="submitTable">
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室编号</td>
		<td width="83%" align="left" nowrap class="tdValue"><input
			type="text" size="20" name="id"></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室大小</td>
		<td width="83%" align="left" nowrap class="tdValue"><h:radio
			key="roomLevel" /></td>
	</tr>
	<tr align="right" valign="middle">
		<td width="17%" height="23" nowrap class="tdTitle">会议室类型</td>
		<td width="83%" align="left" nowrap class="tdValue"><h:radio
			key="roomType" /></td>
	</tr>
</table>

<div class="errors"><span id="EEE"> <h:showmsg
	key="${errCode}" defaultValue="" /> </span></div>
<div align="center"><!-- InstanceBeginEditable name="buttons" -->
<input type="button" name="doItButton" class="submitButton" value="确定"
	onclick="doIt('pageNum=1')"> <!-- InstanceEndEditable --></div>

<span id="AAAA"></span></form>
</body>
</html>