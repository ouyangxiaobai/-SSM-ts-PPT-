<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="zh-CN">
<head>
<script language="javascript" type="text/javascript"> 

		
function registershow(){
		var now = new Date(); 
		var t = now.getTime()+''; 
		window.open("register.jsp?t="+t, null, 
		'dialogWidth:500px;dialogHeight:600px;help:no;unadorned:no;resizable:no;status:no;scroll:no');
	}
	
</script>

    <link rel="stylesheet" href="css/login.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
	<title>后台登陆</title>
</head>
<body>
<form method="post"  action="method!login">
	<div id="login_top">
		<div id="">
			
		</div>
		<div id="back">
			
		</div>
	</div>
	<div id="login_center">
	
	
		<div id="login_area">
		<span style="font-size: 50px;font-weight: bold;color: white; margin-left:39%;">
   智能化图书查询系统
    </span>
			<div id="login_form">
				
					<div id="login_tip">
						用户登录&nbsp;&nbsp;
					</div>
					<div><input type="text" name="username" class="username"></div>
					<div><input type="password" name="password" class="pwd"></div>
					<div id="btn_area">
						<input type="submit" name="submit" id="sub_btn" value="登&nbsp;&nbsp;录">&nbsp;&nbsp;
						<input type="button"   id="sub_btn" onclick="javascript:window.open('register.jsp','','width=660,height=500,left=750, top=200,toolbar=no, status=no, menubar=no, resizable=yes, scrollbars=yes');return false;"" value="用户注册">
					</div>
				
			</div>
		</div>
	</div>
	<div id="login_bottom">
		
	</div>
	</form>
</body>
</html>