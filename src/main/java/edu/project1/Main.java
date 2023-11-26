package edu.project1;

import java.io.FileNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {

    public final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) throws FileNotFoundException {
        ConsoleHangman game = new ConsoleHangman();
        int a = game.game();
        if (a == -1) {
            LOGGER.info("Not game");
        } else if (a == 0) {
            LOGGER.info("Start game");
        } else {
            LOGGER.info("Game ended by user");
        }
    }
}
