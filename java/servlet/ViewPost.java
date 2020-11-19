package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostList;
import model.PostLogic;

/**
 * Servlet implementation class ViewPost
 */
@WebServlet("/ViewPost")
public class ViewPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  //postIdを取得して検索用のインスタンスを生成
	  int postId = Integer.valueOf(request.getParameter("id"));
	  PostList searchPost = new PostList(postId);
	  
	  //PostListDAO検索
	  PostLogic search = new PostLogic();
	  PostList resultPost = search.searchPost(searchPost);
	  
	  //一致した投稿をリクエストスコープに保存
	  request.setAttribute("postList", resultPost);
	  
	  //postView.jspへフォワード
	  RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/postView.jsp");
	  d.forward(request, response);
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//使う可能性あり
	}

}
