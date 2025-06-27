import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    @Test
    public void shouldThrowIllegalArgumentExceptionIfFirstNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "  \n", "\t\t\n"})
    public void shouldThrowIllegalArgumentExceptionIfFirstBlank(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 1, 1));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionIfSecondNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("something", -1, 1));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfThirdNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("something", 1, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void souldGetNameFromConstructor() {
        Horse horse = new Horse("any name", 1, 1);
        assertEquals("any name", horse.getName());
    }

    @Test
    public void souldGetSpeedFromConstructor() {
        Horse horse = new Horse("any name", 70, 1);
        assertEquals(70, horse.getSpeed());
    }

    @Test
    public void souldGetDistanceFromConstructor() {
        Horse horse = new Horse("any name", 1, 20);
        assertEquals(20, horse.getDistance());
    }

    @Test
    public void souldReturnZeroIfNoDistanceInConstructor() {
        Horse horse = new Horse("any name", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void moveInvokegetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
        new Horse("any name", 68, 50).move();

        mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.3, 0.5, 0.7, 0.9, 1.0, 1.99, 15.8, 999.999, 0.0})
    void shouldMoveWithProperlyCalculatedDistance(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("any name", 60, 123);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(123 + 60 * random, horse.getDistance());
        }
    }

}
