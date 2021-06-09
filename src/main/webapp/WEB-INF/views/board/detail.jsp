<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021-06-04
  Time: 오전 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div><a href="#" onclick="goBack();">돌아가기</a></div>
<h1>${requestScope.data.title}</h1>
<div>글번호 : ${requestScope.data.iboard}</div>
<div>작성자 : <c:out value="${requestScope.data.writerNm}"/> | 작성일 : ${requestScope.data.regdt}</div>
<div><c:out value="${requestScope.data.ctnt}"/></div>
<%-- <c:out /> 하나 안하나 결과 값은 같다. 보안상 감싸는 이유가있다. 자바스크립트 공격 방어--%>
<c:if test="${not empty sessionScope.loginUser}">
<div>
    <form id="cmtFrm" onsubmit="return false;">
        <input type="text" id="cmt" placeholder="댓글" value="">
        <input type="button" value="댓글달기" onclick="regCmt();">
    </form>
</div>
</c:if>
<div id="cmtList" data-login-user-pk="${sessionScope.loginUser.iuser}" data-iboard="${param.iboard}"></div>

<div id="modal" class="displayNone">
    <div class="modal_content">
        <form id="cmtModFrm" action="#">
            <input type="hidden" id="icmt">
            <input type="text" id="modCmt">
        </form>
        <input type="button" value="댓글 수정" onclick="modAjax();">
        <input type="button" value="취소" onclick="closeModModal();">
    </div>
</div>
