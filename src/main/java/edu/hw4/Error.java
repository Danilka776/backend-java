package edu.hw4;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public class Error {
    public enum ValidationError {
        INVALID_FIRST_LETTER,
        DIGIT_IN_NAME,
        INVALID_HEIGHT,
        INVALID_WEIGHT,
        INVALID_AGE,
    }

    private Error() {
    }

    public static boolean isValidAnimal(Animal animal) {
        return getValidationErrors(animal).isEmpty();
    }

    @SuppressWarnings({"MultipleStringLiterals", "MagicNumber"})
    public static Set<ValidationError> getValidationErrors(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if (!Character.isUpperCase(animal.name().charAt(0))) {
            errors.add(ValidationError.INVALID_FIRST_LETTER);
        }

        if (animal.name().matches(".*\\d.*")) {
            errors.add(ValidationError.DIGIT_IN_NAME);
        }

        if (animal.height() > 2 || animal.height() < 0) {
            errors.add(ValidationError.INVALID_HEIGHT);
        }

        if (animal.age() > 50 || animal.age() < 0) {
            errors.add(ValidationError.INVALID_AGE);
        }

        if (animal.weight() > 30 || animal.weight() < 0) {
            errors.add(ValidationError.INVALID_WEIGHT);
        }

        return errors;
    }

    @SuppressWarnings("MagicNumber")
    public static String getValidationField(Animal animal) {
        StringJoiner errors = new StringJoiner(", ");

        if (!Character.isUpperCase(animal.name().charAt(0)) || animal.name().matches(".*\\d.*")) {
            errors.add("name");
        }


        if (animal.height() > 2 || animal.height() < 0) {
            errors.add("height");
        }

        if (animal.age() > 50 || animal.age() < 0) {
            errors.add("age");
        }

        if (animal.weight() > 30 || animal.weight() < 0) {
            errors.add("weight");
        }

        return errors.toString();
    }
}
