package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    // main method
    public static void main(String[] args) {
        LOGGER.info("Привет, мир!");
    }
}
