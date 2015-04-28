<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

	function mytest() {
		getXmlHttp();
		var url = "<%=request.getContextPath()%>/mytest.do";
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
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload="javascript:window.setInterval('mytest()',5000);">
<div style="text-align: center">copyright<span id='AAAA'></span></div>

</body>
</html>