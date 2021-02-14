import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = new ArrayList();
        List<Integer> seat = new ArrayList();
        input(list);

        System.out.println("Max Id - " + maxSeatId(list, seat));

        Collections.sort(seat);

        System.out.println(mySeat(seat));
    }

    // Finds the max seat Id and creates the seat list, Technically could separate seat creation into diff method
    // Time - O(N) Space - O(1)
    public static int maxSeatId(List<String> list, List<Integer> seat) {
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            int seatId = seatScore(list.get(i));
            seat.add(seatId);
            max = Math.max(seatId, max);
        }
        return max;
    }

    // Helper method to find seat score
    public static int seatScore(String seat) {
        int left = 0;
        int right = 127;
        for (int i = 0; i < 6; i++) {
            int half = (right - left) / 2 + 1;
            if (seat.charAt(i) == 'F') {
                right = right - half;
            } else {
                left = left + half;
            }
        }

        int up = 0;
        int down = 7;
        for (int i = 7; i < 9; i++) {
            int half = (down - up) / 2 + 1;

            if (seat.charAt(i) == 'L') {
                down = down - half;
            } else {
                up = up + half;
            }
        }

        int row = 0;
        int col = 0;

        if (seat.charAt(6) == 'F') {
            row = left;
        } else {
            row = right;
        }

        if (seat.charAt(9) == 'L') {
            col = up;
        } else {
            col = down;
        }

        return (row * 8) + col;
    }

    // Part 2 to find my seat
    // Time - O(N) Space - O(1)
    public static int mySeat(List<Integer> seat) {
        int current = seat.get(0);
        for (int i = 1; i < seat.size(); i++) {
            if (current + 1 != seat.get(i)) {
                return current + 1;
            }

            current++;
        }

        return -1;
    }

    // Helper method to write input
    public static void input(List<String> list) throws FileNotFoundException {
        File file = new File("day 5/input.txt");
        Scanner scan = new Scanner(file);
        
        while(scan.hasNextLine()) {
            list.add(scan.nextLine());
        }

        scan.close();
    }
}
