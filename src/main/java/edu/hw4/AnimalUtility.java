package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class AnimalUtility {

    private AnimalUtility() {
    }

    private static final int HEIGHT = 100;

    public static List<Animal> heightSort(List<Animal> zoo) {
        Comparator<Animal> comparator = new Comparator<Animal>() {
            public int compare(Animal animal1, Animal animal2) {
                return Integer.compare(animal1.height(), animal2.height());
            }
        };
        Stream<Animal> stream = zoo.stream();
        return stream.sorted(comparator).collect(Collectors.toList());
    }

    public static List<Animal> weightSort(List<Animal> zoo, int k) {
        Comparator<Animal> comparator = new Comparator<Animal>() {
            public int compare(Animal animal1, Animal animal2) {
                return -1 * Integer.compare(animal1.weight(), animal2.weight());
            }
        };
        Stream<Animal> stream = zoo.stream();
        return stream.sorted(comparator).limit(k).collect(Collectors.toList());
    }

    public static Map<Animal.Type, Long> countType(List<Animal> zoo) {
        Stream<Animal> stream = zoo.stream();
        return stream.collect(
            Collectors.groupingBy(
                Animal::type,
                Collectors.counting()));
    }

    public static Animal longestName(List<Animal> zoo) {
        Comparator<Animal> comparator = new Comparator<Animal>() {
            public int compare(Animal animal1, Animal animal2) {
                return Integer.compare(animal1.name().length(), animal2.name().length());
            }
        };
        return zoo.stream().max(comparator).orElse(null);
    }


    public static Animal.Sex maleOrFemale(List<Animal> zoo) {
        return zoo.stream().filter(x -> x.sex() == Animal.Sex.F).count()
            > zoo.stream().filter(x -> x.sex() == Animal.Sex.M).count() ? Animal.Sex.F : Animal.Sex.M;
    }

    public static Map<Animal.Type, Animal> heaviestAnimal(List<Animal> zoo) {
        Comparator<Animal> comparator = new Comparator<Animal>() {
            public int compare(Animal animal1, Animal animal2) {
                return Integer.compare(animal1.weight(), animal2.weight());
            }
        };
        return zoo.stream().collect(
            Collectors.groupingBy(
                Animal::type,
                Collectors.maxBy(comparator)))
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    x -> x.getValue().orElse(null)
                )
            );
    }

    public static Animal koldestAnimal(List<Animal> zoo, int k) {
        return zoo.stream().sorted(Comparator.comparing(Animal::age)).skip(k - 1).findFirst().orElse(null);
    }

    public static Optional<Animal> kHeaviestAnimal(List<Animal> zoo, int k) {
        return zoo.stream().filter(x -> x.height() < k).max(Comparator.comparing(Animal::weight));
    }

    public static Integer countPaws(List<Animal> zoo) {
        return Math.toIntExact(zoo.stream().flatMapToInt(x -> IntStream.range(0, x.paws())).count());
    }

    public static List<Animal> filterAgePaws(List<Animal> zoo) {
        return zoo.stream().filter(x -> x.paws() != x.age()).toList();
    }

    public static List<Animal> canBite(List<Animal> zoo) {
        return zoo.stream().filter(x -> x.height() > HEIGHT).filter(Animal::bites).toList();
    }

    public static Integer weightMoreHeight(List<Animal> zoo) {
        return Math.toIntExact(zoo.stream().filter(x -> x.weight() > x.height()).count());
    }

    public static List<Animal> longName(List<Animal> zoo) {
        return zoo.stream().filter(x -> x.name().split(" ").length > 2).toList();
    }

    public static Boolean bigDog(List<Animal> zoo, int k) {
        return zoo.stream().anyMatch(x -> x.type() == Animal.Type.DOG && x.height() > k);
    }

    public static Map<Animal.Type, Integer> totalWeightByType(List<Animal> zoo, int k, int l) {
        return zoo.stream().filter(x -> x.age() >= k && x.age() <= l)
                    .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> sortedZoo(List<Animal> zoo) {
        return zoo.stream().sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
                .collect(Collectors.toList());
    }

    public static Boolean isSpiderDangerDog(List<Animal> zoo) {
        long freqSpider = zoo.stream().filter(x -> x.type() == Animal.Type.SPIDER && x.bites()).count();
        long freqDog = zoo.stream().filter(x -> x.type() == Animal.Type.DOG && x.bites()).count();
        if (freqDog == 0 || freqSpider == 0) {
            return false;
        }
        return freqSpider > freqDog;
    }

    public static Animal heaviestFish(List<List<Animal>> zoos) {
        return zoos.stream().flatMap(List::stream).filter(x -> x.type() == Animal.Type.FISH)
                    .max(Comparator.comparing(Animal::weight)).orElse(null);
    }

    public static Map<String, Set<Error.ValidationError>> wrongAnimalField(List<Animal> zoo) {
        return zoo.stream().filter(x -> !Error.isValidAnimal(x))
                .collect(Collectors.toMap(
                    Animal::name,
                    Error::getValidationErrors
            ));
    }

    public static Map<String, String> wrongAnimalStringField(List<Animal> zoo) {
        return zoo.stream().filter(x -> !Error.isValidAnimal(x))
                .collect(Collectors.toMap(
                    Animal::name,
                    Error::getValidationField
                    //(existing, replacement) -> existing // если есть дубликаты имен, оставляем первую запись
            ));
    }
 }
