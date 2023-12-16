package edu.hw10;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task2 {

    private Task2() {
    }



    public static class CacheProxy {

        public static <T> T create(T obj, Class<?> cl) {
            return (T) Proxy.newProxyInstance(
                cl.getClassLoader(),
                cl.getInterfaces(),
                new CacheHandler(obj)
            );
        }

        private static class CacheHandler implements InvocationHandler {

            private final static Logger LOGGER = LogManager.getLogger();
            private final Object target;
            private final Map<String, Object> cache;
            private final String tempDirectory; // Directory to persist cached results

            CacheHandler(Object target) {
                this.target = target;
                this.cache = new HashMap<>();
                this.tempDirectory = "src/main/java/edu/hw10/";
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Cache cacheAnnotation = method.getAnnotation(Cache.class);
                if (cacheAnnotation != null) {
                    String key = generateCacheKey(method, args);
                    if (cache.containsKey(key)) {
                        return cache.get(key);
                    }

                    Object result = method.invoke(target, args);
                    cache.put(key, result);

                    if (cacheAnnotation.persist()) {
                        persistResult(method, key, result);
                    }

                    return result;
                } else {
                    return method.invoke(target, args);
                }
            }

            private String generateCacheKey(Method method, Object[] args) {
                StringBuilder keyBuilder = new StringBuilder(method.getName());
                for (Object arg : args) {
                    keyBuilder.append("_").append(arg.toString());
                }
                return keyBuilder.toString();
            }

            private void persistResult(Method method, String key, Object result) {
                try {
                    String fileName = tempDirectory + getFileName(method);
                    File file = new File(fileName);
                    file.createNewFile();
                    try (FileWriter writer = new FileWriter(file, true)) {
                        String text = method.getName() + ": " + key + " - " + result.toString();
                        writer.write(text);
                        writer.append('\n');

                        writer.flush();
                    } catch (Exception e) {
                        LOGGER.info(e.getMessage());
                    }
                } catch (IOException e) {
                    LOGGER.info(e.getMessage());
                }
            }

            private String getFileName(Method method) {
                return method.getName() + ".cache";
            }
        }

        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface Cache {
            boolean persist() default false;
        }

        public interface FibCalculator {
            @Cache(persist = true)
            long fib(int number);
        }


        public static class FibCalculatorImpl implements FibCalculator {
            @Override
            public long fib(int number) {
                if (number <= 1) {
                    return number;
                } else {
                    return fib(number - 1) + fib(number - 2);
                }
            }
        }
    }



}
