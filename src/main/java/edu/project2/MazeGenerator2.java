package edu.project2;

import java.util.ArrayList;
import java.util.Random;

class MazeGenerator2 {
    int n;
    int lastX;
    int lastY;
    int startX;
    int startY;
    static boolean[][] north;
    static boolean[][] south;
    static boolean[][] east;
    static boolean[][] west;
    static boolean[][] visited;
    static int numVisited;
    static Random rand;
    static ArrayList<int[]> path;

    MazeGenerator2(int dim, int x, int y) {
        n = dim;
        startX = x;
        startY = y;
    }

    @SuppressWarnings({"RegexpSinglelineJava"})
    void printBoundaries() {
        for (int i = 0; i < n; i++) {
            System.out.print(" _");
        }
    }

    @SuppressWarnings({"RegexpSinglelineJava"})
    void printMaze() {
        printBoundaries();
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print('|');
            for (int j = 0; j < n; j++) {
                boolean inPath = false;
                for (int[] tmp : path) {
                    if (tmp[0] == i && tmp[1] == j) {
                        inPath = true;
                        break;
                    }
                }
                if (i == lastX && j == lastY || i == startX && j == startY) {
                    System.out.print("X");
                } else if (south[i][j] && !inPath) {
                    System.out.print("_");
                } else if (south[i][j] && inPath) {
                    System.out.print("±");
                } else if (inPath) {
                    System.out.print("+");
                } else {
                    System.out.print(" ");
                }
                if (east[i][j]) {
                    System.out.print('|');
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    void setParameters() {
        numVisited = 0;
        rand = new Random();
        north = new boolean[n][n];
        south = new boolean[n][n];
        east = new boolean[n][n];
        west = new boolean[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                north[i][j] = true;
                south[i][j] = true;
                east[i][j] = true;
                west[i][j] = true;
                visited[i][j] = false;
            }
        }
        lastX = -1;
        lastY = -1;
        path = new ArrayList<>();
    }

    void setBoundaries(int r, int c) {
        if (c - lastY == -1) {
            west[lastX][lastY] = false;
            east[r][c] = false;
        } else if (c - lastY == 1) {
            east[lastX][lastY] = false;
            west[r][c] = false;
        } else if (r - lastX == 1) {
            south[lastX][lastY] = false;
            north[r][c] = false;
        } else if (r - lastX == -1) {
            north[lastX][lastY] = false;
            south[r][c] = false;
        }
    }

    ArrayList<int[]> findNeighbours(int r, int c) {
        // find new neighbours that haven't been visited
        ArrayList<int[]> neighs = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                // check boundaries
                if (r + i >= 0 && r + i < n && c + j >= 0 && c + j < n) {
                    if (!visited[r + i][c + j] && (i == 0 || j == 0)) {
                        neighs.add(new int[]{r + i, c + j});
                    }
                }
            }
        }
        return neighs;
    }

    int makeMaze() {
        if (startX > n || startY > n) {
            return -1;
        }
        int r = startX;
        int c = startY;
        setParameters();
        while (numVisited < n * n) {
            if (!visited[r][c]) {
                visited[r][c] = true;
                numVisited++;
            }
            ArrayList<int[]> neighs = new ArrayList<>();

            neighs = findNeighbours(r, c);

            if (neighs.isEmpty()) {
                if (r != lastX || c != lastY) {
                    r = lastX;
                    c = lastY;
                } else {
                    r = path.get(path.size() - 1)[0];
                    c = path.get(path.size() - 1)[1];
                    path.remove(path.size() - 1);
                }
            } else {
                int next = rand.nextInt(neighs.size());
                path.add(new int[]{lastX, lastY});
                lastX = r;
                lastY = c;
                r = neighs.get(next)[0];
                c = neighs.get(next)[1];
                setBoundaries(r, c);
            }
        }
        return 0;
    }
}
