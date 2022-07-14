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
<form action="method!borrowlist" method="post">
       <div class="box">
          <div class="box_border">
            <div class="box_top">
             <b class="pl15">图书编号</b>
            <input type="text" name="bianhao"  value="${bianhao }" class="input-text lh25" size="20">
            <b class="pl15">图书名</b>
            <input type="text" name="name"  value="${name }" class="input-text lh25" size="20">  
             <b class="pl15">出版社</b>
            <input type="text" name="cbs"  value="${cbs }" class="input-text lh25" size="20">
             <b class="pl15">图书作者</b>
            <input type="text" name="zuozhe"  value="${zuozhe }" class="input-text lh25" size="20">
              <b class="pl15">根据分类查询</b>
           <select style="width:100px" name="fenlei" class="input-text lh30" >
                    <option value="" >所有</option>
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
                   <th width="100%" align="left">图书列表</th>
                    </tr>
                    </table>
                   
                    
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                   <th width="10%">图书图片</th>
                   <th width="10%">图书编号</th>
                   <th width="10%">图书名</th>
                    <th width="10%">所属分类</th>
                   <th width="10%">图书价格</th>
                    <th width="10%">出版时间</th>
                     <th width="10%">出版社</th>
                      <th width="10%">图书作者</th>
                       
                        <th width="10%">图书状态</th>
                   <th width="15%">操作</th>
                    </tr>
                    
                <c:forEach items="${list}" var="bean"> 
                <tr class="tr">
                <td><img src='<%=basePath %>/Books/images/${bean.imgpath}' width="100" height="120"/></td>
                <td>${bean.bianhao }</td>
                <td>${bean.name }</td>
                  <td>${bean.fenlei.name }</td>
                <td>${bean.price }</td>
                <td>${bean.cb_time }</td>
                 <td>${bean.cbs }</td>
                   <td>${bean.zuozhe }</td>
                   
                     
                       <td>
                        <span style="color: red"><c:if test="${bean.status=='已借'}">已借</c:if></span>
                        <c:if test="${bean.status=='未借'}">未借</c:if>
                       </td>
                   <td>  
                  <input type="button"  class="btn btn82 btn_config" value="详情"   onclick="location.href='method!booksupdate3?id=${bean.id }'"/>
                  <c:if test="${user.role==2}">
                  <c:if test="${bean.status=='未借'}">
                    <input type="button"  class="btn btn82 btn_del" value="借书"   onclick="location.href='method!borrowadd?id=${bean.id }'"/>
                  </c:if>
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
              
               <c:if test="${user.role==2}"> 
              <table>
                <tr>
                   <th width="100%" align="left">图书推荐列表</th>
                    </tr>
                    </table>
                   
                    
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                   <th width="10%">图书图片</th>
                   <th width="10%">图书编号</th>
                   <th width="10%">图书名</th>
                     <th width="10%">所属分类</th>
                   <th width="10%">图书价格</th>
                    <th width="10%">出版时间</th>
                     <th width="10%">出版社</th>
                      <th width="10%">图书作者</th>
                      
                        <th width="10%">图书状态</th>
                   <th width="15%">操作</th>
                    </tr>
                    
                <c:forEach items="${bookslist}" var="bean"> 
                <tr class="tr">
                <td><img src='<%=basePath %>/Books/images/${bean.imgpath}' width="100" height="120"/></td>
                <td>${bean.bianhao }</td>
                <td>${bean.name }</td>
                 <td>${bean.fenlei.name }</td>
                <td>${bean.price }</td>
                <td>${bean.cb_time }</td>
                 <td>${bean.cbs }</td>
                   <td>${bean.zuozhe }</td>
                    
                     
                       <td>
                        <span style="color: red"><c:if test="${bean.status=='已借'}">已借</c:if></span>
                        <c:if test="${bean.status=='未借'}">未借</c:if>
                       </td>
                   <td>  
                  <input type="button"  class="btn btn82 btn_config" value="详情"   onclick="location.href='method!booksupdate3?id=${bean.id }'"/>
                  <c:if test="${user.role==2}">
                  <c:if test="${bean.status=='未借'}">
                    <input type="button"  class="btn btn82 btn_del" value="借书"   onclick="location.href='method!borrowadd?id=${bean.id }'"/>
                  </c:if>
                  </c:if>
                   </td>
                 </tr>
                 </c:forEach> 
              </table>
              </c:if>
              
              
        </div>
     </div>
   
   </div>
   </div>
 </body>
 </html>
  