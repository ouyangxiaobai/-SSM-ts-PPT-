<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();

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

<script language="javascript" type="text/javascript" src="js/jquery.min.js"></script>

</head>


	<body>
<form action="method!booksupdate2" method="post" enctype="multipart/form-data" >
<input type="hidden" name="id" value="${bean.id }"/>
    <div id="regdiv">
         <div id="forms" class="mt10">
        <div class="box">
          <div class="box_border">
            <div class="box_top"><b class="pl15">图书修改</b></div>
            <div class="box_center">
              
               <table class="form_table pt15 pb15" width="100%" border="0" cellpadding="0" cellspacing="0">
                
                 <tr>
                  <td class="td_right">图书分类:</td>
                  <td class="">
                  <select style="width:150px"  name="fenlei" id="fenleiid" class="input-text lh30" > 
                    <c:forEach items="${list}" var="bean2">
                    <option value="${bean2.id }" <c:if test="${bean2.id==bean.fenlei.id }">selected</c:if> >${bean2.name}</option>
                    </c:forEach>
                  </select>
                  </td>
                 </tr>

                 <tr>
                  <td class="td_right">图书名:</td>
                  <td class="">
                  <input type="text" name="name" value="${bean.name }" id="nameid" class="input-text lh30" size="40"/>
                  </td>
                 </tr>
                 
                 
                 <tr>
                  <td class="td_right">图书描述:</td>
                  <td class="">
                     <textarea class="textarea" name="miaoshu" id="miaoshuid" rows="5" cols="30">${bean.miaoshu }</textarea>
                  </td>
                 </tr>
                 
                 <tr>
                  <td class="td_right">图书价格:</td>
                  <td class="">
                  <input type="text" name="price" value="${bean.price }" id="priceid" class="input-text lh30" size="40"/>
                  </td>
                 </tr>
                 
                 <tr>
                  <td class="td_right">出版时间:</td>
                  <td class="">
                   <input type="text" name="cb_time" value="${bean.cb_time }" id="cb_timeid" class="input-text lh30"  onfocus="WdatePicker({isShowWeek:true})"/>
                  </td>
                 </tr>
                 
                 <tr>
                  <td class="td_right">出版社:</td>
                  <td class="">
                  <input type="text" name="cbs" value="${bean.cbs }" id="cbsid" class="input-text lh30" size="40"/>
                  </td>
                 </tr>
                 
                 <tr>
                  <td class="td_right">图书作者:</td>
                  <td class="">
                  <input type="text" name="zuozhe" id="zuozheid" value="${bean.zuozhe }" class="input-text lh30" size="40"/>
                  </td>
                 </tr>
                 
                   <tr>
                  <td class="td_right">图书图片:</td>
                  <td class="">
                   <img src='<%=basePath %>/Books/images/${bean.imgpath}' width="400" height="320"/>
                  </td>
                 </tr>
                 
                  <tr>
                  <td class="td_right">出售状态:</td>
                  <td class="">
                  <select style="width:150px"  name="zt" id="ztid" class="input-text lh30" > 
                      <option value="可购买" <c:if test="${bean.zt=='可购买' }">selected</c:if>>可购买</option>
                      <option value="已售罄" <c:if test="${bean.zt=='已售罄' }">selected</c:if>>已售罄</option>
                  </select>
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
