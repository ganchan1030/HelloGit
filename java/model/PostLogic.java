package model;

import java.util.List;

import dao.PostListDAO;

public class PostLogic {
  public void postContents(PostList postList) {
    PostListDAO dao = new PostListDAO();
    dao.createPost(postList);
  }
  
  public List<PostList> putPostContents(){
    PostListDAO dao = new PostListDAO();
    List<PostList> postList = dao.loadAllPost();
    return postList;
  }
  
  public PostList searchPost(PostList searchPost) {
	PostListDAO dao = new PostListDAO();
	PostList postList = dao.findPost(searchPost);
	return postList;
  }
}
