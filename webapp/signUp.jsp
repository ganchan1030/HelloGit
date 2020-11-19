<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String errorMsgId = (String)request.getAttribute("errorMsgId");
   String errorMsgPass = (String)request.getAttribute("errorMsgPass");
   String errorMsgName = (String)request.getAttribute("errorMsgName");
   String errorMsgAge = (String)request.getAttribute("errorMsgAge");
   String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録 | RECSPOT</title>
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
		<form action = "/SignUpConfig" method = "post"> <!-- オンライン時は"/SignUpconfig" -->
		<h1>ユーザー登録</h1>
		<section>
		<div class ="inner">
		<% if(errorMsg != null){ %>
		<h6><%=errorMsg %></h6>
		<%} %>
		<% if(errorMsgId != null){ %>
		<h6><%=errorMsgId %></h6>
		<%} %>
		<% if(errorMsgPass != null){ %>
		<h6><%=errorMsgPass %></h6>
		<%} %>
		<% if(errorMsgName != null){ %>
		<h6><%=errorMsgName %></h6>
		<%} %>
		<% if(errorMsgAge != null){ %>
		<h6><%=errorMsgAge %></h6>
		<%} %>
		<h3>ID(30文字以内)</h3><input type ="text" maxlength="30" name ="userId"><br>
		<h3>パスワード(30文字以内)</h3><input type ="password" maxlength="30" name ="pass"><br>
		<h3>表示名(40文字以内)</h3><input type ="text" maxlength="40" name ="name">(表示名は他のユーザーに公開されます)<br>
		<h3>年齢</h3><select name="age" class="select-box">
		<%for(int i=0; i<=120; i++){ %>
		<option value = <%=i %>><%=i %></option>
		<%} %>
		</select>歳<br>
		</div>
		<input type ="submit" value ="ユーザー登録" class="button"><br>
		<a href="index.jsp">トップへ戻る</a>
		</section>
		</form>
	</div>
	</div>
	</div>
	</body>
</html>