package servlet;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Account;
import model.PostList;
import model.PostLogic;
/**
 * Servlet implementation class Post
 */
@WebServlet("/Post")
@MultipartConfig(maxFileSize=1048576)
public class Post extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  //ログイン状態確認
	  HttpSession session = request.getSession();
	  Account loginUser = (Account)session.getAttribute("loginUser");

	  if(loginUser == null) {
		response.sendRedirect("login.jsp");
	  }else {
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/post.jsp");
		d.forward(request, response);
	  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  //投稿フォームからタイトルとテキスト読み込む
	  request.setCharacterEncoding("UTF-8");
      try {
      Part part = request.getPart("file");
      InputStream fileContent = part.getInputStream();
      byte[] byteArray = getByteArray(fileContent);
      String img = Base64.getEncoder().encodeToString(byteArray);

	  String title = request.getParameter("title");
	  String text = request.getParameter("text");
	  //タイトル、本文、画像が入力されていれば、投稿
	  if(text != null && text.length() != 0 && title != null & title.length() != 0 && img != null && img.length() != 0) {
		//セッションスコープからユーザ情報取得
		HttpSession session = request.getSession();
		Account loginUser = (Account)session.getAttribute("loginUser");
	    //投稿日時を保存
		LocalDateTime nowDateTime = LocalDateTime.now();
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	    String date = nowDateTime.format( format );
		//POSTLISTに追加
		PostList postList = new PostList(title, text, loginUser.getName(), date, img);
		PostLogic postLogic = new PostLogic();
		postLogic.postContents(postList);

	  }else{
	    request.setAttribute("errorMsg", "未記入項目があるか、画像が選択されていません");
	    RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/post.jsp");
	    d.forward(request, response);
	  }
    }catch(Exception e) {}

	  //投稿一覧をリクエストスコープに保存
	  PostLogic postLogic = new PostLogic();
	  List<PostList> postList = postLogic.putPostContents();
	  request.setAttribute("postList", postList);

	  response.sendRedirect("/Main");
	}

	private static byte[] getByteArray(InputStream is) throws Exception {
	    ByteArrayOutputStream b = new ByteArrayOutputStream();
	    BufferedOutputStream os = new BufferedOutputStream(b);
	    while (true) {
	      int i = is.read();
	      if (i == -1) break;
	      os.write(i);
	    }
	    os.flush();
	    os.close();
	    return b.toByteArray();
	  }
}
