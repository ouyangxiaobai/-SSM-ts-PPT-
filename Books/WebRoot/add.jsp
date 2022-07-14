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
   <meta charset="UTF-8">
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
   <title>添加修改框</title>
 </head>
 <body>
  <div class="container">
    
     <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">表单</b></div>
            <div class="box_center">
              <form action="" class="jqtransform">
               <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
                 <tr>
                  <td class="td_right">输入框：</td>
                  <td class=""> 
                    <input type="text" name="name" class="input-text lh30" size="40">
                  </td>
                  <td class="td_right">输入框：</td><td><input type="text" name="name" class="input-text lh30" size="40"></td>
                </tr>
                <tr >
                  <td class="td_right">下拉框：</td>
                  <td class="">
 
                    <span class="fl">
                      <div class="select_border"> 
                        <div class="select_containers "> 
                        <select name="" class="select"> 
                        <option>北京</option> 
                        <option>天津</option> 
                        <option>上海</option> 
                        <option>重庆</option> 
                        </select> 
                        </div> 
                      </div> 
                    </span>
                  </td>
                 </tr>
                 <tr>
                  <td class="td_right">文本框：</td>
                  <td class="">
                    <textarea name="" id="" cols="30" rows="10" class="textarea"></textarea>
                  </td>
                 </tr>
                 <tr>
                  <td class="td_right">单选：</td>
                  <td class="">
                    <input type="radio" name="status"> 可用
                    <input type="radio" name="status"> 不可用
                  </td>
                 </tr>
                 <tr>
                  <td class="td_right">多选：</td>
                  <td class="">
                    <input type="checkbox" name="check" > 1
                    <input type="checkbox" name="check" > 2
                    <input type="checkbox" name="check" > 3
                  </td>
                </tr>
                <tr>
                  <td class="td_right">文件：</td>
                  <td class=""><input type="file" name="file" class="input-text lh30" size="10"></td>
                 </tr>
                 <tr>
                   <td class="td_right">&nbsp;</td>
                   <td class="">
                     <input type="button" name="button" class="btn btn82 btn_save2" value="保存"> 
                    <input type="button" name="button" class="btn btn82 btn_res" value="重置"> 
                   </td>
                 </tr>
               </table>
               </form>
            </div>
          </div>
        </div>
     </div>
   </div> 
 </body>
 </html>
  