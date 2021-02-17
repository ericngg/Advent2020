import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day13 {

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> list = new ArrayList<>();
        int busNumber = input(list);

        System.out.println(partOne(list, busNumber));

    }

    // Part 1 solution
    public static int partOne(List<Integer> list, int busNumber) {
        int min = Integer.MAX_VALUE;
        int number = 0;

        for (int i = 0; i < list.size(); i++) {
            int current = list.get(i);
            int num = (busNumber / current * current) + current;

            if (num - busNumber < min) {
                min = num - busNumber;
                number = current;
            }
        }

        return min * number;
    }

    // Part 2, could not do on my own, uses Chinese Remainder Theorem

    // Helper method for input
    public static int input(List<Integer> list) throws FileNotFoundException {
        File file = new File("src/day 13/input.txt");
        Scanner scan = new Scanner(file);

        int busNumber = Integer.parseInt(scan.nextLine());

        String[] busId = scan.nextLine().split(",");

        for (int i = 0; i < busId.length; i++) {
            if (!busId[i].equals("x")) {
                list.add(Integer.parseInt(busId[i]));
            }
        }

        return busNumber;
    }

}