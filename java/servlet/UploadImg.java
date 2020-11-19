//使わない。byte64のサンプルサーブレット。
package servlet;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/**
 * Servlet implementation class UploadImg
 */
@WebServlet("/UploadImg")
@MultipartConfig(maxFileSize=1048576)
public class UploadImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  try {
	  Part part = request.getPart("file");
	  InputStream fileContent = part.getInputStream();
	  byte[] byteArray = getByteArray(fileContent);
	  String img = Base64.getEncoder().encodeToString(byteArray);

	  String name = this.getFileName(part);
	  request.setAttribute("name", name);
	  request.setAttribute("img", img);
	  }catch(Exception e) {}
	  //part.write(getServletContext().getRealPath("/uploaded") + "/" + name);
	  RequestDispatcher d = request.getRequestDispatcher("upload.jsp");
	  d.forward(request, response);
	}

	private String getFileName(Part part) {
	  String name = null;
	    for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
	      if (dispotion.trim().startsWith("filename")) {
	        name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
	        name = name.substring(name.lastIndexOf("\\") + 1);
	        break;
	      }
	    }
	  return name;
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
