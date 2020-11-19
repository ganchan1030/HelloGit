package model;

import dao.AccountDAO;

public class LoginJudge {
  public Account judgement(LoginUser user) {
	AccountDAO dao = new AccountDAO();
	Account account = dao.findAccount(user);
	return account ;
  }
}