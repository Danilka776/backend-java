package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {

    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        ConsoleHangman game = new ConsoleHangman();
        int a = game.game();
        if (a == 1) {
            LOGGER.info("Not game");
        } else {
            LOGGER.info("Start game");
        }
    }
}
