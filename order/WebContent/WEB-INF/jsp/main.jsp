<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎使用会议管理系统</title>

</head>
<frameset rows="120, *, 25" framespacing="0">
    <frame src="<%=request.getContextPath() %>/frame/top.jsp" frameborder="0" noresize="noresize" scrolling="no" marginheight="0" marginwidth="0" />
    <frameset cols="15%, 85%" framespacing="0">
        <frame src="<%=request.getContextPath() %>/frame/left.jsp"  frameborder="0" noresize="noresize" scrolling="no" marginheight="0" marginwidth="0"/>
        <frame src="meetingAddPre.do"  frameborder="0" noresize="noresize" scrolling="yes" marginheight="0" marginwidth="0" name="mainFrame"/>
    </frameset>
    <frame src="<%=request.getContextPath() %>/frame/foot.jsp"  frameborder="0" noresize="noresize" scrolling="no" marginheight="0" marginwidth="0"/>
</frameset>

</html>