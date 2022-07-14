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
<div id="table" class="mt10">
        <div class="box span10 oh">

     <div id="table" class="mt10">
        <div class="box span10 oh">
         
        
         <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                   <tr>
                   <th width="100%" align="left">
                     <input type="button" name="button" class="btn btn82 btn_add" value="新增" onclick="location.href='method!fenleiadd'"/>
                  &nbsp;&nbsp; &nbsp;&nbsp;  
                       </th>  
        
                    </tr>
                  <tr>
                   <th width="100%" align="left">图书分类列表</th>
                    </tr>
                    </table>
                   
                    
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                   <th width="20%">图书分类名</th>
                   <th width="20%">添加时间</th>
                   <th width="30%">操作</th>
                    </tr>
                    
                <c:forEach items="${list}" var="bean"> 
                <tr class="tr">
                <td>${bean.name }</td>
                <td>${bean.createtime }</td>
                   <td>  
                   <form name="form1" action="">
                   <input type="button"  class="btn btn82 btn_del" value="删除"   onclick="location.href='method!fenleidelete?id=${bean.id }'"/>
                   <input type="button"  class="btn btn82 btn_config" value="修改"   onclick="location.href='method!fenleiupdate?id=${bean.id }'"/>
                   </form>
                   </td>
                 </tr>
                 </c:forEach> 
              </table>
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                   <th width="50%" align="left"> ${pagerinfo }</th>
                    </tr>
              </table>
        </div>
     </div>
   
   </div>
   </div>
 </body>
 </html>
  