import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        Map<String, String> map = new HashMap<>();
        input(map);
        System.out.println(partOne(map));

        // System.out.println(partTwo(map));
    }

    public static int partOne(Map<String, String> map) {
        List<String> seen = new ArrayList();
        int count = 0;
        Queue<String> q = new LinkedList();

        count += bagChecker(map, q, seen, "shiny gold bag");


        
        while (!q.isEmpty()) {
            String current = q.poll();
            count += bagChecker(map, q, seen, current.substring(0, current.length() - 1));
        }

        return count;
    }

    public static int bagChecker(Map<String, String> map, Queue<String> q, List<String> seen, String target) {
        int count = 0;

        for (String key : map.keySet()) {
            if (map.get(key).contains(target) && !seen.contains(key)) {
                count++;
                q.add(key);
                seen.add(key);
            }
        }

        return count;
    }

    // prob recursion go to bottom of bag and then return number of bags
    public static int partTwo(Map<String, String> map) {
        String shinyGold = map.get("shiny gold bags"); // 1 dark olive bags, 2 vibrant plum bags

        return countBags(map, shinyGold, 1) - 1;
    }

    public static int countBags(Map<String, String> map, String current, int num) {
        if (current.equals("no other bags")) {
            return num;
        }
        
        String[] bags = current.split(", ");

        // shiny gold -> 2 dark red (2 red)
        // dark red -> 2 dark orange (4 orange)
        // dark orange -> 2 dark yellow (8 yellow)
        // dark yellow -> 2 dark green (16 green)
        // dark green -> 2 dark blue (32 blue)
        // dark blue -> 2 dark violet (64 violet)
        // dark violet -> none

        int count = 0;

        System.out.println(Arrays.toString(bags));
        
        for (int i = 0; i < bags.length; i++) {
            int number = Integer.parseInt(bags[i].substring(0, bags[i].indexOf(" ")));
            String content = bags[i].substring(bags[i].indexOf(" ") + 1);
            if (content.charAt(content.length() - 1) != 's') {
                content += "s";
            }

            count += num * countBags(map, map.get(content), number);

            System.out.println(content + " is " + count);

        }
        
        return count;
    }

    public static void input(Map<String, String> map) throws FileNotFoundException {
        File file = new File("day 7/test.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            String[] bags = scan.nextLine().split(" contain ");
            map.put(bags[0], bags[1].replace(".", ""));
        }

        scan.close();
    }

}
