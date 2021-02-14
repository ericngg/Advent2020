import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = new ArrayList();
        input(list);

        // Part 1
        //System.out.println(sumYes(list));

        // Part 2
        System.out.println(everyoneYes(list));
    }

    // Part 1: Find every yes to a unique question
    // List without space (configure the input structure)
    // Time - O(N * M) Space - O(N)
    public static int sumYes(List<String> list) {
        int count = 0;

        for (int i = 0; i < list.size(); i++) {
            String ans = list.get(i);
            Set<Character> set = new HashSet<>();

            for (int j = 0; j < ans.length(); j++) {
                set.add(ans.charAt(j));
            }

            count += set.size();
        }

        return count;
    }

    // Part 2: Find all questions that were answer yes by every person
    // List with space (configure the input structure)

    // Probably a way to optimize it without so many for loops
    // I think Time O(N * M * S * T) ????  Space - O(N)
    // N - Iterating through every group,
    // M - Iterarting through every person
    // S - Iterating through every answer
    // T - Iterating through answers to see if everyone answered it
    public static int everyoneYes(List<String> list) {
        int count = 0;

        for (int i = 0; i < list.size(); i++) {
            String[] ans = list.get(i).split(" ");
            Map<Character, Integer> map = new HashMap<>();

            // Iterate through each person
            for (int j = 0; j < ans.length; j++) {
                String answers = ans[j];

                // Iterate through each answer
                for (int k = 0; k < answers.length(); k++) {
                    char letter = answers.charAt(k);
                    map.put(letter, map.getOrDefault(letter, 0) + 1);
                }

            }

            int people = ans.length;
            for (int score : map.values()) {
                if (score == people) {
                    count++;
                }
            }

        }

        return count;
    }

    // Helper method for input
    public static void input(List<String> list) throws FileNotFoundException {
        File file = new File("day 6/input.txt");
        Scanner scan = new Scanner(file);

        String cur = scan.nextLine();
        while (scan.hasNextLine()) {
            String current = scan.nextLine();
            
            if (current.isEmpty()) {
                list.add(cur.trim());
                cur = "";
            } else {
                cur += " " + current;
            }    
        }

        list.add(cur.trim());

        scan.close();
    }
}
