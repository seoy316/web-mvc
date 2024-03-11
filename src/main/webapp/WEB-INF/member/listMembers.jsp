<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원 목록</title>
</head>
<body>
<h2>회원 목록</h2>
<table border="1">
    <tr>
        <th>아이디</th>
        <th>비밀번호</th>
        <th>이름</th>
        <th>이메일</th>
        <th>가입일</th>
        <th>수정</th>
        <th>삭제</th>
    </tr>
    <c:forEach var="member" items="${members}">
        <tr>
            <td>${member.mid}</td>
            <td>${member.mpw}</td>
            <td>${member.mname}</td>
            <td>${member.email}</td>
            <td>${member.created}</td>
            <td>
                <form action="/member/modMemberForm.do" method="get">
                    <input type="hidden" name="id" value="${member.id}"/>
                    <button type="submit">수정</button>
                </form>
            </td>
            <td>
                <form action="/member/delMember.do" method="post">
                    <input type="hidden" name="id" value="${member.id}"/>
                    <button type="submit">삭제</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/member/addMember.do">회원 가입</a>
</body>
</html>