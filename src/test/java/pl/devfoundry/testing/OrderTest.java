package pl.devfoundry.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;

    @BeforeEach
    void initializeOrder() {
        order = new Order();
    }

    @AfterEach
    void cleanUp() {
        order.cancel();
    }

    @Test
    void testAssertArray() {
        // when
        int[] ints1 = {1, 2, 3};
        int[] ints2 = {1, 2, 3};

        // then
        assertArrayEquals(ints1, ints2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder() {

        // then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize() {
        // given
        Meal hamburger = new Meal(15, "Hamburger");

        // when
        order.addMealToOrder(hamburger);

        // then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(hamburger));
        assertThat(order.getMeals(), hasItem(hamburger));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize() {
        // given
        Meal hamburger = new Meal(15, "Hamburger");

        // when
        order.addMealToOrder(hamburger);
        order.removeMealFromOrder(hamburger);

        // then
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), not(contains(hamburger)));
    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToOrder() {
        // given
        Meal hamburger = new Meal(15, "Hamburger");
        Meal sandwich = new Meal(15, "Sandwich");

        // when
        order.addMealToOrder(hamburger);
        order.addMealToOrder(sandwich);

        // then
        assertThat(order.getMeals(), contains(hamburger, sandwich));
        // different order
        assertThat(order.getMeals(), containsInAnyOrder(sandwich, hamburger));

    }

    @Test
    void mealsShouldNotBeInCorrectOrderAfterAddingThemToOrder() {
        // given
        Meal hamburger = new Meal(15, "Hamburger");
        Meal sandwich = new Meal(15, "Sandwich");

        // when
        order.addMealToOrder(hamburger);
        order.addMealToOrder(sandwich);

        // then
        assertThat(order.getMeals(), not(contains(sandwich, hamburger)));
    }

    @Test
    void testIfTwoMealsListsAreTheSame() {
        // given
        Meal hamburger = new Meal(15, "Hamburger");
        Meal sandwich = new Meal(15, "Sandwich");
        Meal kebab = new Meal(10, "Kebab");

        // when
        List<Meal> meals1 = Arrays.asList(hamburger, sandwich);
        List<Meal> meals2 = Arrays.asList(hamburger, sandwich);

        // then
        assertThat(meals1, is(meals2));

    }

}