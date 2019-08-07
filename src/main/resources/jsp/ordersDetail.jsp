<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath(); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/tools.js"></script>
</head>
<body>
   <%@include file="/header.jsp"%>

   <div class="main" style="width: 900px;">
        
        <div class="message">
            <p>订单详情</p>
            <span>${message }</span>
        </div>
   
         <table>
            <thead>
                <tr>
                  <td>书名</td>
                  <td>单价</td>
                  <td>数量</td>
                  <td>总价</td>
               </tr>
            </thead>
            
            <tbody>
               <tr>
                   <td>三体全集（全3册）</td> <td>¥168.00</td> <td>2</td> <td>¥336.00</td>
               </tr>
               <tr>
                   <td>时间简史</td> <td>¥30.00</td> <td>1</td> <td>¥30.00</td>
               </tr>
               <tr>
                   <td>了不起的盖茨比</td> <td>¥19.50</td> <td>1</td> <td>¥19.50</td>
               </tr>
            </tbody>
            
            <tfoot>
               <tr>
                    <td></td><td></td><td>合计：4</td><td>合计：¥385.50</td><td></td>
               </tr>
            </tfoot>
         </table>
        
        <div style="margin-top:50px">
                  <span style="font-weight: bold;">收货地址信息：</span> 收货地址：蛋蛋，18000000001，北京 西城区 xxx
        </div>
   </div>

   <%@include file="/footer.jsp"%>
</body>
</html>