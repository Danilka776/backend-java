package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
//import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Checking that the toString method produces the correct result")
    void testHelloByteBuddy() throws Exception {
        // given
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();

        // when
        String result = dynamicType.getDeclaredConstructor().newInstance().toString();

        // then
        assertThat(result).isEqualTo("Hello, ByteBuddy!");
    }

}
