<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원 정보 수정</title>
</head>
<body>
<h2>회원 정보 수정</h2>
<form action="/member/modMember.do" method="post">
    <input type="hidden" name="id" value="${memberVO.id}"/>
    비밀번호 <input type="password" name="pwd" value="${memberVO.pwd}" required/><br/>
    이름 <input type="text" name="mname" value="${memberVO.name}" required/><br/>
    이메일 <input type="email" name="email" value="${memberVO.email}" required/><br/>

    <button type="submit">입력 완료</button>
</form>
</body>
</html>
