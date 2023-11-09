package edu.project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dictionary {
    private static ArrayList<String> words;
    private static final Random RANDOMIZER = new Random();

    private final static Logger LOGGER = LogManager.getLogger();

    Dictionary(String fileName) throws FileNotFoundException {
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            words = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                words.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("");
        }
    }

    public static Integer getMaxMistake() {
        int data = 0;
        try {
            File myObj = new File("../maxMistake");
            Scanner myReader = new Scanner(myObj);
            if (myReader.hasNextLine()) {
                data = Integer.parseInt(myReader.nextLine());
            } else {
                throw new EmptyFileException("File is empty.");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            LOGGER.info("An error occurred.");
        } catch (EmptyFileException e) {
            LOGGER.info(e.getMessage());
        }
        return data;
    }

    public String getRandomWord(int idx) {
        int index;
        if (idx == -1) {
            index = RANDOMIZER.nextInt(words.size());
        } else {
            index = idx;
        }
        return words.get(index);
    }

    static class EmptyFileException extends Exception {
        EmptyFileException(String message) {
            super(message);
        }
    }

}
