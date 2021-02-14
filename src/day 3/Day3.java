import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = new ArrayList();
        input(list);

        BigInteger product = new BigInteger("" + (trajectory(list, 1, 1) * trajectory(list, 3, 1) * trajectory(list, 5, 1) * trajectory(list, 7, 1)));
        product = product.multiply(new BigInteger("" + trajectory(list, 1, 2)));
        
        System.out.println(product);
    }

    // Keep count of number of trees that we cross
    // Iterate through the list ->
    // When we reach to the end of a row, go to next row and continue
    // When we reach to the end of the list, we know we finished
    // Ex. Row length is 11 and column length is 11. if placement is higher than row length, then (x % 11)
    // for right-3 and down-1, (0,0), (3, 1), (6,2), (9,3), (12,4) <- (1,4)
    // Return when we end at the end of the list (list.size() - 1)
    public static int trajectory(List<String> list, int right, int down) {
        int count = 0;
        int x = 0;
        int y = 0;

        int row_length = list.get(0).length(); // 31

        while (y < list.size() - 1) {
            x = x + right;
            y = y + down;

            // if x goes over row length, start back at beginning
            if (x >= row_length) {
                x = x % row_length;
            }

            // current position
            char pos = list.get(y).charAt(x);

            // checks to see if it is # or .
            if (pos == '#') {
                count++;
            }

        }

        return count;
    }

    // Time - O(N) Space - O(1)

    // Iterate through input txt file and make an arraylist
    public static void input(List<String> list) throws FileNotFoundException {
        File file = new File("day 3/input.txt");
        Scanner scan = new Scanner(file);

        while(scan.hasNextLine()) {
            list.add(scan.nextLine());
        }

        scan.close();
    }
}
