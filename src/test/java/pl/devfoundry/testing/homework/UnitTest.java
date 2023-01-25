package pl.devfoundry.testing.homework;

import org.assertj.core.error.ShouldBeLessOrEqual;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    void unitShouldMove() {
        // given
        Coordinates coordinates = new Coordinates(50, 30);
        Unit unit = new Unit(coordinates, 100, 100);
        // when
        Coordinates move = unit.move(10, 10);
        // then
        assertThat(move, equalTo(new Coordinates(60, 40)));
    }

    @Test
    void unitShouldLoseFuel() {
        // given
        Coordinates coordinates = new Coordinates(50, 30);
        Unit unit = new Unit(coordinates, 100, 100);
        // when
        unit.move(10, 10);
        // then
        assertThat(unit.getFuel(), equalTo(80));
    }

    @Test
    void unitShouldThrowExceptionWhenThereIsNotEnoughFuel() {
        // given
        Coordinates coordinates = new Coordinates(50, 30);
        Unit unit = new Unit(coordinates, 100, 100);
        // when
        // then
        assertThrows(IllegalStateException.class, () -> unit.move(100, 10));
    }

    @Test
    void fuelingShouldNotExceedMaxFuelLimit() {
        // given
        Coordinates coordinates = new Coordinates(50, 30);
        Unit unit = new Unit(coordinates, 100, 100);
        // when
        unit.move(3,3);
        unit.tankUp();
        // then
        assertThat(unit.getFuel(), lessThanOrEqualTo(100));
    }

    @Test
    void cargoCanNotExceedMaxWeightLimit() {
        // given
        Coordinates coordinates = new Coordinates(50, 30);
        Unit unit = new Unit(coordinates, 100, 100);
        // when
        // then
        assertThrows(IllegalStateException.class, () -> unit.loadCargo(new Cargo("Shoe container", 1000)));
    }

    @Test
    void unloadingAllCargoShouldReduceUnitLoadToZero() {
        // given
        Coordinates coordinates = new Coordinates(50, 30);
        Unit unit = new Unit(coordinates, 100, 100);
        // when
        Cargo shoes = new Cargo("Shoe container", 50);
        Cargo laces = new Cargo("Laces container", 30);
        unit.loadCargo(shoes);
        unit.loadCargo(laces);
        assertThat(unit.getLoad(), equalTo(80));
        unit.unloadCargo(shoes);
        unit.unloadAllCargo();
        // then
        assertThat(unit.getLoad(), equalTo(0));

    }
}