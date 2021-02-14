import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = new ArrayList<String>();
        input(list);

        System.out.println(validPassports(list));
        
    }

    // Count each valid passport
    // split by space and check if there are 8
    // if 8 = valid
    // else  if it is 7, check if cid is missing
    // if cid missing = valid else false
    // everything under 7 false
    public static int validPassports(List<String> list) {
        int count = 0;

        for (int i = 0; i < list.size(); i++) {
            String[] passport = list.get(i).split(" ");
            if (passport.length == 8 || (passport.length == 7 && list.get(i).contains("cid") == false)) {
                if (validFields(passport)) {
                    count++;
                }
            }
        }

        return count;
    }

    public static boolean validFields(String[] passport) {
        List<String> eyeColor = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

        for (int i = 0; i < passport.length; i++) {
            String[] field = passport[i].split(":");
            String name = field[0];
            String value = field[1];

            if (name.equals("byr")) {
                int val = Integer.parseInt(value);

                if (val < 1920 || val > 2002) {
                    return false;
                }
            }

            if (name.equals("iyr")) {
                int val = Integer.parseInt(value);

                if (val < 2010 || val > 2020) {
                    return false;
                }
            }

            if (name.equals("eyr")) {
                int val = Integer.parseInt(value);

                if (val < 2020 || val > 2030) {
                    return false;
                }
            }

            if (name.equals("hgt")) {
                String measurement = value.substring(value.length() - 2);

                if (!(measurement.equals("in") || measurement.equals("cm"))) {
                    return false;
                }

                if (measurement.equals("in")) {
                    int val = Integer.parseInt(value.substring(0, value.length() - 2));
                    // 59 - 76
                    if (val < 59 || val > 76) {
                        return false;
                    }
                } else if (measurement.equals("cm")) {
                    int val = Integer.parseInt(value.substring(0, value.length() - 2));
                    // 150 - 193
                    if (val < 150 || val > 193) {
                        return false;
                    }
                }
            }

            if (name.equals("hcl")) {
                if (value.length() != 7) {
                    return false;
                } else if (value.charAt(0) != '#') {
                    return false;
                } else {
                    List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
                    String color = value.substring(1);
                    for (int j = 0; j < color.length(); j++) {
                        char index = color.charAt(j);
                        if (!chars.contains(index)) {
                            return false;
                        }
                    }
                }
            }

            if (name.equals("ecl") && !eyeColor.contains(value)) {
                return false;
            }

            if (name.equals("pid") && (value.length() != 9 && !value.matches("[0-9]+"))) {
                return false;
            }

        }

        return true;
    }

    public static void input(List<String> list) throws FileNotFoundException {
        File file = new File("day 4/input.txt");
        Scanner scan = new Scanner(file);
        String line = "";
        
        while(scan.hasNextLine()) {
            String current = scan.nextLine();

            if (current.isEmpty()) {
                list.add(line);
                line = "";
            } else if (line.isEmpty()) {
                line += current;
            } else {
                line += " " + current;
            }
        }

        list.add(line);

        scan.close();
    }
}
