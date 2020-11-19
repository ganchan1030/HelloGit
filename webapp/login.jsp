<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String errorMsg = (String)request.getAttribute("errorMsg"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン | RECSPOT</title>
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
		  <form action = "/Login" method = "post"> <!-- オンライン時は"/Login" -->
		    <h1>ログイン</h1>
		    <section>
		    <div class="inner">
		   	<% if(errorMsg != null){ %>
			<h6><%=errorMsg %></h6>
			<%} %>
		    <h3>ID</h3><input type ="text" maxlength="30" name ="userId"><br>
		    <h3>パスワード</h3><input type ="password" maxlength="30" name ="pass"><br>
		    </div>
			<input type ="submit" value ="ログイン" class="button" style="font-style:20px;"><br>
		    <a href="index.jsp">トップへ戻る</a>
		    </section>
		  </form>
		</div>
      </div>
    </div>

  </body>
</html>