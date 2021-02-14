import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> list = new ArrayList();
        input(list);
        System.out.println("2 sum:" + twoSum(list, 2020));
        System.out.println("3 sum:" + threeSum(list));
        
    }


    // For 2 numbers to equal the target
    // Time - O(N + Nlog(N)) Space - O(N)
    public static int twoSum(List<Integer> list, int target) {
        Collections.sort(list);
        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            if (list.get(left) + list.get(right) < target) {
                left++;
            } else if (list.get(left) + list.get(right) > target) {
                right--;
            } else {
                return list.get(left) * list.get(right);
            }
        }

        return -1;
    }

    // For 3 numbers to equal the target
    // Time - O(N^2 + N^2LogN) <- ?  Space - O(1)
    public static int threeSum(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int target = 2020 - list.get(i);

            int twoSum = twoSum(list, target);

            if (twoSum != -1) {
                return twoSum * list.get(i);
            }
        }

        return -1;
    }

    // Helper method to iterate through input txt file
    public static void input(List<Integer> list) throws FileNotFoundException {
        File input = new File("day 1/input.txt");
        Scanner scan = new Scanner(input);

        while(scan.hasNextLine()) {
            list.add(Integer.parseInt(scan.nextLine()));
        }

        scan.close();
    }
}