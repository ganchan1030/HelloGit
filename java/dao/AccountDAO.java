package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.LoginUser;

public class AccountDAO {
  //データベース情報
  String hostname = "ec2-52-20-160-44.compute-1.amazonaws.com";
  String dbname ="d5a4a1dcp9vs2v";

  public final String JDBC_URL = "jdbc:postgresql://" + hostname + ":5432/" + dbname;
  public final String DB_USER = "tlebjnjrncwdyc";
  public final String DB_PASS = "992525c79a7a7887cfcc940d6fc50e2f5ac428d61ac4dedb72bcd1d12d42bdeb";

  //ログイン時に入力されたIDとパスワードを検索
  public Account findAccount(LoginUser user){
    Account account = null;
    //データベース接続
    try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
      conn.setAutoCommit(false);
    //SERECT文
    String sql ="SELECT USER_ID, PASS, NAME, AGE FROM ACCOUNT WHERE USER_ID = ? AND PASS = ?";
    PreparedStatement pStmt = conn.prepareStatement(sql);
    pStmt.setString(1, user.getUserId());
    pStmt.setString(2, user.getPass());

    //SERECT実行
    ResultSet rs = pStmt.executeQuery();

    //ユーザーが一致したら、Accountインスタンスを生成
    if(rs.next()) {
      String userId = rs.getString("USER_ID");
      String pass = rs.getString("PASS");
      String name = rs.getString("NAME");
      int age = rs.getInt("AGE");
      account = new Account(userId, pass, name, age);
    }
  }catch(SQLException e) {
    e.printStackTrace();
    return null; //ユーザー一致しなかった場合はnullを返す
  }
    return account; //if内で保存したaccountを返す
  }

  //アカウントを登録
  public boolean createAccount(Account account) {
  //データベース接続
    try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){

    //INSERT文
    String sql ="INSERT INTO ACCOUNT VALUES(?,?,?,?)";
    PreparedStatement pStmt = conn.prepareStatement(sql);

    //INSERT文の"?"部分
    pStmt.setString(1, account.getUserId());
    pStmt.setString(2, account.getPass());
    pStmt.setString(3, account.getName());
    pStmt.setInt(4, account.getAge());
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

  //サインアップ時にID重複がないか検索
  public boolean findAccountId(Account account) {

    try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
      conn.setAutoCommit(false);

    //SERECT文
    String sql ="SELECT USER_ID, PASS, NAME, AGE FROM ACCOUNT WHERE USER_ID = ?";
    PreparedStatement pStmt = conn.prepareStatement(sql);
    pStmt.setString(1, account.getUserId());

    //SERECT実行
    ResultSet rs = pStmt.executeQuery();

    //一致するIDがあれば、falseを返し、無ければtrueを返す
    if(rs.next()) {
      return false;
    }
  }catch(SQLException e) {
    e.printStackTrace();
  }
    return true;
  }
}
