import com.google.common.collect.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by fuwei on 2017/2/9.
 */
public class LambdaTest {
    @Test
    @DisplayName("test stream convert")
    public void testStreamConvert() {
        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        assertEquals(36, nums.stream().
                filter(num -> num != null).
                distinct().
                mapToInt(num -> num * 2).skip(2).
                peek(System.out::println).limit(4).sum());
    }

    @Test
    @DisplayName("test stream reduce return optional val")
    public void testStreamReduce() {
        List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertEquals(55, ints.stream().reduce((sum, item) -> sum + item).get().intValue());
    }

    @Test
    @DisplayName("test stream reduce return default val")
    public void testStreamReduceHaveDefault() {
        List<Integer> ints = Lists.newArrayList();
        assertEquals(0, ints.stream().reduce(0, (sum, item) -> sum + item).intValue());
    }

    @Test
    @DisplayName("test stream match return boolean")
    public void testStreamMatch() {
        List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertAll(
                () -> assertTrue(ints.stream().allMatch(item -> item < 11)),//必须所有都满足条件
                () -> assertTrue(ints.stream().anyMatch(item -> item < 2)),//必须至少有一项满足条件
                () -> assertTrue(ints.stream().noneMatch(item -> item > 10))//所有元素都不满足条件
        );
    }

    @Test
    @DisplayName("test stream max() and min()")
    public void testStreamMaxAndMin() {
        List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertAll(
                () -> assertEquals(10, ints.stream().max((o1, o2) -> o1.compareTo(o2)).get().intValue()),//必须所有都满足条件
                () -> assertEquals(1, ints.stream().min((o1, o2) -> o1.compareTo(o2)).get().intValue())//必须所有都满足条件
        );
    }
}