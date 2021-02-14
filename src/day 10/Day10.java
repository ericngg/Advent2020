import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> list = new ArrayList<>();
        input(list);

        // System.out.println(partOne(list));
        System.out.println(partTwo(list));
    }

    public static int partOne(List<Integer> list) {
        Collections.sort(list);

        int oneJolt = 0;
        int threeJolt = 1;

        int current = 0;

        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);

            if (num - current == 1) {
                oneJolt++;
                System.out.println("1: " + oneJolt);
            } else if (num - current == 3) {
                threeJolt++;
                System.out.println("3: " + threeJolt);
            }

            current = num;
        }

        return oneJolt * threeJolt;
    }

    // DP problem TODO: learn DP
    public static int partTwo(List<Integer> list) {

        return 0;
    }

    // Helper method for input
    public static void input(List<Integer> list) throws FileNotFoundException {
        File file = new File("src/day 10/test.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            list.add(Integer.parseInt(scan.nextLine()));
        }

        scan.close();
    }
}