package edu.hw10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MyClass1 {

    final int intValue;
    final String stringValue;

    public MyClass1(int intValue, String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }

    public static MyClass1 create() {
        return new MyClass1(42, "Default");
    }
}

class MyClass2 {
    private final int intValue;
    private final Double doubleValue;

    public MyClass2(@Task1.Min(0) @Task1.Max(1_000_000) int intValue, Double doubleValue) {
        this.intValue = intValue;
        this.doubleValue = doubleValue;
    }

    public static MyClass2 create() {
        return new MyClass2(42, 0.005);
    }

    @Override
    public String toString() {
        return "MyClass2{" +
            "intValue=" + intValue +
            ", doubleValue='" + doubleValue + '\'' +
            '}';
    }
}

class MyClass {

    private final int intValue;
    private final String stringValue;

    public MyClass(@Task1.Min(0) @Task1.Max(1_000_000) int intValue, String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }

    public static Task1.MyClass create() {
        return new Task1.MyClass(42, "Default");
    }

    @Override
    public String toString() {
        return "MyClass{" +
            "intValue=" + intValue +
            ", stringValue='" + stringValue + '\'' +
            '}';
    }
}

public class Task1Test {
    @Test
    @DisplayName("Checking the correctness of calling a class function(class with factory)")
    void correctCall() throws Throwable {
        // given
        Task1.RandomObjectGenerator rog = new Task1.RandomObjectGenerator();

        // when
        MyClass1 myClassWithFactory = rog.nextObject(MyClass1.class, "create");
        // then
        assertThat(myClassWithFactory.intValue).isEqualTo(42);
        assertThat(myClassWithFactory.stringValue).isEqualTo("Default");
    }

    @Test
    @DisplayName("Checking the correctness of calling a class constructor")
    void correctConstructor() throws Throwable {
        // given
        Task1.RandomObjectGenerator rog = new Task1.RandomObjectGenerator();

        // when
        MyClass2 myClass2 = rog.nextObject(MyClass2.class);
        // then
        // no exception
    }

    @Test
    @DisplayName("Checking the exception while calling No accessible class")
    void catchException() throws Throwable {
        // given
        Task1.RandomObjectGenerator rog = new Task1.RandomObjectGenerator();

        // when
        try {
            MyClass myClass = rog.nextObject(MyClass.class);
        } catch (Exception e) {
            assertNotEquals("", e.getMessage());
        }
        // then

    }

}
