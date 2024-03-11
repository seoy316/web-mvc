<%--
  Created by IntelliJ IDEA.
  User: 장서윤
  Date: 2024-03-11
  Time: 오후 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
    <h2>로그인</h2>
    <form action="/login" method="post">
        아이디 <input type="text" name="mid">
        비밀번호 <input type="password" name="mpw">
        <input type="submit" value="로그인">
    </form>
</body>
</html>
