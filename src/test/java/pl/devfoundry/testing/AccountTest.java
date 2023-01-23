package pl.devfoundry.testing;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {


    @Test
    void newAccountIsNotActive() {
        // given
        Account newAccount = new Account();
        // then
        assertThat(newAccount.isActive(), is(false));
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


}