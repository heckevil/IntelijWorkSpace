<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021-06-08
  Time: 오후 2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${empty sessionScope.loginUser.profileImg}">
        <c:set var="img" value="/res/img/noprofile.jpg"/>
    </c:when>
    <c:otherwise>
        <c:set var="img" value="/img/${sessionScope.loginUser.iuser}/${sessionScope.loginUser.profileImg}"/>
    </c:otherwise>
</c:choose>
<div>
    <form action="profile" method="post" enctype="multipart/form-data" id="frm" onsubmit="return imgChk();">
        이미지변경 : <input type="file" name="profileImg" accept="image/*">
<%--  Controller > profile (MultipartFile profileImg) 이랑 이미지변경 name랑 같아야 한다 .
    혹은 Controller 쪽 @RequestParam("이미지변경 ID")를 써야함--%>
        <input type="submit" value="이미지 업로드">
    </form>
</div>
<div>
    <div><img src="${img}"></div>
    <div>PK : ${sessionScope.loginUser.iuser}</div>
    <div>ID : ${sessionScope.loginUser.uid}</div>
    <div>Name : ${sessionScope.loginUser.unm}</div>
</div>