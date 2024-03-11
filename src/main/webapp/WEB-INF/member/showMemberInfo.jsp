<%--
  Created by IntelliJ IDEA.
  User: 장서윤
  Date: 2024-03-11
  Time: 오후 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원정보</title>
</head>
<body>
<h1>회원정보</h1>
<p>아이디 ${memberVO.id}</p>
<p>이름 ${memberVO.name}</p>
<p>이메일 ${memberVO.email}</p>
<a href="/member/mem.do">회원목록</a>
</body>
</html>
