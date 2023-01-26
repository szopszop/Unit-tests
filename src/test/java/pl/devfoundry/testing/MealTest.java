package pl.devfoundry.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class MealTest {

    @Spy
    private Meal mealSpy;

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

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 19})
    void mealPricesShouldBeLowerThan20(int price) {
        assertThat(price, lessThan(20));
    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgerShouldHaveCorrectNameAndPrice(String name, int price) {
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(10));
    }

    private static Stream<Arguments> createMealsWithNameAndPrice() {
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheeseburger", 12)
        );
    }

    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNamesShouldEndWithCake(String name) {
        assertThat(name, notNullValue());
        assertThat(name, endsWith("cake"));
    }

    @Test
    void testMealSumPrice() {
        // given
        Meal meal = mock(Meal.class);

        given(meal.getPrice()).willReturn(15);
        given(meal.getQuantity()).willReturn(3);

        given(meal.sumPrice()).willCallRealMethod();

        // when
        int result = meal.sumPrice();

        // then
        assertThat(result, equalTo(45));
    }

    private static Stream<String> createCakeNames() {
        List<String> cakeNames = Arrays.asList("Cheesecake", "Fruitcake", "Cupcake");
        return cakeNames.stream();
    }

    @Test
    void testMealSumPriceWithSpy() {
        // given
        Meal meal = spy(Meal.class);

        given(meal.getPrice()).willReturn(15);
        given(meal.getQuantity()).willReturn(3);

//        given(meal.sumPrice()).willCallRealMethod();

        // when
        int result = meal.sumPrice();

        // then
        then(meal).should().getPrice();
        then(meal).should().getQuantity();
        assertThat(result, equalTo(45));
    }

    @Test
    void testMealSumPriceWithSpyWithSpecificConstructor() {
        // given
        Meal meal = new Meal(15, 3, "Burrito");
        Meal mealSpy = spy(meal);

        given(mealSpy.getPrice()).willReturn(15);
        given(mealSpy.getQuantity()).willReturn(3);

//        given(meal.sumPrice()).willCallRealMethod();

        // when
        int result = mealSpy.sumPrice();

        // then
        then(mealSpy).should().getPrice();
        then(mealSpy).should().getQuantity();
        assertThat(result, equalTo(45));
    }

}