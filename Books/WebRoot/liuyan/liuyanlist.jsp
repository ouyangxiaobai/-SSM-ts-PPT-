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
                   <th width="100%" align="left">留言信息列表</th>
                    </tr>
                    </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
              
                <tr>
                  
                        	<th>留言内容</th>
							<th>留言用户</th>
							<th>留言时间</th>
							<th>回复</th>
							<th>回复时间</th>
							<th>操作</th>
                    </tr>
                    
                <c:forEach items="${list}" var="bean">
                <tr class="tr">
                 
                                <td>${bean.content }</td>
								<td>${bean.user.username }</td>
								<td>${bean.createtime }</td>
								<td>${bean.recontent }</td>
								<td>${bean.recreatetime }</td>
                   <td>  
                   <input type="button"  class="btn btn82 btn_del" value="删除"  onclick="javascript:if(confirm('确定要删除此信息吗？')){location.href='method!liuyandelete?id=${bean.id }';return true;}return false;"  />
                  <c:if test="${bean.recontent==null}">
                    <input type="button"  class="btn btn82 btn_config" value="回复" onclick="location.href='method!liuyanupdate?id=${bean.id }'"/>
                 </c:if>
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
   
 </body>
 </html>
  