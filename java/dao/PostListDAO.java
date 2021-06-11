package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.PostList;

public class PostListDAO {
  //データベース情報(オンライン)
  String hostname = "ec2-52-20-160-44.compute-1.amazonaws.com";
  String dbname ="d5a4a1dcp9vs2v";
  private final String JDBC_URL = "jdbc:postgresql://" + hostname + ":5432/" + dbname;
  private final String DB_USER = "tlebjnjrncwdyc";
  private final String DB_PASS = "992525c79a7a7887cfcc940d6fc50e2f5ac428d61ac4dedb72bcd1d12d42bdeb";
  

  //すべての投稿を読み込む
  public List<PostList> loadAllPost(){
    List<PostList> postList = new ArrayList<>();

    //データベース接続
    try(Connection conn =DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){

    //SELECT文
    String sql ="SELECT POST_ID, TITLE, TEXT, NAME, DATE, IMG FROM POSTLIST ORDER BY POST_ID DESC";
    PreparedStatement pStmt = conn.prepareStatement(sql);

    //SERECT実行
    ResultSet rs = pStmt.executeQuery();

      while(rs.next()) {
        int postId = rs.getInt("POST_ID");
        String title = rs.getString("TITLE");
        String text = rs.getString("TEXT");
        String name = rs.getString("NAME");
        String date = rs.getString("DATE");
        String img = rs.getString("IMG");
        PostList post = new PostList(postId, title, text, name, date, img);
        postList.add(post);
      }

    }catch(SQLException e) {
      e.printStackTrace();
      return null;
    }
    return postList;
    }

  //POSTIDから投稿を検索する
  public PostList findPost(PostList pst) {
    PostList post = null;

    try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){

    //SERECT文
    String sql ="SELECT POST_ID, TITLE, TEXT, NAME, DATE, IMG FROM POSTLIST WHERE POST_ID = ?";
    PreparedStatement pStmt = conn.prepareStatement(sql);
    pStmt.setInt(1, pst.getId());

    //SERECT実行
    ResultSet rs = pStmt.executeQuery();

    //POST_IDが一致したら、PostListインスタンスを生成
    if(rs.next()) {
      int id = rs.getInt("POST_ID");
      String title = rs.getString("TITLE");
      String text = rs.getString("TEXT");
      String name = rs.getString("NAME");
      String date = rs.getString("DATE");
      String img = rs.getString("IMG");
      post = new PostList(id, title, text, name, date, img);
    }
  }catch(SQLException e) {
    e.printStackTrace();
    return null; //ユーザー一致しなかった場合はnullを返す
  }
    return post; //if内で保存したaccountを返す
  }

  //投稿する
  public boolean createPost(PostList postList) {
   //データベース接続
   try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){

   //INSERT文
   String sql ="INSERT INTO POSTLIST(TITLE, TEXT, NAME, DATE, IMG) VALUES(?,?,?,?,?)";
   PreparedStatement pStmt = conn.prepareStatement(sql);

   //INSERT文の"?"を埋める
   pStmt.setString(1, postList.getTitle());
   pStmt.setString(2, postList.getText());
   pStmt.setString(3, postList.getName());
   pStmt.setString(4, postList.getDate());
   pStmt.setString(5, postList.getImg());

   //INSERT実行
   int result = pStmt.executeUpdate();
   if(result != 1) {
     return false;
    }
  }catch(SQLException e) {
    e.printStackTrace();
    return false;
  }
    return true;
  }
}
