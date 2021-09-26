<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>ｎちゃんねる　スレッド一覧画面</title>
<link rel="stylesheet" href="css/threadList.css"> 

</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="fix_body">
	
	<div class="contents">
		<ul>
			<li>
				<h2>スレッド一覧</h2>
			</li>
			<c:forEach var="thList" items="${thList}">
				<c:set value="${ no +1 }" var="no" />
				<li class="row">
					<c:out value="${ no }" />
					<a href="/nchannel/ThreadListServlet?threadid=${thList.thread_id}">
					<c:out value="${thList.title}" />
					（<c:out value="${thList.mcount}" />）</a>
				</li>
			</c:forEach>
		</ul>
	</div>

</div>

<ul>

	<div class="new_thread">
	
		<form action="/nchannel/ThreadListServlet" method="post">
			<li class="thtitle">
				<input type="text" name="threadtitle" required placeholder="スレッドタイトル">
				 
			</li>
			<li class="name">
				<input class="name" type="text" name="name" readonly value=<c:out value="${user.name} " /> >
			</li>
			<li class="comment">
				<textarea name="message" maxlength="100" required placeholder="コメント内容"></textarea>
			</li>
			<li class="new_thread_button">
				<div class="new"><button type="submit">新規スレッド作成</button></div>
			</li>
	
		</form>
	
	</div>
</ul>
		
</body>
</html>