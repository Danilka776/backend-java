package edu.project5;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class ReflectionBenchmark {

    @SuppressWarnings({"UncommentedMain", "MagicNumber"})
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(120))
            .build();

        new Runner(options).run();
    }

    private Student student;
    private Method reflectionMethod;
    private MethodHandle methodHandle;
    private StudentName getter;

    @SuppressWarnings("MultipleStringLiterals")
    @Setup
    public void setup() throws Throwable {
        student = new Student("Alexander", "Biryukov");
        reflectionMethod = Student.class.getMethod("name");
        MethodType factoryType = MethodType.methodType(StudentName.class);

        MethodHandles.Lookup caller = MethodHandles.lookup();

        MethodType methodType = MethodType.methodType(String.class);
        methodHandle = MethodHandles.lookup().findVirtual(Student.class, "name", methodType);

        MethodType lambda = MethodType.methodType(String.class, Student.class);

        getter = (StudentName) LambdaMetafactory.metafactory(
            caller,
            "get",
            factoryType,
            lambda,
            methodHandle,
            lambda
        ).getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        String name = (String) reflectionMethod.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandles(Blackhole bh) throws Throwable {
        String name = (String) methodHandle.invokeExact(student);
        bh.consume(name);
    }

    @Benchmark
    public String lambdaMetafactory() throws Throwable {

        return getter.get(student);
    }

    public interface StudentName {
        String get(Student student);
    }

    record Student(String name, String surname) {
    }
}
