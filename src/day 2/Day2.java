
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = new ArrayList();
        input(list);
        
        System.out.println("number of pass: " + validPassword(list));
    }

    // break down password into
    // min,max, letter, and password

    // "1-3 a: abcde"
    // space split into "1-3", "a:", "abcde"
    // dash split "1-3" into 1 and 3

    // count the number of letters in the password
        
    // have a count and then return
    public static int validPassword(List<String> list) {
        int count = 0;

        for (int i = 0; i < list.size(); i++) {
            int min = 0;
            int max = 0;
            char letter = 0;
            String password = "";

            String[] spaceSplit = list.get(i).split(" "); // "1-3" "a:" "abcde"
            // min and max
            String[] nums = spaceSplit[0].split("-");
            min = Integer.parseInt(nums[0]);
            max = Integer.parseInt(nums[1]);
            // letter
            letter = spaceSplit[1].charAt(0);
            // password
            password = spaceSplit[2];

            // Change depending on requirements for a valid password
            if (isValidPassword(min, max, letter, password)) {
                count++;
            }

        }
        return count;
    }

    // Checks to see if password is valid part 1 requirement
    // Time - O(N) Space - O(1)
    public static boolean isValidPassword(int min, int max, char letter, String password) {
        int letterCount = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == letter) {
                letterCount++;
            }

            if (letterCount > max) {
                return false;
            }
        }

        if (letterCount < min) {
            return false;
        } else {
            return true;
        }
    }

    // Checks to see if password is valid part 2 requirement
    // Time - O(N) Space - O(1)
    public static boolean isValidPassword2(int ind1, int ind2, char letter, String password) {
        if (password.charAt(ind1 - 1) == letter && password.charAt(ind2 - 1) != letter) {
            return true;
        } else if (password.charAt(ind1 - 1) != letter && password.charAt(ind2 - 1) == letter) {
            return true;
        } else {
            return false;
        }
    }

    // Helper method to iterate through input txt file
    public static void input(List<String> list) throws FileNotFoundException {
        File input = new File("day 2/input.txt");
        Scanner scan = new Scanner(input);

        while(scan.hasNextLine()) {
            list.add(scan.nextLine());
        }

        scan.close();
    }
}
