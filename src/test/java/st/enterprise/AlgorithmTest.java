package st.enterprise;

import org.junit.Before;
import org.junit.Test;
import st.enterprise.algorithm.implementations.SumAllElementsInCollection;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Marcin on 16.02.2018.
 */
public class AlgorithmTest {

    private SumAllElementsInCollection sumAllElementsInCollection;

    @Before
    public void setUp() {
        sumAllElementsInCollection = new SumAllElementsInCollection();
    }

    @Test
    public void sumAllElementsInArrayTest() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer sum = sumAllElementsInCollection.process(integers);

        assertTrue(sum == 55);
    }
}
