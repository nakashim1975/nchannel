<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ｎちゃんねる　ユーザー登録画面</title>
<link rel="stylesheet" href="css/userAdd.css">
</head>
<body>

<form action="/nchannel/UserAddServlet" method="post">

	<ul>

		<li>
			<h2>ユーザー登録</h2>
		</li>
	
		<li>
			<label for="uid">ユーザーＩＤ</label>
			<input type="text" id="uid" name="uid" 
				readonly placeholder="自動採番されます"> 
		</li>
		
		<li>
			<label for="name">名前（必須）</label>
			<input type="text" id="name" name="name" maxlength="40" 
				required placeholder="画面に表示される名前を入力してください">
		</li>
	
		<li>
			<label for="pass">パスワード（必須）</label>
			<input type="password"  id="pass" name="pass" maxlength="20"  pattern="^[0-9a-zA-Z]{8,20}$"
				required placeholder="半角英数8文字以上20文字以内で入力してください" >
		</li>
		
		<li>
			<div class="button"><button type="submit" >登録</button></div>
		</li>

	</ul>

</form>

</body>
</html>