<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
          <div id="search_bar" class="mt10">
<form action="method!bookslist" method="post">
       <div class="box">
          <div class="box_border">
            <div class="box_top">
            <b class="pl15">图书名</b>
            <input type="text" name="name"  value="${name }" class="input-text lh25" size="20">
              <b class="pl15">图书编号</b>
            <input type="text" name="bianhao"  value="${bianhao }" class="input-text lh25" size="20">
              <b class="pl15">根据分类查询</b>
           <select style="width:150px" name="fenlei" class="input-text lh25" >
                    <option value="">--所有--</option>
                    <c:forEach items="${list2}" var="bean2">
                    <option value="${bean2.id }" <c:if test="${fenlei==bean2.id }">selected</c:if> >${bean2.name}</option>
                    </c:forEach>
                    
                 </select>
            <input type="submit"  class="btn btn82 btn_search" value="查询">   
            </div>
          </div>
        </div>
        </form>
      </div>
        
         <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                   <tr>
                   <th width="100%" align="left">
                     <input type="button" name="button" class="btn btn82 btn_add" value="新增" onclick="location.href='method!booksadd'"/>
                  &nbsp;&nbsp; &nbsp;&nbsp;  
                       </th>  
        
                    </tr>
                  <tr>
                   <th width="100%" align="left">图书列表</th>
                    </tr>
                    </table>
                   
                    
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                   <th width="10%">图书</th>
                   <th width="10%">编号</th>
                   <th width="10%">图书名</th>
                      <th width="10%">分类</th>
                   <th width="10%">出售状态</th>
                    <th width="10%">价格</th>
                      <th width="10%">图书作者</th>
                   <th width="15%">操作</th>
                    </tr>
                    
                <c:forEach items="${list}" var="bean"> 
                <tr class="tr">
                <td><img src='<%=basePath %>  ${bean.imgpath}' width="100" height="120"/></td>
                <td>${bean.bianhao }</td>
                <td>${bean.name }</td>
                <td>${bean.fenlei.name }</td>
                <td>${bean.zt }</td>
                 <td>${bean.price }</td>
                <td>${bean.zuozhe }</td>
                 <td>  
                   <input type="button"  class="btn btn82 btn_del" value="删除"   onclick="location.href='method!booksdelete?id=${bean.id }'"/>
                   <input type="button"  class="btn btn82 btn_config" value="修改"   onclick="location.href='method!booksupdate?id=${bean.id }'"/>
                   <input type="button"  class="btn btn82 btn_config" value="详情"   onclick="location.href='method!booksupdate3?id=${bean.id }'"/>
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
  