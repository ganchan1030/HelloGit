package model;

import java.io.Serializable;

public class PostList implements Serializable{
  private int id;
  private String title;
  private String displayTitle;
  private String text;
  private String name;
  private String date;
  private String img;

  //POSTIDによる検索用
  public PostList(int id) {
	  this.id = id;
  }

  //取得用
  public PostList(int id, String title, String text, String name, String date, String img) {
	this.id = id;
	this.title = title;
	this.displayTitle = "<a href="+"\""+"/ViewPost?id="+ id + "\"" +">"+title+"</a>";
	this.text = text;
	this.name = name;
	this.date = date;
	this.img = img;
  }
  //投稿用
  public PostList(String title, String text, String name, String date, String img) {
	this.title = title;
	this.text = text;
	this.name = name;
	this.date = date;
	this.img = img;
  }

  public int getId() {return id;}
  public String getName() {return name;}
  public String getTitle() {return title;}
  public String getDisplayTitle() {return displayTitle;}
  public String getText() {return text;}
  public String getDate() {return date;}
  public String getImg() {return img;}
}
