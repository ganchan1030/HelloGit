package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.LoginJudge;
import model.LoginUser;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  //リクエストパラメータの取得
	  request.setCharacterEncoding("UTF-8");
	  String userId = request.getParameter("userId");
	  String pass = request.getParameter("pass");

	  //User情報インスタンス生成
	  LoginUser user = new LoginUser (userId, pass);

	  //ログイン処理
	  LoginJudge judge = new LoginJudge();
	  Account account = judge.judgement(user);

	  //ログイン成功時
	  if(account != null) {
    	//セッションスコープにユーザ情報を保存
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", account);
	    //メイン画面にリダイレクト
		response.sendRedirect("/Main"); //オンライン時は"/Main"
	  }else {
		request.setAttribute("errorMsg", "ユーザーIDまたはパスワードに誤りがあります");
	    RequestDispatcher d = request.getRequestDispatcher("login.jsp");
	    d.forward(request, response);
	  }
	}

}
