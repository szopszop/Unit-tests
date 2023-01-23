package pl.devfoundry.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {
        // given
        Meal meal = new Meal(35);

        // when
        int discountedPrice = meal.getDiscountedPrice(9);
        assertEquals(26,discountedPrice);
    }

    @Test
    void referencesToTheSameObjectShouldNotBeTheSameObject() {
        // given + when
         Meal meal1 = new Meal(10);
         Meal meal2 = new Meal(10);

         // then
        assertNotSame(meal1, meal2);
    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameMatch() {
        // given + when
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pizza");

        // then  ( equals & hashcode override )
        assertEquals(meal1, meal2);
    }


}