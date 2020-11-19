package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.PostList;
import model.PostLogic;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  PostLogic postLogic = new PostLogic();
	  List<PostList> postList = postLogic.putPostContents();
	  request.setAttribute("postList", postList);

	  //ログイン状態確認
	  HttpSession session = request.getSession();
	  Account loginUser = (Account)session.getAttribute("loginUser");

	  if(loginUser == null) {
		response.sendRedirect("login.jsp");
	  }else {
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		d.forward(request, response);
	  }
	}

}
