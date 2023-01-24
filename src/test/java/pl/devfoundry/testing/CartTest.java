package pl.devfoundry.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test cases for Cart")
class CartTest {

    @Test
    void simulateLargeOrder() {
        // given
        Cart cart = new Cart();

        // when + then
        assertTimeout(Duration.ofMillis(30), cart::simulateLargeOrder);
    }

    @Test
    void cartShouldNotBeEmptyAfterAddingOrderToCart() {
        // given
        Order order = new Order();
        Cart cart = new Cart();

        // when
        cart.addOrderToCart(order);

        // then - anyOf - just one is needed to pass
        assertThat(cart.getOrders(), anyOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));

        assertThat(cart.getOrders(), allOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));

        assertAll("Group of assertions for cart",
                () -> assertThat(cart.getOrders(), notNullValue()),
                () -> assertThat(cart.getOrders(), hasSize(1)),
                () -> assertThat(cart.getOrders(), is(not(empty()))),
                () -> assertThat(cart.getOrders(), is(not(emptyCollectionOf(Order.class)))),
                () -> assertThat(cart.getOrders().get(0).getMeals(), empty() )
                );

    }



}