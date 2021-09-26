<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ｎちゃんねる　ログイン画面</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>

<form action="/nchannel/LoginServlet" method="post">

	<ul>
	
		<li>
			<img src="img/title.png" style="height:70px;">
		</li>

		<li>
			<c:choose>
				<c:when test="${kind == 1}">
					<div style="color:red">
						${infoMsg}
					</div>
				</c:when>
				<c:when test="${kind == 2}">
					${infoMsg}
				</c:when>
			</c:choose>
		</li>
		
		<li>
			<h2>ログイン</h2>
		</li>
		
		<li>
			<input type="text" name="user"  value="${user.user_id}"
				placeholder="ユーザーＩＤ" maxlength="40" required>
		</li>
		
		<li>
			<input type="password" name="pass" 
				placeholder="パスワード" maxlength="20" required>
		</li>
		
		<li>
			<div class="button"><button type="submit">ログイン</button></div>
		</li>
		
		<li>
			<a href="/nchannel/UserAddServlet">ユーザー登録はこちらから</a>
		</li>

	</ul>
	
</form>

</body>
</html>