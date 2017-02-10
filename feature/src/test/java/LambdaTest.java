import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fuwei on 2017/2/9.
 */
public class LambdaTest {
    @Test
    @DisplayName("test stream convert")
    public void testStreamConvert() {
        List<Integer> nums = Arrays.asList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        assertEquals(36, nums.stream().
                filter(num -> num != null).
                distinct().
                mapToInt(num -> num * 2).skip(2).
                peek(System.out::println).limit(4).sum());
    }

    @Test
    @DisplayName("test stream reduce")
    public void testStreamReduce() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertEquals(55, Math.toIntExact(ints.stream().reduce((sum, item) -> sum + item).get()));
    }
}