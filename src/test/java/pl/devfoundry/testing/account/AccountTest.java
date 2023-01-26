package pl.devfoundry.testing.account;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AccountTest {


    @Test
    void newAccountIsNotActive() {
        // given
        Account newAccount = new Account();
        // then
        assertFalse(newAccount.isActive());

    }

    @Test
    void activationWorks() {
        // given
        Account newAccount = new Account();

        // when
        newAccount.activate();

        // then
        assertTrue(newAccount.isActive());
    }

    @Test
    void newlyCreatedAccountAccountDoesNotHaveDefaultDeliveryAddress() {
        // given
        Account account = new Account();

        // when
        Address address = account.getDefaultDeliveryAddress();

        // then
        assertNull(address);
    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet() {
        // given
        Address address = new Address("Krakowska", "64/11");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);

        // when
        Address defaultAddress =account.getDefaultDeliveryAddress();

        // then
        assertNotNull(defaultAddress);
    }

    @RepeatedTest(25)
    void newAccountWithNotNullAddressShouldBeActive() {
        // given
        Address address = new Address("Bronowicka", "33/3");

        // when
        Account account = new Account(address);

        // then
        assumingThat(address != null, () -> {
            assertTrue(account.isActive());
        });
    }

    @Test
    void invalidEmailShouldThrowException() {
        // given
        Account account = new Account();
        // when
        //then
        assertThrows(IllegalArgumentException.class, ()-> account.setEmail("Wrong email format"));
    }

    @Test
    void validEmailShouldBeSet() {
        // given
        Account account = new Account();
        // when
        account.setEmail("szymontracz1@gmail.com");
        // then
        assertThat(account.getEmail(), is("szymontracz1@gmail.com"));
    }

    @Test
    void accountWithoutAddressShouldNotBeActive() {
        // given
        Account account = new Account(null);
        // when
        // then
        assertThat(account.isActive(), is(false));
    }

}