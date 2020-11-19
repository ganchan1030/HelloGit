package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import model.SignUpLogic;

/**
 * Servlet implementation class SignUpConfig
 */
@WebServlet("/SignUpConfig")
public class SignUpConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  //リクエストパラメータの取得
	  request.setCharacterEncoding("UTF-8");
	  String userId = request.getParameter("userId");
	  String pass = request.getParameter("pass");
	  String name = request.getParameter("name");
	  int age = Integer.valueOf(request.getParameter("age"));
	  
	  //入力漏れがないか確認
	  int check = 0;
	  if(userId == null || userId.length() == 0) {
	    String errorMsgId ="IDが入力されていません";
	    request.setAttribute("errorMsgId", errorMsgId);
	    check++;
	  }
	  if(pass == null || pass.length() == 0) {
	    String errorMsgPass ="パスワードが設定されていません";
	    request.setAttribute("errorMsgPass", errorMsgPass);
	    check++;
	  }
	  if(name == null || name.length() == 0) {
	    String errorMsgName ="表示名が入力されていません";
	    request.setAttribute("errorMsgName", errorMsgName);
	    check++;
	  }
	  
	  //入力漏れが1つ以上あった場合、ログインページに戻ってエラーメッセージを表示
	  if(check > 0) {
	    String errorMsg = check+"個の項目が未入力です";
	    request.setAttribute("errorMsg", errorMsg);
	    RequestDispatcher d = request.getRequestDispatcher("signUp.jsp");
	    d.forward(request, response);
	  }
	  else {
	  //Accountインスタンスを生成し、リクエストスコープに保存
	  Account account = new Account(userId, pass, name, age);
	  request.setAttribute("account", account);
	  
	  SignUpLogic sul = new SignUpLogic(); 
	  boolean isSignUp = sul.signup(account);
	  
	  
	  //登録出来たら登録完了ページへ
	  if(isSignUp == true) {
	    RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/signUpOk.jsp");
	    d.forward(request, response);
	  }else {
	    String errorMsgId = "IDが重複しています";
	    request.setAttribute("errorMsgId", errorMsgId);
	    RequestDispatcher d = request.getRequestDispatcher("signUp.jsp");
	    d.forward(request, response);
	  }
	}
  }
}
