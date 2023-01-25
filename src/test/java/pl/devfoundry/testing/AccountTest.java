package pl.devfoundry.testing;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
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


}