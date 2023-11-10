package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task8Test {
    @Test
    @DisplayName("ReverseList")
    void test1() {
        // given
        List<Integer> test = new ArrayList<>(List.of(1,2,3));
        List<Integer> ans = new ArrayList<>(List.of(3,2,1));
        Iterator<Integer> trueIter = ans.iterator();

        // when
        Iterator<Integer> newIter = new Task8.BackwardIterator<>(test);

        // then
        while (trueIter.hasNext() && newIter.hasNext()) {
            Integer val1 = trueIter.next();
            Integer val2 = newIter.next();

            assertThat(val1).isEqualTo(val2);
        }
        assertThat(!trueIter.hasNext() && !newIter.hasNext()).isTrue();
    }

}
