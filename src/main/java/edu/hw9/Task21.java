package edu.hw9;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public final class Task21 {

    private Task21() {
    }

    public static final int NUM_OF_FILES_IN_DIRECTORY = 10; // 1_000

    public static class FileSearchTask extends RecursiveTask<List<File>> {
        private final File directory;

        public FileSearchTask(File directory) {
            this.directory = directory;
        }

        @Override
        protected List<File> compute() {
            List<File> result = new ArrayList<>();
            List<FileSearchTask> subTasks = new ArrayList<>();

            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        result.add(file);
                        FileSearchTask subtask = new FileSearchTask(file);
                        subTasks.add(subtask);
                        subtask.fork();
                    }
                }
            }

            for (FileSearchTask subtask : subTasks) {
                result.addAll(subtask.join());
            }

            return result;
        }

        public static List<File> getBigDirectory(String pathName) {
            ForkJoinPool forkJoinPool = new ForkJoinPool();

            File rootDirectory = new File(pathName);

            FileSearchTask fileSearchTask = new FileSearchTask(rootDirectory);
            List<File> result = forkJoinPool.invoke(fileSearchTask);

            List<File> directoriesWithFiles = new ArrayList<>();
            for (File directory : result) {
                File[] files = directory.listFiles();
                if (files != null && files.length > NUM_OF_FILES_IN_DIRECTORY) {
                    directoriesWithFiles.add(directory);
                }
            }

            return directoriesWithFiles;
        }
    }



}
