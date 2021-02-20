import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Day16 {
    public static void main(String[] args) throws FileNotFoundException {
        // Part 1
        //Set<Integer> set = new HashSet<>();
        //List<Integer> list = new ArrayList<>();
        //input(set, list);
        //System.out.println(partOne(set, list));

        // false true true
        // true  true true
        // false  false true
    }

    public static int partOne(Set<Integer> set, List<Integer> list) {
        int count = 0;

        for (int i = 0; i < list.size(); i++) {
            if (!set.contains(list.get(i))) {
                count += list.get(i);
            }
        }

        return count;
    }

    public static void input(Set<Integer> set, List<Integer> list) throws FileNotFoundException {
        File file = new File("src/day 16/input.txt");
        Scanner scan = new Scanner(file);
        boolean tickets = false;

        while (scan.hasNext()) {
            String line = scan.nextLine();

            if (line.contains("nearby tickets")) {
                tickets = true;
                line = scan.nextLine();
            }

            if (tickets) {
                // tickets
                String[] ticket = line.split(",");
                for (String val : ticket) {
                    list.add(Integer.parseInt(val));
                }
            } else {
                // not tickets
                String[] valids = line.split(" ");

                for (String value : valids) {
                    if (value.contains("-")) {
                        String[] nums = value.split("-");
                        for (int i  = Integer.parseInt(nums[0]); i < Integer.parseInt(nums[1]) + 1; i++) {
                            set.add(i);
                        }
                    }
                }
            }
        }
    }


}