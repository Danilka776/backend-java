package edu.hw6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class Task2 {
    private Task2() {
    }

    private static final String EXTENTIONS = ".txt";

    static void cloneFile(Path path) throws IOException {
        File file = new File(String.valueOf(path));
        String baseName = path.toString().substring(0, path.toString().length() - EXTENTIONS.length());
        int count = 1;
        String newName;
        if (file.exists()) {
            while (true) {
                newName = baseName + " — копия" + (count > 1 ? " (" + count + ")" : "") + EXTENTIONS;
                File newFile = new File(newName);
                if (!newFile.exists()) {
                    break;
                }
                count++;
            }
            File newFile = new File(newName);
            Files.copy(path, newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

}
