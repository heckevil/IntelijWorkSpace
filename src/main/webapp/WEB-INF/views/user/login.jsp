<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021-06-01
  Time: 오후 4:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<h1>로그인</h1>
<div>${errMsg}</div>
<form action="login" method="post">
    <div><input type="text" name="uid" placeholder="id" value="t1"></div>
    <div><input type="password" name="upw" placeholder="password" value="t1"></div>
    <div>
        <input type="submit" value="Login">
    </div>
</form>

<div>
    <a href="join">join</a>
</div>
</body>
</html>
