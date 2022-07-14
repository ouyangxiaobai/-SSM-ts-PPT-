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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		
		
	<style type="text/css">
#regdiv {
	position: absolute;
	width: 600px;
	height: 700px;
	background-image: url(img/b2c_04.jpg);
}
</style>
   <link rel="stylesheet" href="css/common.css"/>
   <link rel="stylesheet" href="css/main.css"/>
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

<script language="javascript" type="text/javascript" src="js/jquery.min.js"></script>

</head>


	<body>
<form action="method!userupdate2" method="post"  >
<input type="hidden" name="id" value="${bean.id }"/>
    <div id="regdiv">
         <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">修改用户信息</b></div>
            <div class="box_center">
              
               <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
                 
                
                
                <tr>
                  <td class="td_right">姓名:</td>
                  <td><input type="text" name="truename"  value="${bean.truename }" class="input-text lh30" size="40"/></td>
                </tr>
                <tr>
                  <td class="td_right">手机号:</td>
                  <td><input type="text" name="telephone"  value="${bean.telephone }" class="input-text lh30" size="40"/></td>
                </tr>
                <tr>
                  <td class="td_right">籍贯:</td>
                  <td><input type="text" name="jiguan"  value="${bean.jiguan }" class="input-text lh30" size="40"/></td>
                </tr>
                <tr>
                  <td class="td_right">家庭地址:</td>
                  <td><input type="text" name="address" value="${bean.address }" class="input-text lh30" size="40"/></td>
                </tr>
                <tr>
                  <td class="td_right">年龄:</td>
                  <td><input type="text" name="age"  value="${bean.age }" class="input-text lh30" size="40"/></td>
                </tr>
               
                <tr>
                  <td class="td_right">性别:</td>
                  <td class="">
 
                    <span class="fl">
                      <div class="select_border"> 
                        <div class="select_containers "> 
                        <select name="xingbie" class="select">
         <option value="男" <c:if test="${bean.xingbie=='男' }">selected</c:if> >男</option>
         <option value="女" <c:if test="${bean.xingbie=='女' }">selected</c:if>>女</option>
         </select>
                        </div> 
                      </div> 
                    </span>
                  </td>
                 </tr>
                 
                 <tr>
                   <td class="td_right">&nbsp;</td>
                   <td class="">
                    <input type="submit" class="btn btn82 btn_save2" value="保存" onclick="checkregisterform()"/>
                    <input type="button" class="btn btn82 btn_nochecked" value="取消" onclick="window.history.go(-1);"/>
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
