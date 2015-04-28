<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
ul,li{
	text-decoration: none;
}

a:LINK{
	text-decoration: none;
}

li{
	list-style:none;
	line-height: 18px;
	margin: 10px 0px 10px 0px; 
}

.menu_open{
	list-style-image: url("./../img/menu_open.jpg");
	padding-left: 0px;
}

.menu_close{
	list-style-image: url("./../img/close.jpg");
	
}

.menu_open li{
	list-style-image:  url("./../img/menu_open2.jpg");
}

.menu_open2{
	list-style-image:  url("./../img/menu_open2.jpg");
}

.menu_close li{
	list-style-image: none;
}

.menu_on{
	display: block;
	margin-left: 5px;
}

.menu_off{
	display: none;
}

</style>
<script type="text/javascript">
	function menuop(id, parent){
		var obj = document.getElementById(id);
		var menuclass = obj.className;
		if(menuclass=="menu_off"){
			obj.className = "menu_on";
			parent.className="menu_open";
		}else{
			obj.className = "menu_off";
			parent.className="menu_close";
		}
	}	

	function doAction(action) {
        window.parent.mainFrame.location.href=action;
	}
</script>
</head>
<body bgcolor="#ccfccc">

<ul>
	<li class="menu_close"><a href="#" onclick="menuop('menu1', this.parentNode);"><strong>会议室管理</strong></a>
	<ul id="menu1" class="menu_off">
		<li><a href="#" onclick="doAction('<%=request.getContextPath() %>/meetingRoomQryPre.do')">会议室查询</a></li>
		<li><a href="#" onclick="doAction('<%=request.getContextPath() %>/meetingRoomAddPre.do')">会议室新增</a></li>
	</ul>
	</li>

	<li class="menu_close"><a href="#" onclick="menuop('menu2', this.parentNode);">会议管理</a>
	<ul id="menu2" class="menu_off">
		<li><a href="#" onclick="doAction('<%=request.getContextPath() %>/meetingAddPre.do')">会议预约</a></li>
		<li><a href="#" onclick="doAction('<%=request.getContextPath() %>/meetingQryPre.do')">会议查询</a></li>
	</ul>
	</li>
	
	<li class="menu_open2"><a href="#" onclick="doAction('<%=request.getContextPath() %>/prePwdModify.do')">密码修改</a></li>
</ul>


</body>
</html>