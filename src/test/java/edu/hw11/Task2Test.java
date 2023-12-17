package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.returns;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Checking the correctness of replacing the addition operation with the multiplication operation")
    void changeSumToMult() throws Exception {
        // given
        ByteBuddyAgent.install();
        new ByteBuddy()
            .redefine(Task2.ArithmeticUtilsNew.class)
            .name(Task2.ArithmeticUtils.class.getName())
            .make()
            .load(
                Task2.ArithmeticUtils.class.getClassLoader(),
                ClassReloadingStrategy.fromInstalledAgent()
            );
        Task2.ArithmeticUtils arithmetic = new Task2.ArithmeticUtils();
        // when
        int result = arithmetic.sum(3, 4);

        // then
        assertThat(result).isEqualTo(12);
    }

}
