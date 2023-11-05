package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AnimalUtility {

    private AnimalUtility() {
    }

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
}
