package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    private AccountDAO accountDAO;

    public Account newAccount(Account account) {
        return accountDAO.newAccount(account);

    }

    public Account LoginAccount(Account loginAccount) {
        return null;
    }

}