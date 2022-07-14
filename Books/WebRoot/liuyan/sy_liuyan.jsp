<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if((model.User)session.getAttribute("user")==null){
	response.sendRedirect("login.jsp");
	return;
}
%>
 <!doctype html>
 <html lang="zh-CN">
 <head>
   <link rel="stylesheet" href="css/common.css">
   <link rel="stylesheet" href="css/main.css">
   <script type="text/javascript" src="js/jquery.min.js"></script>
   <script type="text/javascript" src="js/colResizable-1.3.min.js"></script>
   <script type="text/javascript" src="js/common.js"></script>
   
   <script type="text/javascript">
      $(function(){  
        $(".list_table").colResizable({
          liveDrag:true,
          gripInnerHtml:"<div class='grip'></div>", 
          draggingClass:"dragging", 
          minWidth:30
        }); 
        
      }); 
   </script>
   
   <title>Document</title>
 </head>
 <body >
  <div class="container">
     <div id="table" class="mt10">
        <div class="box span10 oh">
         <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                  <tr>
                   <th width="100%" align="left">用户留言</th>
                    </tr>
                    </table>
             <c:forEach items="${list}" var="bean">
         <table width="100%" border="1"  cellpadding="0" cellspacing="0" style="background-color: #b9d8f3;">
         <tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
         <td width="30%">
     用户：${bean.user.username } <p/>留言时间： ${fn:substring(bean.createtime,0, 16)}</td><td width="70%">${bean.content }
         </td>
         </tr>
         <tr style="text-align: center; ">
         <c:if test="${bean.recontent!=null}">
        <td width="30%"> 
         管理员： <p/>回复时间： ${fn:substring(bean.recreatetime,0, 16)}</td><td width="70%">${bean.recontent }
         </td>
        </c:if> 
         </tr>
         </table>
         <br/>
       </c:forEach>
        <div>${pagerinfo }</div>
              
             <form action="method!liuyanadd" method="post"  onsubmit="return checkform()">
     <p ><textarea align="left" style="width:80%;" cols="20" rows="5"  name="content" id="contentid" ></textarea></p>
     <div align="left"><span class="STYLE10">
     <input style="width:20%;height:30px;" type="submit" value="消息发送" align="center"/>
     </span></div>
     </form>
        </div>
     </div>
    
   </div> 
   
 </body>
 <script language="javascript" type="text/javascript">
function checkform()
{
	 if (document.getElementById('contentid').value=="")
	{
		alert("发送消息不能为空");
		return false;
	}

	return true;
	
}
</script>
 </html>
  