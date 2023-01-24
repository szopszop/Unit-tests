package pl.devfoundry.testing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {
        // given
        Meal meal = new Meal(35);

        // when
        int discountedPrice = meal.getDiscountedPrice(9);
        assertEquals(26,discountedPrice);
        // Hamcrest
        assertThat(discountedPrice, equalTo(26));
        // AssertJ
        assertThat(discountedPrice).isEqualTo(26);

    }

    @Test
    void referencesToTheSameObjectShouldNotBeTheSameObject() {
        // given
         Meal meal1 = new Meal(10);
         Meal meal2 = new Meal(10);

         // then
        assertNotSame(meal1, meal2);
        // Hamcrest
        assertThat(meal1, not(sameInstance(meal2)));
        // AssertJ
        assertThat(meal1).isNotSameAs(meal2);

    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameMatch() {
        // given
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pizza");

        // then  ( equals & hashcode override )
        assertEquals(meal1, meal2);
    }
    @Test
    void referenceToTheSameObjectShouldBeEqual() {
        // given
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = meal1;

        // then  ( equals & hashcode override )
        assertSame(meal1, meal2);
    }

    @Test
    void exceptionShouldBeThrownIfDiscountedPriceIsHigherThanThePrice() {
        // given
        Meal meal = new Meal(8, "Soup");
        // then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(10));
    }

}