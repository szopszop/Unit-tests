package pl.devfoundry.testing.homework;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class CoordinatesTest {

    @Test
    void creatingCoordinatesWithPositiveNumbers() {
        // given
        Coordinates coordinates = new Coordinates(2, 3);
        // then
        assertThat(coordinates).isInstanceOf(Coordinates.class);
    }

    @Test
    void creatingCoordinatesWithNegativeNumbersThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-2, 3));
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(2, -3));
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-2, -3));
    }

    @Test
    void creatingCoordinatesWithNumbersAbove100ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(101, 3));
    }

    @Test
    void referenceToTheSameObject() {
        Coordinates coordinates1 = new Coordinates(2,2);
        Coordinates coordinates2 = coordinates1;

        // then
        assertSame(coordinates1, coordinates2);
    }

    @Test
    void referenceToIdenticalObjectShouldBeEqual() {
        Coordinates coordinates1 = new Coordinates(2,2);
        Coordinates coordinates2 = new Coordinates(2,2);

        // then
        assertEquals(coordinates1, coordinates2);
    }

    @Test
    void copyCoordinated() {
        Coordinates coordinates1 = new Coordinates(10,10);

        Coordinates coordinates2 = Coordinates.copy(coordinates1, 5, 10);

        assertThat(coordinates2.getX(), equalTo(15));
        assertThat(coordinates2.getY(), equalTo(20));
    }

    @Test
    void copyShouldReturnNewObject() {
        Coordinates coordinates = new Coordinates(1,1);

        Coordinates copy = Coordinates.copy(coordinates, 0, 0);

        assertThat(copy, not(sameInstance(coordinates)));
        assertThat(copy, equalTo(coordinates));
    }



}