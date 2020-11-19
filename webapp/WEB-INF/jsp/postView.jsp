<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.PostList"%>
<%@ page import="model.Account"%>
<%
PostList postList = (PostList)request.getAttribute("postList");
Account account = (Account)session.getAttribute("loginUser");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title><%=postList.getTitle() %> | RECSPOT</title>
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
    <div class="post-wrap">
	<div class="posttitle">
	<h1><%=postList.getTitle() %></h1>
	<%=postList.getDate() %><br>
	投稿者:<%=postList.getName() %>
	</div>
	<img src="data:image/png;base64,<%= postList.getImg()%>" class="postimg">
	<p class="posttext"><%=postList.getText() %></p><br>
	<a href = "/Main" class="postreturn">戻る</a>
</div>
</body>
</html>