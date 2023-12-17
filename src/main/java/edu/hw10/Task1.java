package edu.hw10;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Random;

public final class Task1 {

    private Task1() {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface NotNull {}

    @Retention(RetentionPolicy.RUNTIME)
    @interface Min {
        int value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Max {
        int value();
    }
    static class RandomObjectGenerator {
        private final Random random = new Random();

        public <T> T nextObject(Class<T> cl, String factoryMethod)
            throws Throwable {
            if (factoryMethod == null) {
                return nextObject(cl);
            }
            Method factory = cl.getDeclaredMethod(factoryMethod);
            return (T) nextObject(factory.getReturnType(), factory);
        }

        public <T> T nextObject(Class<T> cl)
            throws Throwable {
            return nextObject(cl, (Method) null);
        }

        private <T> T nextObject(Class<T> cl, Method factory)
            throws Throwable {
            Constructor<?>[] constructors = cl.getConstructors();
            if (constructors.length == 0 && factory == null) {
                throw new IllegalArgumentException("No accessible class: " + cl.getName());
            }

            Constructor<?> constructor = constructors.length > 0 ? constructors[0] : null;

            if (factory != null) {
                MethodHandles.Lookup lookup = MethodHandles.lookup();
                MethodType methodType = MethodType.methodType(cl);
                MethodHandle staticMethodHandle = lookup.findStatic(cl, factory.getName(), methodType);
                return (T) staticMethodHandle.invoke();
            }

            Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Object[] parameters = new Object[parameterTypes.length];

            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i].isPrimitive()) {
                    parameters[i] = generatePrimitiveValue(parameterTypes[i], parameterAnnotations[i]);
                } else {
                    parameters[i] = nextObject(parameterTypes[i]);
                }
            }

            return (T) constructor.newInstance(parameters);
        }

        private Object generatePrimitiveValue(Class<?> type, Annotation[] annotations) {
            Object generateValue;
            if (type == int.class || type == Integer.class) {
                generateValue = generateIntValue(annotations);
            } else if (type == long.class || type == Long.class) {
                generateValue = generateLongValue(annotations);
            } else if (type == double.class || type == Double.class) {
                generateValue = generateDoubleValue(annotations);
            } else if (type == float.class || type == Float.class) {
                generateValue = generateFloatValue(annotations);
            } else if (type == boolean.class || type == Boolean.class) {
                generateValue = random.nextBoolean();
            } else if (type == char.class || type == Character.class) {
                generateValue = generateCharValue(annotations);
            } else {
                throw new IllegalArgumentException("Unsupported primitive type: " + type.getName());
            }
            return generateValue;
        }

        private int generateIntValue(Annotation[] annotations) {
            int min = Integer.MIN_VALUE;
            int max = Integer.MAX_VALUE;

            for (Annotation annotation : annotations) {
                if (annotation instanceof Min) {
                    min = Math.max(min, ((Min) annotation).value());
                } else if (annotation instanceof Max) {
                    max = Math.min(max, ((Max) annotation).value());
                }
            }

            return min + random.nextInt(max - min + 1);
        }

        private long generateLongValue(Annotation[] annotations) {
            long min = Long.MIN_VALUE;
            long max = Long.MAX_VALUE;

            for (Annotation annotation : annotations) {
                if (annotation instanceof Min) {
                    min = Math.max(min, ((Min) annotation).value());
                } else if (annotation instanceof Max) {
                    max = Math.min(max, ((Max) annotation).value());
                }
            }

            return min + random.nextLong(max - min + 1);
        }

        private double generateDoubleValue(Annotation[] annotations) {
            double min = Double.MIN_VALUE;
            double max = Double.MAX_VALUE;

            for (Annotation annotation : annotations) {
                if (annotation instanceof Min) {
                    min = Math.max(min, ((Min) annotation).value());
                } else if (annotation instanceof Max) {
                    max = Math.min(max, ((Max) annotation).value());
                }
            }

            return min + random.nextDouble(max - min + 1);
        }

        private float generateFloatValue(Annotation[] annotations) {
            float min = Float.MIN_VALUE;
            float max = Float.MAX_VALUE;

            for (Annotation annotation : annotations) {
                if (annotation instanceof Min) {
                    min = Math.max(min, ((Min) annotation).value());
                } else if (annotation instanceof Max) {
                    max = Math.min(max, ((Max) annotation).value());
                }
            }

            return min + (float) (random.nextDouble(max - min + 1));
        }

        private char generateCharValue(Annotation[] annotations) {
            char min = Character.MIN_VALUE;
            char max = Character.MAX_VALUE;

            for (Annotation annotation : annotations) {
                if (annotation instanceof Min) {
                    min = (char) Math.max(min, ((Min) annotation).value());
                } else if (annotation instanceof Max) {
                    max = (char) Math.min(max, ((Max) annotation).value());
                }
            }

            return (char) (min + random.nextInt(max - min + 1));
        }
    }

    static class MyClass {

        private final int intValue;
        private final String stringValue;

        MyClass(@Min(0) @Max(1_000_000) int intValue, String stringValue) {
            this.intValue = intValue;
            this.stringValue = stringValue;
        }

        @SuppressWarnings("MagicNumber")
        public static MyClass create() {
            return new MyClass(42, "Default");
        }

        @Override
        public String toString() {
            return "MyClass{"
                + "intValue=" + intValue
                + ", stringValue='" + stringValue + '\''
                + '}';
        }
    }

}
