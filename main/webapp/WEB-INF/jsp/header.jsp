<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>

html,body {
	margin			: 0;
	paddhing		: 0;
	width			: 100%;
	height			: 100%;
}
.header {
	display			: flex;
	top				: 0px;
	left			: 0px;
	justify-content	: space-between;
	border			: 1px solid #666;
	width			: 100%;
	height			: 60px;
	position		: fixed;
	background		: white;
}

.logout button {
	margin			: 1em 10px 0px 0;
	font			: 1em sans-serif;
	width			: 200px;
	height			: 2em;
	border-radius	: 5px;
}

img {
	margin			: 0 0 0 10px;
	height			: 50px;
}
</style>

<div class="head">
<form action="/nchannel/LogoutServlet" method="post">
	<ul class="header">
		<li>
			<img class="title" src="img/title.png">
		</li>
		<li>
			<div class="logout"><button class="logout" type="submit">ログアウト</button></div>
		</li>
	</ul>
</form>
</div>
