package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    @DisplayName("Task1")
    void test1() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 10, 3, 5, false);
        Animal animal2 = new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 10, 4, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 10, 2, 4, false);
        Animal animal4 = new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 100, 1, 1, true);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);

        // when
        Animal[] sortedZoo = AnimalUtility.heightSort(zoo).toArray(new Animal[0]);

        // then
        assertArrayEquals(sortedZoo, new Animal[]{animal4, animal3, animal1, animal2});
    }

    @Test
    @DisplayName("Task2")
    void test2() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 10, 3, 5, false);
        Animal animal2 = new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 10, 4, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 10, 2, 8, false);
        Animal animal4 = new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 100, 1, 1, true);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);

        // when
        Animal[] sortedZoo = AnimalUtility.weightSort(zoo, 3).toArray(new Animal[0]);

        // then
        assertArrayEquals(sortedZoo, new Animal[] {animal2, animal3, animal1});
    }

    @Test
    @DisplayName("Task3")
    void test3() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 10, 3, 5, false);
        Animal animal2 = new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 10, 4, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 10, 2, 8, false);
        Animal animal4 = new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 100, 1, 1, true);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);

        // when
        Map<Animal.Type, Long> countedZoo = AnimalUtility.countType(zoo);

        // then
        assertEquals(countedZoo, Stream.of(new Object[][] {
            {Animal.Type.CAT, 2L},
            {Animal.Type.DOG, 1L},
            {Animal.Type.SPIDER, 1L},
        }).collect(Collectors.toMap(data -> (Animal.Type) data[0], data -> (Long) data[1])));
    }

    @Test
    @DisplayName("Task4")
    void test4() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 10, 3, 5, false);
        Animal animal2 = new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 10, 4, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 10, 2, 8, false);
        Animal animal4 = new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 100, 1, 1, true);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);

        // when
        Animal longestName = AnimalUtility.longestName(zoo);

        // then
        assertEquals(longestName, animal4);
    }

    @Test
    @DisplayName("Task5")
    void test5() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 10, 3, 5, false);
        Animal animal2 = new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 10, 4, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 10, 2, 8, false);
        Animal animal4 = new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 100, 1, 1, true);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);

        // when
        Animal.Sex sex = AnimalUtility.maleOrFemale(zoo);

        // then
        assertEquals(sex, Animal.Sex.F);
    }

    @Test
    @DisplayName("Task6")
    void test6() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 10, 3, 5, false);
        Animal animal2 = new Animal("dog1", Animal.Type.DOG, Animal.Sex.F, 10, 4, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 10, 2, 8, false);
        Animal animal4 = new Animal("dog2", Animal.Type.DOG, Animal.Sex.F, 15, 1, 15, true);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);

        // when
        Map<Animal.Type, Animal> heaviestAnimal = AnimalUtility.heaviestAnimal(zoo);

        // then
        assertEquals(heaviestAnimal, Stream.of(new Object[][] {
            {Animal.Type.CAT, animal3},
            {Animal.Type.DOG, animal4},
        }).collect(Collectors.toMap(data -> (Animal.Type) data[0], data -> (Animal) data[1])));
    }

    @Test
    @DisplayName("Task7")
    void test7() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 10, 3, 5, false);
        Animal animal2 = new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 4, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 2, 8, false);
        Animal animal4 = new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 100, 1, 1, true);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);

        // when
        Animal animal = AnimalUtility.koldestAnimal(zoo, 2);

        // then
        assertEquals(animal, animal2);
    }

    @Test
    @DisplayName("Task8")
    void test8() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 10, 3, 5, false);
        Animal animal2 = new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 4, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 2, 8, false);
        Animal animal4 = new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 100, 1, 1, true);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);

        // when
        Optional<Animal> animal = AnimalUtility.kHeaviestAnimal(zoo, 4);

        // then
        assertEquals(animal, Optional.of(animal3));
    }

    @Test
    @DisplayName("Task9")
    void test9() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 4, 3, 5, false);
        Animal animal2 = new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 4, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 2, 8, false);
        Animal animal4 = new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 8, 1, 1, true);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);

        // when
        Integer paws = AnimalUtility.countPaws(zoo);

        // then
        assertEquals(paws, 20);
    }

    @Test
    @DisplayName("Task10")
    void test10() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 4, 3, 5, false);
        Animal animal2 = new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 4, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 2, 8, false);
        Animal animal4 = new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 8, 1, 1, true);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);

        // when
        Animal[] res = AnimalUtility.filterAgePaws(zoo).toArray(new Animal[0]);

        // then
        assertArrayEquals(res, new Animal[] {animal2, animal3});
    }

    @Test
    @DisplayName("Task11")
    void test11() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 4, 101, 5, false);
        Animal animal2 = new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 120, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 90, 8, false);
        Animal animal4 = new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 8, 1, 1, true);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);

        // when
        Animal[] res = AnimalUtility.canBite(zoo).toArray(new Animal[0]);

        // then
        assertArrayEquals(res, new Animal[] {animal2});
    }

    @Test
    @DisplayName("Task12")
    void test12() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 4, 101, 5, false);
        Animal animal2 = new Animal("dog", Animal.Type.DOG, Animal.Sex.F, 2, 120, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 90, 8, false);
        Animal animal4 = new Animal("spider", Animal.Type.SPIDER, Animal.Sex.F, 8, 1, 2, true);
        Animal animal5 = new Animal("fish", Animal.Type.FISH, Animal.Sex.M, 100, 1, 4, true);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);
        zoo.add(animal5);

        // when
        Integer res = AnimalUtility.weightMoreHeight(zoo);

        // then
        assertEquals(res, 2);
    }

    @Test
    @DisplayName("Task13")
    void test13() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 4, 101, 5, false);
        Animal animal2 = new Animal("dog without big nose", Animal.Type.DOG, Animal.Sex.F, 2, 120, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 90, 8, false);
        Animal animal4 = new Animal("spider with paws", Animal.Type.SPIDER, Animal.Sex.F, 8, 1, 1, true);
        Animal animal5 = new Animal("fish", Animal.Type.FISH, Animal.Sex.M, 100, 1, 4, true);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);
        zoo.add(animal5);

        // when
        Animal[] res = AnimalUtility.longName(zoo).toArray(new Animal[0]);

        // then
        assertArrayEquals(res, new Animal[] {animal2, animal4});
    }

    @Test
    @DisplayName("Task14")
    void test14() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 4, 101, 5, false);
        Animal animal2 = new Animal("dog without big nose", Animal.Type.DOG, Animal.Sex.F, 2, 120, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 90, 8, false);
        Animal animal4 = new Animal("spider with paws", Animal.Type.SPIDER, Animal.Sex.F, 8, 1, 1, true);
        Animal animal5 = new Animal("fish", Animal.Type.FISH, Animal.Sex.M, 100, 1, 4, true);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);
        zoo.add(animal5);

        // when
        Boolean res = AnimalUtility.bigDog(zoo, 100);

        // then
        assertThat(res).isTrue();
    }
}
