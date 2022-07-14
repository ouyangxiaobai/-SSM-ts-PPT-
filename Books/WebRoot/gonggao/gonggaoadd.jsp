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
  <script language="javascript" type="text/javascript">
function checkform()
{
	 
	 if (document.getElementById('biaotiid').value=="")
	{
		alert("标题不能为空");
		return false;
	}
	

	 if (document.getElementById('contentid').value=="")
	{
		alert("内容不能为空");
		return false;
	}
	
	return true;
	
}
</script>
   
   <title></title>
 </head>
 <body>
  <form action="method!gonggaoadd2" method="post"  onsubmit="return checkform()">
  
  <div class="container">
    
     <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">公告</b></div>
            <div class="box_center">
             
               <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">

                 <tr>
                  <td class="td_right">标题:</td>
                  <td class="">
                  <input type="text" name="biaoti" id="biaotiid" class="input-text lh30" size="40">
                  </td>
                 </tr>
                 <tr>
                  <td class="td_right">内容:</td>
                  <td class="">
                     <textarea class="textarea" name="content" id="contentid" rows="5" cols="30"></textarea>
                  </td>
                 </tr>
                 
                 <tr>
                   <td class="td_right">&nbsp;</td>
                   <td class="">
                     <input type="submit" name="submit" class="btn btn82 btn_save2" value="保存"> 
                    <input type="reset"  class="btn btn82 btn_res" value="重置"> 
                   </td>
                 </tr>
               </table>
               
            </div>
          </div>
        </div>
     </div>
   </div> 
   </form>
 </body>
 </html>
  