<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account" %>
<% Account account =(Account)request.getAttribute("account"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
<link rel="stylesheet" type="text/css" href="/PageStyle.css"> <!-- オンライン時は"/PageStyle.css" -->
</head>
<body>
    <div class="header">
      <div class="container">
        <div class="header-logo">RECSPOT</div>
        <div class="header-list">
          <ul>
            <li><a href ="login.jsp">ログイン</a></li>
            <li><a href ="signUp.jsp">ユーザー登録</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="top-wrapper">
		<div class="container">
			<div class="acount-wrap">
			<h1>ユーザー登録完了</h1>
			<section>
			<p>以下のユーザーを登録しました</p>
			<h5>ユーザーID:<%= account.getUserId() %></h5><br>
			<h5>パスワード:設定されたパスワード</h5><br>
			<h5>表示名:<%= account.getName() %></h5><br>
			<h5>年齢:<%= account.getAge() %></h5><br>
			<a href ="login.jsp">ログイン</a><br>
			<a href ="index.jsp">トップへ</a>
			</section>
			</div>
		</div>
	</div>
</body>
</html>