<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Member</title>
</head>
<body>
<h2>Add Member</h2>
<form action="/member/addMember.do" method="post">
    아이디 <input type="text" name="id" required /><br />
    비밀번호 <input type="password" name="pwd" required /><br />
    이름 <input type="text" name="name" required /><br />
    이메일 <input type="email" name="email" required /><br />
    <button type="submit">가입하기</button>
    <button type="reset">다시입력</button>
</form>
</body>
</html>