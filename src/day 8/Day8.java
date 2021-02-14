import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = new ArrayList();

        input(list);

        //System.out.println(partOne(list));
        System.out.println(partTwo(list));
    }

    // Keep track of indexes seen, if index was seen already, then there is a loop
    public static int partOne(List<String> list) {
        List<Integer> seen = new ArrayList<>();
        int acc = 0;
        boolean on = true;
        int index = 0;

        while (on) {
            String input[] = list.get(index).split(" ");
            String command = input[0];
            String number = input[1];

            seen.add(index);

            if (command.equals("jmp")) { // jmp
                index += Integer.parseInt(number);
            } else if (command.equals("acc")) { // acc
                acc += Integer.parseInt(number);
                index++;
            } else { // nop
                index++;
            }

            if (seen.contains(index)) {
                on = false;
            }
        }

        return acc;
    }

    public static int partTwo(List<String> list) {
        List<Integer> seen = new ArrayList<>();
        int acc = 0;
        int index = 0;

        List<Integer> tempSeen = new ArrayList<>();
        int tempAcc = 0;
        int tempIndex = 0;

        boolean tempMode = false;

        while (index < list.size()) {
            String input[] = list.get(index).split(" ");
            String command = input[0];
            String number = input[1];

            seen.add(index);

            if (!tempMode && (command.equals("jmp") || command.equals("nop")) && !list.get(index).equals("nop +0")) {
                tempAcc = acc;
                tempIndex = index;
                tempSeen = new ArrayList<>(seen);
                tempMode = true;

                if (command.equals("jmp")) {
                    command = "nop";
                } else {
                    command = "jmp";
                }
            }

            if (command.equals("jmp")) { // jmp
                index += Integer.parseInt(number);
            } else if (command.equals("acc")) { // acc
                acc += Integer.parseInt(number);
                index++;
            } else { // nop
                index++;
            }

            if (seen.contains(index)) {
                tempMode = false;
                acc = tempAcc;
                index = tempIndex;
                seen = new ArrayList<>(tempSeen);

                String tempInput[] = list.get(index).split(" ");
                String tempCommand = tempInput[0];
                String tempNumber = tempInput[1];

                if (tempCommand.equals("jmp")) { // jmp
                    index += Integer.parseInt(tempNumber);
                } else if (tempCommand.equals("acc")) { // acc
                    acc += Integer.parseInt(tempNumber);
                    index++;
                } else { // nop
                    index++;
                }
            }

        }

        return acc;
    }

    public static void input(List<String> list) throws FileNotFoundException {
        File file = new File("src/day 8/input.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            list.add(scan.nextLine());
        }

        scan.close();
    }
}
