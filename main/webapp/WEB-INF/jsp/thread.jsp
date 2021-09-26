<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ｎちゃんねる　スレッドメッセージ画面</title>
<link rel="stylesheet" href="css/thread.css">

</head>

<body>
 
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="fix_body">

	<jsp:include page="/WEB-INF/jsp/return.jsp" />

	<ul>
	
		<div class="message_list">
		
			<li class="title">
				<h2><c:out value="${title}" /></h2>
			</li>
		
			<c:forEach var="ThreadMessage" items="${thMsgList}">
			
				<c:set value="${ no +1 }" var="no" />
				
				<div class="message">
				
					<li class="name_time">
						<c:out value="${ no }" />
						<c:out value="${ThreadMessage.name}" />　
						<c:out value="${ThreadMessage.create_date}" /><br>
					</li>
					<li class="line">
						<c:out value="${ThreadMessage.message}" escapeXml="false" />
					</li>
		
				</div>
				
			</c:forEach>
		
		</div>
	
	</ul>
	
</div>
	
<ul>

	<div class="new_message">

		<form action="/nchannel/ThreadServlet" method="post">
			<li class="username">
				<input id="name" type="text" name="name" readonly value=<c:out value="${user.name}" />>
			</li>
			<li class="comment">
				<textarea name="message" maxlength="100" placeholder="コメント内容"></textarea>
			</li>
			<li class="post_button">
				<div class="button"><button type="submit">書き込む</button></div>
			</li>
		</form>

	</div>

</ul>


</body>
</html>