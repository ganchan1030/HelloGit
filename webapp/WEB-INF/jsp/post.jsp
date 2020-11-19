<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account, model.PostList, java.util.List" %>
<%
Account account =(Account)session.getAttribute("loginUser");
String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RECSPOT|投稿ページ</title>
<link rel="stylesheet" type="text/css" href="/PageStyle.css"> <!-- オンライン時は"/PageStyle.css" -->
</head>
<body>
    <div class="header">
      <div class="container">
        <div class="header-logo">RECSPOT</div>
        <div class="header-list">
          <ul>
            <li><a href = "/Main"><%=account.getName()%>さん</a></li>
            <li><a href = "/Main">投稿一覧へ</a></li>
            <li><a href = "/Logout">ログアウト</a></li>
          </ul>
        </div>
      </div>
    </div>
	<div class="top-wrapper">
		<div class="container">
			<div class="acount-wrap">
			<form action = "/Post" enctype="multipart/form-data" method = "post">
			<h1>投稿する</h1>
			<section>
		    <div class="inner">
			<% if(errorMsg != null){ %>
			<h6><%=errorMsg %></h6>
			<%} %>
			<h3>タイトル(50字以内)</h3><input type ="text" class ="title" maxlength="50" name ="title"><br>
			<h3>説明(255字以内)</h3><textarea name="text" maxlength="255" rows="5" cols="44"></textarea><br>
			<h3>画像</h3>
			<div class = "file">
			<label for=”id_img” >
			<input id="id_img" type="file" name ="file">
			</label>
			</div>
		    </div>
			<input type ="submit" value ="投稿" class="button"><br>
			<a href="/Main">戻る</a>
			</section>
			</form>
			</div>
		</div>
	</div>
</body>
</html>