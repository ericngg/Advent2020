import java.math.BigInteger;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        List<BigInteger> list = new ArrayList();
        input(list);
        //System.out.println(partOne(list));

        // part 1 invalid number is 21806024 for part 2
        System.out.println(partTwo(list, new BigInteger("21806024")));
    }

    // Find the first invalid number
    // TODO: should learn what the time and space complexity is
    public static BigInteger partOne(List<BigInteger> list) {
        List<BigInteger> preamble = new ArrayList();
        int preambleSize = 25;

        init(list, preamble, preambleSize);


        for (int i = preambleSize; i < list.size(); i++) {
            BigInteger current = list.get(i);
            boolean check = twoSumCheck(preamble, current); // T- O(N), S- O(N)

            if (!check) {
                return list.get(i);
            }

            preamble.remove(0);
            preamble.add(current);
        }

        return new BigInteger("-1");
    }

    // Two sum but the array is not sorted and uses integers that are very big, so BigInteger is needed.
    // Part 1 solution Time - O(N) Space - O(N)
    public static boolean twoSumCheck(List<BigInteger> preamble, BigInteger target) {
        Map<BigInteger, BigInteger> map = new HashMap<>();

        for (int i = 0; i < preamble.size(); i++) {
            BigInteger num = target.subtract(preamble.get(i));

            if (map.keySet().contains(num)) {
                return true;
            } else {
                map.put(preamble.get(i), num);
            }
        }

        System.out.println("No match for " + target);
        return false;
    }

    // initializes the preamble for part 1
    public static void init(List<BigInteger> list, List<BigInteger> preamble, int size) {
        int index = 0;
        while (preamble.size() < size) {
            preamble.add(list.get(index));
            index++;
        }
    }

    // Part 2: finds the section where it equals to the invalid number
    public static BigInteger partTwo(List<BigInteger> list, BigInteger target) {
        for (int i = 0; i < list.size(); i++) {
            int currentIndex = i;
            BigInteger count = new BigInteger("0");

            while (currentIndex < list.size() && count.compareTo(target) == -1) {
                count = count.add(list.get(currentIndex));
                currentIndex++;
            }

            if(count.compareTo(target) == 0) {
                return findResult(list, i, currentIndex);
            }
        }

        return new BigInteger("-1");
    }

    // Part 2 to find the min and max of the section
    public static BigInteger findResult(List<BigInteger> list, int start, int end) {
        BigInteger min = list.get(start);
        BigInteger max = list.get(start + 1);

        for (int i = start + 1; i < end; i++) {
            BigInteger num = list.get(i);

            if (num.compareTo(min) == -1) { // if num is less than min
                min = num;
            } else if (num.compareTo(max) == 1) { // if num is greater than max
                max = num;
            }
        }

        return min.add(max);
    }

    // Helper method for input
    public static void input(List<BigInteger> list) throws FileNotFoundException {
        File file = new File("src/day 9/input.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            list.add(new BigInteger(scan.nextLine()));
        }

        scan.close();
    }
}
