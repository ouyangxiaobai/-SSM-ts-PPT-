<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.*"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();

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
                   <th width="100%" align="left">逾期图书列表</th>
                    </tr>
                    </table>
                   
                    
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                   <th width="10%">图书图片</th>
                   <th width="10%">图书编号</th>
                   <th width="10%">图书名</th>
                   <th width="10%">图书价格</th>
                    <th width="10%">借书时间</th>
                     <th width="10%">到期时间</th>
                      <th width="10%">借书人</th>
                        <th width="10%">图书状态</th>
                         <th width="10%">还书时间</th>
                          <th width="10%">逾期天数</th>
                           <th width="10%">逾期费用</th> 
                
                    </tr>
                    
                <c:forEach items="${list}" var="bean"> 
                <tr class="tr">
                <td><img src='<%=basePath %>/Books/images/${bean.books.imgpath}' width="100" height="120"/></td>
                <td>${bean.books.bianhao }</td>
                <td>${bean.books.name }</td>
                <td>${bean.books.price }</td>
                <td>
                ${fn:substring(bean.b_createtime, 0, 10)}
                </td>
                 <td>
                  ${fn:substring(bean.re_createtime, 0, 10)}
                 </td>
                   <td>${bean.user.username }</td>
                     <td>
                     <c:if test="${bean.books.status=='未借'}">已归还</c:if>
                     <span style="color: blue"><c:if test="${bean.books.status=='已借'}">已借</c:if></span>
                     </td>
                     <td>${bean.back_createtime }</td>
                     <td>${bean.retime }</td>
                     <td>
                      <span style="color: red"><c:if test="${bean.num<0}">应付逾期费：${-bean.num }</c:if></span>
                    <c:if test="${bean.num==0}">${bean.num }</c:if>
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
  