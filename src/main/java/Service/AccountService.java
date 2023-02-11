package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
       accountDAO = new AccountDAO();
    }

    public Account addAccount(Account account) {
        
       if(account.username.length() > 0 && account.password.length() > 4){
            return accountDAO.insertAccount(account);
        }
        return null;
    }

    public Account findAccount(Account account) {
        return accountDAO.findUserAndPassword(account);
    }

}