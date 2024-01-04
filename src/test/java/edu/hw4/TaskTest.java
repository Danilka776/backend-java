package edu.hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Error.ValidationError.INVALID_AGE;
import static edu.hw4.Error.ValidationError.INVALID_FIRST_LETTER;
import static edu.hw4.Error.ValidationError.DIGIT_IN_NAME;
import static edu.hw4.Error.ValidationError.INVALID_HEIGHT;
import static edu.hw4.Error.ValidationError.INVALID_WEIGHT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    @DisplayName("Sort animal by height")
    void sortByHeight() {
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
    @DisplayName("Sort animal by weigth and get first k")
    void sortByWeight() {
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
    @DisplayName("Сalculate how many animals of each type")
    void countType() {
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
    @DisplayName("Get animal with longest name")
    void longestName() {
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
    @DisplayName("Calculate who has more: males or females")
    void maleVsFemale() {
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
    @DisplayName("Give out the heaviest animal of each type")
    void heaviestAnimalByType() {
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
    @DisplayName("Give out the kth oldest animal")
    void kOldestAnimal() {
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
    @DisplayName("The heaviest animal among animals below k cm")
    void heaviestAnimalAmongLow() {
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
    @DisplayName("Give total paws of animal")
    void totalPaws() {
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
    @DisplayName("Give out the animals whose age does not match the number of paws")
    void differNumberAgeAndPaws() {
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
    @DisplayName("Give out animals that can bite and whose height exceeds 100 cm")
    void canBiteAndHeight() {
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
    @DisplayName("How many animals are in the list whose weight exceeds height")
    void countAnimalWeightExceedsHeight() {
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
    @DisplayName("Give out animals whose names consist of more than two words")
    void longName() {
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
    @DisplayName("Is there a dog in the list with a height of more than k cm")
    void isHeightDog() {
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

    @Test
    @DisplayName("Give out total weight of animal of each type")
    void totalWeight() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 4, 101, 5, false);
        Animal animal2 = new Animal("dog without big nose", Animal.Type.DOG, Animal.Sex.F, 2, 120, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 90, 8, false);
        Animal animal4 = new Animal("spider with paws", Animal.Type.SPIDER, Animal.Sex.F, 8, 1, 1, true);
        Animal animal5 = new Animal("fish", Animal.Type.FISH, Animal.Sex.M, 100, 1, 4, true);
        Animal animal6 = new Animal("spider", Animal.Type.SPIDER, Animal.Sex.M, 50, 1, 4, false);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);
        zoo.add(animal5);
        zoo.add(animal6);
        int k = 2;
        int l = 100;

        // when
        Map<Animal.Type, Integer> res = AnimalUtility.totalWeightByType(zoo, k, l);

        // then
        assertEquals(res, Stream.of(new Object[][] {
            {Animal.Type.CAT, 5},
            {Animal.Type.DOG, 10},
            {Animal.Type.FISH, 4},
            {Animal.Type.SPIDER, 5},
        }).collect(Collectors.toMap(data -> (Animal.Type) data[0], data -> (Integer) data[1])));
    }

    @Test
    @DisplayName("Give out list of sorted animal by type, then by sex, then by name")
    void sortedAnimalByTypeSexName() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 4, 101, 5, false);
        Animal animal2 = new Animal("dog without big nose", Animal.Type.DOG, Animal.Sex.F, 2, 120, 10, true);
        Animal animal3 = new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 1, 90, 8, false);
        Animal animal4 = new Animal("spider with paws", Animal.Type.SPIDER, Animal.Sex.F, 8, 1, 1, true);
        Animal animal5 = new Animal("fish", Animal.Type.FISH, Animal.Sex.M, 100, 1, 4, true);
        Animal animal6 = new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 50, 1, 4, false);
        Animal animal7 = new Animal("spider with eyes", Animal.Type.SPIDER, Animal.Sex.F, 50, 1, 4, false);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);
        zoo.add(animal5);
        zoo.add(animal6);
        zoo.add(animal7);

        // when
        Animal[] res = AnimalUtility.sortedZoo(zoo).toArray(new Animal[0]);

        // then
        assertArrayEquals(res, new Animal[] {animal3, animal1, animal2, animal5, animal6, animal7, animal4});
    }

    @Test
    @DisplayName("Is it true that spiders bites more often than dog")
    void isSpiderDangerousDog() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 4, 101, 5, false);
        Animal animal2 = new Animal("dog without big nose", Animal.Type.DOG, Animal.Sex.F, 2, 120, 10, true);
        Animal animal3 = new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 1, 90, 8, false);
        Animal animal4 = new Animal("spider with paws", Animal.Type.SPIDER, Animal.Sex.F, 8, 1, 1, true);
        Animal animal5 = new Animal("fish", Animal.Type.FISH, Animal.Sex.M, 100, 1, 4, true);
        Animal animal6 = new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 50, 1, 4, false);
        Animal animal7 = new Animal("spider with eyes", Animal.Type.SPIDER, Animal.Sex.F, 50, 1, 4, true);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);
        zoo.add(animal5);
        zoo.add(animal6);
        zoo.add(animal7);

        // when
        Boolean res = AnimalUtility.isSpiderDangerDog(zoo);

        // then
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Give out the the heaviest fish")
    void heaviestFish() {
        // given
        List<Animal> zoo1 = new ArrayList<Animal>();
        List<Animal> zoo2 = new ArrayList<Animal>();
        List<Animal> zoo3 = new ArrayList<Animal>();
        Animal animal1 = new Animal("cat1", Animal.Type.CAT, Animal.Sex.F, 4, 101, 5, false);
        Animal animal2 = new Animal("dog without big nose", Animal.Type.DOG, Animal.Sex.F, 2, 120, 10, true);
        Animal animal3 = new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 1, 90, 8, false);
        Animal animal4 = new Animal("spider with paws", Animal.Type.SPIDER, Animal.Sex.F, 8, 1, 1, true);
        Animal animal5 = new Animal("fish", Animal.Type.FISH, Animal.Sex.M, 100, 1, 4, true);
        Animal animal6 = new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 50, 1, 4, false);
        Animal animal8 = new Animal("fish1", Animal.Type.FISH, Animal.Sex.F, 50, 1, 16, true);
        Animal animal9 = new Animal("fish2", Animal.Type.FISH, Animal.Sex.F, 50, 1, 20, true);
        Animal animal10 = new Animal("fish2", Animal.Type.FISH, Animal.Sex.F, 50, 1, 5, true);

        zoo1.add(animal1);
        zoo1.add(animal2);
        zoo1.add(animal3);
        zoo1.add(animal4);
        zoo1.add(animal5);
        zoo1.add(animal6);
        zoo1.add(animal9);

        zoo2.add(animal1);
        zoo2.add(animal2);
        zoo2.add(animal3);
        zoo2.add(animal4);
        zoo2.add(animal5);
        zoo2.add(animal6);
        zoo2.add(animal8);

        zoo3.add(animal1);
        zoo3.add(animal2);
        zoo3.add(animal3);
        zoo3.add(animal4);
        zoo3.add(animal5);
        zoo3.add(animal10);
        zoo3.add(animal9);

        List<List<Animal>> zoos = new ArrayList<>(Arrays.asList(zoo1, zoo2, zoo3));

        // when
        Animal res = AnimalUtility.heaviestFish(zoos);

        // then
        assertThat(res).isEqualTo(animal9);
    }

    @Test
    @DisplayName("Give out animal with errors in record")
    void errorsRecord() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("sam", Animal.Type.CAT, Animal.Sex.F, 4, 101, 5, false);
        Animal animal2 = new Animal("Dog without big nose", Animal.Type.DOG, Animal.Sex.F, 2, 120, 10, true);
        Animal animal3 = new Animal("Bob", Animal.Type.CAT, Animal.Sex.F, 15, 1, 8, false);
        Animal animal4 = new Animal("spider with paws", Animal.Type.SPIDER, Animal.Sex.F, 8, 1, 1, true);
        Animal animal5 = new Animal("fish2", Animal.Type.FISH, Animal.Sex.M, -1, 1, 4, true);
        Animal animal6 = new Animal("Spider3", Animal.Type.SPIDER, Animal.Sex.M, 50, 50, 31, false);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);
        zoo.add(animal5);
        zoo.add(animal6);


        // when
        Map<String, Set<Error.ValidationError>> res = AnimalUtility.wrongAnimalField(zoo);

        // then
        assertEquals(res, Stream.of(new Object[][] {
            {"sam", Set.of(INVALID_FIRST_LETTER, INVALID_HEIGHT)},
            {"Dog without big nose", Set.of(INVALID_HEIGHT)},
            {"spider with paws", Set.of(INVALID_FIRST_LETTER)},
            {"fish2", Set.of(INVALID_FIRST_LETTER, DIGIT_IN_NAME, INVALID_AGE)},
            {"Spider3", Set.of(DIGIT_IN_NAME, INVALID_WEIGHT, INVALID_HEIGHT)},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> data[1])));
    }

    @Test
    @DisplayName("Give out more informative map with errors in record")
    void informativeErrorsRecord() {
        // given
        List<Animal> zoo = new ArrayList<Animal>();
        Animal animal1 = new Animal("sam", Animal.Type.CAT, Animal.Sex.F, 4, 101, 5, false);
        Animal animal2 = new Animal("Dog without big nose", Animal.Type.DOG, Animal.Sex.F, 2, 120, 10, true);
        Animal animal3 = new Animal("Bob", Animal.Type.CAT, Animal.Sex.F, 15, 1, 8, false);
        Animal animal4 = new Animal("spider with paws", Animal.Type.SPIDER, Animal.Sex.F, 8, 1, 1, true);
        Animal animal5 = new Animal("fish2", Animal.Type.FISH, Animal.Sex.M, -1, 1, 4, true);
        Animal animal6 = new Animal("Spider3", Animal.Type.SPIDER, Animal.Sex.M, 50, 50, 31, false);

        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);
        zoo.add(animal5);
        zoo.add(animal6);


        // when
        Map<String, String> res = AnimalUtility.wrongAnimalStringField(zoo);

        // then
        assertEquals(res, Stream.of(new Object[][] {
            {"sam", "name, height"},
            {"Dog without big nose", "height"},
            {"spider with paws", "name"},
            {"fish2", "name, age"},
            {"Spider3", "name, height, weight"},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> data[1])));
    }
}
