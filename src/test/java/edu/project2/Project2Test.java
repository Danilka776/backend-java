package edu.project2;

import edu.project2.MazeGenerator2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Project2Test {
    @Test
    @DisplayName("Incorrect start position")
    void test1() {
        // given
        int dim = 20;
        int x = 21;
        int y = 5;
        MazeGenerator2 maze = new MazeGenerator2(dim, x, y);

        // when
        int res = maze.makeMaze();

        // then
        assertThat(res).isEqualTo(-1);
    }


}
