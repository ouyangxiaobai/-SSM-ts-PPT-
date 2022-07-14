<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/uploadfile/";
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
    <script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
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
	 
	 if (document.getElementById('nameid').value=="")
	{
		alert("图书名不能为空");
		return false;
	}
	

	
	
	if (document.getElementById('miaoshuid').value=="")
	{
		alert("图书描述不能为空");
		return false;
	}
	
	if (document.getElementById('priceid').value=="")
	{
		alert("图书价格不能为空");
		return false;
	}
	
	if (document.getElementById('cb_timeid').value=="")
	{
		alert("出版时间不能为空");
		return false;
	}
	
	if (document.getElementById('fenleiid').value=="")
	{
		alert("分类不能为空");
		return false;
	}
	
	var valid=/^\d*$/;
	
	if(!valid.test(document.getElementById('priceid').value)){
		alert("图书价格必须是数字");
		return false;
	}
	
	
	
	return true;
	
}
</script>
   
   <title></title>
 </head>
 <body>
  <form action="method!booksadd2" method="post"  onsubmit="return checkform()" enctype="multipart/form-data">
  
  <div class="container">
    
     <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">图书添加</b></div>
            <div class="box_center">
             
               <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                  <td class="td_right">图书分类:</td>
                  <td class="">
                  <select style="width:150px"  name="fenlei" id="fenleiid" class="input-text lh30" > 
                    <option value="">--请选择--</option>
                    <c:forEach items="${list}" var="bean2">
                    <option value="${bean2.id }">${bean2.name}</option>
                    </c:forEach>
                  </select>
                  </td>
                 </tr>

                 <tr>
                  <td class="td_right">图书名:</td>
                  <td class="">
                  <input type="text" name="name" id="nameid" class="input-text lh30" size="40">
                  </td>
                 </tr>
                 
                 
                 <tr>
                  <td class="td_right">图书描述:</td>
                  <td class="">
                     <textarea class="textarea" name="miaoshu" id="miaoshuid" rows="5" cols="30"></textarea>
                  </td>
                 </tr>
                 
                 <tr>
                  <td class="td_right">图书价格:</td>
                  <td class="">
                  <input type="text" name="price" id="priceid" class="input-text lh30" size="40">
                  </td>
                 </tr>
                 
                 <tr>
                  <td class="td_right">出版时间:</td>
                  <td class="">
                   <input type="text" name="cb_time" id="cb_timeid" class="input-text lh30" onfocus="WdatePicker({isShowWeek:true})"><img onclick="WdatePicker({el:'d12'})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
                  
                  </td>
                 </tr>
                 
                 <tr>
                  <td class="td_right">出版社:</td>
                  <td class="">
                  <input type="text" name="cbs" id="cbsid" class="input-text lh30" size="40">
                  </td>
                 </tr>
                 
                 <tr>
                  <td class="td_right">图书作者:</td>
                  <td class="">
                  <input type="text" name="zuozhe" id="zuozheid" class="input-text lh30" size="40">
                  </td>
                 </tr>
                 
                 <tr>
                  <td class="td_right">图片:</td>
                  <td class="">
                  <input type="file" name="uploadfile" class="input-text lh30" size="40" id="uploadfileid"/>
                  </td>
                 </tr>
                 
                 
                  <tr>
                  <td class="td_right">出售状态:</td>
                  <td class="">
                  <select style="width:150px"  name="zt" id="ztid" class="input-text lh30" > 
                      <option value="可购买">可购买</option>
                      <option value="已售罄">已售罄</option>
                  </select>
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
  