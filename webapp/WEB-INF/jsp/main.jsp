<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.Account, model.PostList, java.util.List" %>
<%
Account account =(Account)session.getAttribute("loginUser");
@SuppressWarnings("unchecked")
List<PostList> postList = (List<PostList>)request.getAttribute("postList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホームメニュー | RECSPOT</title>
<link rel="stylesheet" type="text/css" href="/RECSPOT/PageStyle.css"> <!-- オンライン時は"/PageStyle.css" -->
</head>
<body>
    <div class="header">
      <div class="container">
        <div class="header-logo">RECSPOT</div>
        <div class="header-list">
          <ul>
            <li><a href = "/Main"><%=account.getName()%>さん</a></li>
            <li><a href = "/Post">投稿する</a></li>
            <li><a href = "/Logout">ログアウト</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="post-wrap">
		<h1 class="postlisttitle">投稿一覧</h1>
		<ul>
		<% int i=0; %>
		<% for(PostList post: postList){ %>
		<li>
		<div  class="postlist">
		<img src="data:image/png;base64,<%= post.getImg()%>">
		<div class="postinfo">
		<h3><%= post.getDisplayTitle() %></h3>
		<h5><%= post.getDate() %></h5>
		<h6>posted by <span class="postname"><%= post.getName()%></span></h6>
		</div>
		</div>
		</li>
		<% i++; %>
		<% if(i%3 == 0){%>
		</ul>
		<ul>
		<%} %>
		<% } %>
		</ul>

	</div>
</body>
</html>