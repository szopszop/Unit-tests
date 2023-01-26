package pl.devfoundry.testing.account;

import java.util.Arrays;
import java.util.List;

public class AccountRepositoryStub implements AccountRepository{

    @Override
    public List<Account> getAllAccounts() {
        Account account1 = new Account(new Address("Bronowicka", "81C"));
        Account account2 = new Account();
        Account account3 = new Account(new Address("Krakowska", "15/15"));
        Account account4 = new Account();
        return Arrays.asList(account1, account2, account3, account4);
    }


}
