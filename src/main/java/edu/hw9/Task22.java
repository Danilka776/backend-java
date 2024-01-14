package edu.hw9;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public final class Task22 {

    private Task22() {
    }

    public static class FileSearchTask extends RecursiveTask<List<File>> {
        private final File directory;
        private final Predicate<File> predicate;

        public FileSearchTask(File directory, Predicate<File> predicate) {
            this.directory = directory;
            this.predicate = predicate;
        }

        @Override
        protected List<File> compute() {
            List<File> result = new ArrayList<>();
            List<FileSearchTask> subtasks = new ArrayList<>();

            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        FileSearchTask subtask = new FileSearchTask(file, predicate);
                        subtask.fork();
                        subtasks.add(subtask);
                    } else {
                        if (predicate.test(file)) {
                            result.add(file);
                        }
                    }
                }
            }

            for (FileSearchTask subtask : subtasks) {
                result.addAll(subtask.join());
            }

            return result;
        }

        public static List<File> getFileByPredicate(String pathName, int sizeOfFile, String endOfFile) {
            File directory = new File(pathName);
            Predicate<File> sizePredicate = file -> file.length() > sizeOfFile;
            Predicate<File> extensionPredicate = file -> file.getName().endsWith(endOfFile);

            ForkJoinPool forkJoinPool = new ForkJoinPool();

            FileSearchTask fileSearchTask = new FileSearchTask(
                directory,
                sizePredicate.and(extensionPredicate)
            );

            List<File> result = forkJoinPool.invoke(fileSearchTask);

            return result;
        }
    }

}
