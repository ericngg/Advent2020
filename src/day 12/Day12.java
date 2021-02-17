import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day12 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        input(list);

        //System.out.println(partOne(list));
        System.out.println(partTwo(list));
    }

    // Solution to part 1
    public static int partOne(List<String> list) {
        Ship ship = new Ship(0, 0, "E");

        for (int i = 0; i < list.size(); i++) {
            ship.move(list.get(i));
        }

        return ship.calculate();
    }

    // Solution to part 2
    public static int partTwo(List<String> list) {
        Ship ship = new Ship(0, 0, "E");

        for (int i = 0; i < list.size(); i++) {
            ship.move2(list.get(i));
        }

        return ship.calculate();
    }

    public static class Ship {
        private int north; // positive value is north, negative value is south
        private int east; // positive value is east, negative value is west
        private String direction;

        private int waypointNorth; // positive value is north, negative value is south
        private int waypointEast; // positive value is east, negative value is west

        public Ship(int north, int east, String direction) {
            this.north = north;
            this.east = east;
            this.direction = direction;
            this.waypointNorth = 1;
            this.waypointEast = 10;
        }

        // Part 1 move method
        public void move(String command) {
            String direction = "" + command.charAt(0);
            int value = Integer.parseInt(command.substring(1));
            if (direction.equals("R")) {
                rotateRight(value);
            } else if (direction.equals("L")) {
                rotateLeft(value);
            } else if (direction.equals("F")) {
                moveHelper(this.direction, value);
             } else {
                moveHelper(direction, value);
            }
        }

        // Part 1 move method helper
        private void moveHelper(String direction, int value) {
            if (direction.equals("N")) {
                this.north += value;
            } else if (direction.equals("E")) {
                this.east += value;
            } else if (direction.equals("S")) {
                this.north -= value;
            } else {
                this.east -= value;
            }
        }

        // Part 2 move method
        public void move2(String command) {
            String direction = "" + command.charAt(0);
            int value = Integer.parseInt(command.substring(1));

            if (direction.equals("R")) {
                rotateRight2(value);
            } else if (direction.equals("L")) {
                rotateLeft2(value);
            }  else {
                moveHelper2(direction, value);
            }
        }

        // Part 2 move method helper
        private void moveHelper2(String direction, int value) {
            if (direction.equals("F")) {
                this.north += (value * this.waypointNorth);
                this.east += (value * this.waypointEast);
            } else if (direction.equals("N")) {
                this.waypointNorth += value;
            } else if (direction.equals("E")) {
                this.waypointEast += value;
            } else if (direction.equals("S")) {
                this.waypointNorth -= value;
            } else {
                this.waypointEast -= value;
            }
        }

        // Part 1 change direction based on rotation to the right
        private void rotateRight(int value) {
            int count = value / 90;

            while (count > 0) {
                if (this.direction.equals("N")) {
                    this.direction = "E";
                } else if (this.direction.equals("E")) {
                    this.direction = "S";
                } else if (this.direction.equals("S")) {
                    this.direction = "W";
                } else {
                    this.direction = "N";
                }
                count--;
            }
        }

        // Part 1 change direction based on rotation to the left
        private void rotateLeft(int value) {
            int count = value / 90;

            while (count > 0) {
                if (this.direction.equals("N")) {
                    this.direction = "W";
                } else if (this.direction.equals("W")) {
                    this.direction = "S";
                } else if (this.direction.equals("S")) {
                    this.direction = "E";
                } else {
                    this.direction = "N";
                }
                count--;
            }
        }

        // Part 2 rotate waypoint to the right by the value
        public void rotateRight2(int value) {
            int count = value / 90;

            while (count > 0) {
                int temp = this.waypointNorth;
                this.waypointNorth = -1 * waypointEast;
                this.waypointEast = temp;

                count--;
            }
        }

        // Part 2 rotate waypoint to the left by the value
        public void rotateLeft2(int value) {
            int count = value / 90;

            while (count > 0) {
                int temp = this.waypointNorth;
                this.waypointNorth = waypointEast;
                this.waypointEast = -1 * temp;

                count--;
            }
        }

        // calculates manhattan distance for both part 1 and part 2
        public int calculate() {
            return Math.abs(this.north) + Math.abs(this.east);
        }

        // method for testing
        public void print() {
            System.out.println("Ship is - N: " + this.north + " and E: " + this.east);
            System.out.println("waypoint is - N: " + this.waypointNorth + " and  E: " + this.waypointEast);
        }
    }

    // Helper method for iterating through input
    public static void input(List<String> list) throws FileNotFoundException {
        File file = new File("src/day 12/input.txt");
        Scanner scan = new Scanner(file);

        while(scan.hasNext()) {
            list.add(scan.nextLine());
        }

        scan.close();
    }
}