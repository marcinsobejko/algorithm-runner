package st.enterprise.algorithm.implementations;

import st.enterprise.algorithm.Algorithm;
import st.enterprise.algorithm.annotations.Condition;
import st.enterprise.algorithm.annotations.End;
import st.enterprise.algorithm.annotations.Function;
import st.enterprise.algorithm.annotations.Start;

import java.util.List;

/**
 * Summ all elements in collection algorithm.
 *
 * Created by Marcin on 17.02.2018.
 */
public class SumAllElementsInCollection extends Algorithm<List<Integer>, Integer> {

    protected List<Integer> integers;

    protected Integer sum;
    protected int index;

    public SumAllElementsInCollection() {
        super();
    }

    @Start(nextBlock = "fn1")
    protected void start(List<Integer> integers) {
        this.integers = integers;
        this.sum = 0;
        this.index = 0;
    }

    @Function(name = "fn1", nextBlock = "con1")
    protected void fn1(List<Integer> integers) {
        sum += integers.get(index);
    }

    @Condition(name = "con1", nextBlockOnFalse = "fn1", nextBlockOnTrue = "end")
    protected boolean con1() {
        return integers.size() > index;
    }

    @End
    protected Integer end() {
        return sum;
    }
}
