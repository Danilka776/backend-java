package edu.project2;

public class Main {

    private Main() {
    }

    private static final int DIM = 20;
    private static final int X = 1;
    private static final int Y = 5;

    public static void main(String[] args) {
        MazeGenerator2 maze = new MazeGenerator2(DIM, X, Y);
        maze.makeMaze();
        maze.printMaze();
    }
}
