import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class Day11 {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        input(list);

        //System.out.println(partOne(list));
        System.out.println(partTwo(list));
    }

    // Part one solution
    public static int partOne(List<String> list) {
        Grid grid = initGrid(list);

        while(true) {
            Grid next = grid.getNext();

            if(grid.equals(next)) {
                return grid.countSeats();
            }

            grid = next;
        }
    }

    // Part 2 solution
    public static int partTwo(List<String> list) {
        Grid grid = initGrid(list);

        while(true) {
            Grid next = grid.getVisible();

            if(grid.equals(next)) {
                return grid.countSeats();
            }

            grid = next;
        }
    }

    // Initializes the first grid based on the input
    public static Grid initGrid(List<String> list) {
        Grid grid = new Grid(list.size(), list.get(0).length());

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(0).length(); j++) {
                grid.set(i, j, "" + list.get(i).charAt(j));
            }
        }

        return grid;
    }

    public static class Grid {

        // The grid to keep track of seats
        public String[][] grid;

        // Offset for checking seats that are next to a position
        private int[][] adjacent = new int[][]{ {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0} };

        // Sets a grid with size row and col
        public Grid(int row, int col) {
            grid = new String[row][col];
        }

        // Sets a value to the current grid coordinate
        public void set(int row, int col, String current) {
            grid[row][col] = current;
        }

        // gets the next grid based on part 1 rules.
        public Grid getNext() {
            Grid newGrid = new Grid(grid.length, grid[0].length);

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j].equals("L") && countAdjacent(i, j) == 0) {
                        newGrid.set(i, j, "#");
                    } else if (grid[i][j].equals("#") && countAdjacent(i, j) >= 4) {
                        newGrid.set(i, j, "L");
                    } else {
                        newGrid.set(i, j, grid[i][j]);
                    }
                }
            }

            return newGrid;
        }

        // gets the next grid for part 2 with the visible rule.
        public Grid getVisible() {
            Grid newGrid = new Grid(grid.length, grid[0].length);

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j <grid[i].length; j++) {
                    if (grid[i][j].equals("L") && countVisible(i, j) == 0) {
                        newGrid.set(i, j, "#");
                    }  else if (grid[i][j].equals("#") && countVisible(i, j) >= 5) {
                        newGrid.set(i, j, "L");
                    } else {
                        newGrid.set(i, j, grid[i][j]);
                    }
                }
            }

            return newGrid;
        }

        // counts the number of seats that are occupied
        public int countSeats() {
            int count = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j].equals("#")) {
                        count++;
                    }
                }
            }
            return count;
        }

        // equals one grid with another to see if they match
        public boolean equals(Grid other) {
            for (int i = 0; i < this.grid.length; i++) {
                for (int j = 0; j < this.grid[i].length; j++) {
                    if (!grid[i][j].equals(other.grid[i][j])) {
                        return false;
                    }
                }
            }

            return true;
        }

        // count adjacent of occupied seats
        public int countAdjacent(int row, int col) {
            int count = 0;

            for (int[] offset : adjacent) {
                int newRow = row + offset[0];
                int newCol = col + offset[1];

                if (newRow < 0 || newRow >= this.grid.length || newCol < 0 || newCol >= this.grid[0].length) {
                    continue;
                }

                if (this.grid[newRow][newCol] == "#") {
                    count++;
                }
            }

            return count;
        }

        // count visible occupied seats for part 2
        public int countVisible(int row, int col) {
            int count = 0;

            for (int[] offset : adjacent) {
                int newRow = row;
                int newCol = col;

                while (true) {
                    newRow += offset[0];
                    newCol += offset[1];

                    if (newRow < 0 || newRow >= this.grid.length || newCol < 0 || newCol >= this.grid[0].length) {
                        break;
                    }

                    if (this.grid[newRow][newCol].equals("L")) {
                        break;
                    }

                    if (this.grid[newRow][newCol].equals("#")) {
                        count++;
                        break;
                    }
                }
            }

            return count;
        }

        // Similar to toString(), used for testing/debugging
        public void print() {
            for (int i = 0; i < grid.length; i++) {
                System.out.println(Arrays.toString(grid[i]));
            }
        }

    }

    // Input helper method
    public static void input(List<String> list) throws FileNotFoundException {
        File file = new File("src/day 11/input.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            list.add(scan.nextLine());
        }

        scan.close();
    }
}