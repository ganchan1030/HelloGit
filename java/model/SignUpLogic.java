package model;

import dao.AccountDAO;

public class SignUpLogic {
  public boolean signup(Account account) {
	AccountDAO dao = new AccountDAO();
	boolean isResult = dao.findAccountId(account);//重複確認
	
	if(isResult == true) {
	  dao.createAccount(account);
	  return true;
	}else {
	  return false;
	}
  }
}
