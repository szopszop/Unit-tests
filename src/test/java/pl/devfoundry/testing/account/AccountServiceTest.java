package pl.devfoundry.testing.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    @Test
    void getAllActiveAccounts() {
        // given
        List<Account> accounts = prepareAccountData();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        when(accountRepository.getAllAccounts()).thenReturn(accounts);
        // the same, different names
        given(accountRepository.getAllAccounts()).willReturn(accounts);
        // when
        List<Account> accountList = accountService.getAllActiveAccounts();

        // then
        assertThat(accountList, hasSize(2));
    }

    @Test
    void getNoActiveAccounts() {
        // given
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(List.of());
        // when
        List<Account> accountList = accountService.getAllActiveAccounts();

        // then
        assertThat(accountList, hasSize(0));
    }

    private List<Account> prepareAccountData() {
        Account account1 = new Account(new Address("Bronowicka", "81C"));
        Account account2 = new Account();
        Account account3 = new Account(new Address("Krakowska", "15/15"));
        Account account4 = new Account();
        return Arrays.asList(account1, account2, account3, account4);
    }

}
