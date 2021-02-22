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

        // Part 2 ONLY WORKS IF EVERY COL HAD A UNIQUE HOME, UNFORTUNATELY IT DOESNT.
        //List<List<Integer>> list = new ArrayList<>();
        //List<Integer> myTicket = new ArrayList<>();
        //Map<Integer, List<Integer>> map = new HashMap<>();
        //input2(list, myTicket, map);
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
                        for (int i = Integer.parseInt(nums[0]); i < Integer.parseInt(nums[1]) + 1; i++) {
                            set.add(i);
                        }
                    }
                }
            }
        }
    }

    public static void input2(List<List<Integer>> list, List<Integer> myTicket, Map<Integer, List<Integer>> map) throws FileNotFoundException {
        File file = new File("src/day 16/input.txt");
        Scanner scan = new Scanner(file);

        int listIndex = 0;

        // fields
        String line = "";
        while (scan.hasNext()) {
            line = scan.nextLine();

            if (line.isEmpty()) {
                break;
            } else {
                String[] fields = line.split(" ");
                list.add(new ArrayList<>());
                for (String value : fields) {
                    if (value.contains("-")) {
                        String[] num = value.split("-");
                        list.get(listIndex).add(Integer.parseInt(num[0]));
                        list.get(listIndex).add(Integer.parseInt(num[1]));
                    }
                }
                listIndex++;
            }
        }

        // my ticket
        scan.nextLine();
        line = scan.nextLine();
        String[] myNum = line.split(",");
        for (String num : myNum) {
            myTicket.add(Integer.parseInt(num));
        }

        //System.out.println(myTicket);


        // other tickets vertical
        scan.nextLine();
        scan.nextLine();

        List<String> valid = new ArrayList<>();

        // creates valid ticket list
        while (scan.hasNext()) {
            line = scan.nextLine();
            String[] nums = line.split(",");
            boolean val = true;

            for (int i = 0; i < nums.length; i++) {
                val = check(Integer.parseInt(nums[i]), list);

                if (!val) {
                    break;
                }
            }

            if (val) {
                valid.add(line);
            }
        }

        // create map and sort by column
        for (int i = 0; i < valid.get(0).split(",").length; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < valid.size(); i++) {
            String[] num = valid.get(i).split(",");
            for (int j = 0; j < num.length; j++) {
                map.get(j).add(Integer.parseInt(num[j]));
            }
        }

        // Map = columns of tickets

        // myticket = my ticket

        // list = fields

        Map<Integer, Integer> fieldMap = new HashMap<>();

        /*
        for (int i = 0; i < map.size(); i++) {
            List<Integer> current = map.get(i);
            matchField(fieldMap, current, list, i);
        }
         */

        //System.out.println(valid);
        //System.out.println("map: " + map);
        //System.out.println("fieldmap: " + fieldMap);
        // Key = field index, value = ticket index

        long count = 1;
        for (int i = 0; i < 6; i++) {
            count *= myTicket.get(fieldMap.get(i));
        }

        System.out.println(count);

    }

    public static void matchField(Map<Integer, Integer> fieldMap, List<Integer> current, List<List<Integer>> list, int index) {
        for (int i = 0; i < list.size(); i++) {
            int one = list.get(i).get(0);
            int two = list.get(i).get(1);
            int three = list.get(i).get(2);
            int four = list.get(i).get(3);
            boolean val = true;

            for (int j = 0; j < current.size(); j++) {
                int number = current.get(j);
                if (!((number >= one && number <= two) || (number >= three && number <= four))) {
                    val = false;
                    break;
                }
            }

            if (val && fieldMap.containsKey(i)) {
                System.out.println("index: " + index + " fits in " + i);
            }

            if (val && !fieldMap.containsKey(i)) {
                fieldMap.put(i, index);
                break;
            }
        }
    }

    public static boolean check(int num, List<List<Integer>> list) {
        for (int i = 0; i < list.size(); i++) {
            int one = list.get(i).get(0);
            int two = list.get(i).get(1);
            int three = list.get(i).get(2);
            int four = list.get(i).get(3);

            if (((num >= one && num <= two) || (num >= three && num <= four))) {
                return true;
            }
        }

        return false;
    }
}