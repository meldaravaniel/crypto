import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lecture1_Test {

    @Test
    public void testLetterFrequencyAnalysis() {
        List<Character> chars = asList('c', 'd');
        Map<Integer, List<Character>> lettersCount = Lecture1.getLettersCount("aabbbccccddddeeeee");
        assertEquals(chars, lettersCount.get(4));
    }

}
