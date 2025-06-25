import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void shouldThrowIllegalArgumentExceptionIfNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfEmpty() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorsesShouldReturnTheSameList(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse " + (i + 1), i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void allHorsesShouldMove () {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    public void shouldWinTheBiggestDistance() {
        Horse horse1 = new Horse("name1", 1, 5);
        Horse horse2 = new Horse("name2", 1, 89.7);
        Horse horse3 = new Horse("name3", 1, 18.15);
        Horse horse4 = new Horse("name4", 1, 94);
        Horse horse5 = new Horse("name5", 1, 50);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4, horse5));

        assertSame(horse4, hippodrome.getWinner());
    }
}
