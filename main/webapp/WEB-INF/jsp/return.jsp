<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.box {
 	display			: flex;
 	justify-content	: space-around;
 	width			: 100%;
 	margin			: 1em auto;
 	border			: 1px solid #666;
}
.return_button {
	margin			: 0.5em 0 0.5em 0;
	font			: 1em sans-serif;
	width			: 200px;
	height			: 2em;
	border-radius	: 5px;
}
</style>

<div class="box">
	
	<form action="/nchannel/ReturnServlet" method="get">
		<input type="hidden" name="kind" value="1">
		<button class="return_button" type="submit">新着メッセージの表示</button>
	</form>

	<form action="/nchannel/ReturnServlet" method="get">
		<input type="hidden" name="kind" value="2">
		<button class="return_button" type="submit">全部のメッセージの表示</button>
	</form>

	<form action="/nchannel/ReturnServlet" method="get">
		<input type="hidden" name="kind" value="3">
		<button class="return_button" type="submit">掲示板に戻る</button>
	</form>
	
</div>