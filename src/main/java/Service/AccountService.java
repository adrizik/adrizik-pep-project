package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    private AccountDAO accountDAO;

    public Account addAccount(Account account) {
        
       if(account.username != " " || account.password.length() > 4){
            return accountDAO.insertAccount(account);
        }
        return null;
    }

}