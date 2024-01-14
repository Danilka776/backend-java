package edu.hw6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Task1 {
    private Task1() {
    }

    private static final String DIRECTORY = "src/main/java/edu/hw6/";

    public static class DiskMap implements Map<String, String> {
        private final static Logger LOGGER = LogManager.getLogger();
        String fileName = "associativeArray.txt";
        int size = 0;

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public boolean containsKey(Object key) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(DIRECTORY + fileName));
                String line = reader.readLine();
                while (line != null) {
                    if (line.split(":")[0].equals(key.toString())) {
                        return true;
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(DIRECTORY + fileName));
                String line = reader.readLine();
                while (line != null) {
                    if (line.split(":")[1].equals(value.toString())) {
                        return true;
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
            return false;
        }

        @Override
        public String get(Object key) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(DIRECTORY + fileName));
                String line = reader.readLine();
                while (line != null) {
                    if (line.split(":")[0].equals(key)) {
                        return line.split(":")[1];
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
            return null;
        }

        @Nullable
        @Override
        public String put(String key, String value) {
            if (this.get(key) != null) {
                try {
                    String ans = null;
                    File sInputFile = new File(DIRECTORY + fileName);
                    File sTmpFile = new File(DIRECTORY + "temp.txt");

                    BufferedReader sFileReader = new BufferedReader(new FileReader(sInputFile));
                    BufferedWriter sFileWriter = new BufferedWriter(new FileWriter(sTmpFile));

                    String line;
                    while ((line = sFileReader.readLine()) != null) {
                        if (line.split(":")[0].equals(key)) {
                            sFileWriter.write(key + ":" + value);
                            ans = line.split(":")[1];
                        } else {
                            sFileWriter.write(line);
                        }
                        sFileWriter.newLine();
                    }
                    size++;
                    sFileReader.close();
                    sFileWriter.close();

                    sInputFile.delete();
                    sTmpFile.renameTo(sInputFile);

                    return ans;
                } catch (IOException e) {
                    return null;
                }
            } else {
                try {
                    FileWriter fr = new FileWriter(DIRECTORY + fileName, true);
                    BufferedWriter br = new BufferedWriter(fr);
                    if (size != 0) {
                        br.newLine();
                    }
                    br.write(key + ":" + value);
                    size++;
                    br.close();
                    fr.close();

                } catch (IOException e) {
                    return null;
                }
                return null;
            }
        }

        @Override
        public String remove(Object key) {
            if (this.get(key.toString()) != null) {
                try {
                    String ans = null;
                    File sInputFile = new File(DIRECTORY + fileName);
                    File sTmpFile = new File(DIRECTORY + "tmp.txt");

                    BufferedReader sFileReader = new BufferedReader(new FileReader(sInputFile));
                    BufferedWriter sFileWriter = new BufferedWriter(new FileWriter(sTmpFile));

                    String line;
                    while ((line = sFileReader.readLine()) != null) {
                        if (line.split(":")[0].equals(key)) {
                            ans = line.split(":")[1];
                        } else {
                            sFileWriter.write(line);
                        }
                        sFileWriter.newLine();
                    }
                    size--;
                    sFileReader.close();
                    sFileWriter.close();

                    sInputFile.delete();
                    sTmpFile.renameTo(sInputFile);
                    return ans;
                } catch (IOException e) {
                    return null;
                }
            } else {
                return null;
            }
        }

        @Override
        public void putAll(@NotNull Map<? extends String, ? extends String> m) {

        }

        @Override
        public void clear() {

        }

        @NotNull
        @Override
        public Set<String> keySet() {
            return null;
        }

        @NotNull
        @Override
        public Collection<String> values() {
            return null;
        }

        @NotNull
        @Override
        public Set<Entry<String, String>> entrySet() {
            return null;
        }
    }
}
