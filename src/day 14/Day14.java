import java.math.BigInteger;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day14 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        input(list);

        //System.out.println(partOne(list));

        System.out.println(partTwo(list));
    }

    // Solution for part 1
    public static long partOne(List<String> list) {
        Map<Integer, String> mem = new HashMap<>();
        String mask = "";

        for (int i = 0; i < list.size(); i++) {
            String current = list.get(i);
            if (current.contains("mask")) {
                mask = getMask(current);
                //System.out.println("mask is: " + mask);
            } else {
                String[] line = current.split(" = "); // ex mem[123] = 456, 0 - mem[123], 1 - 456
                int index = getIndex(line[0]);
                String binary = Integer.toBinaryString(Integer.parseInt(line[1]));

                mem.put(index, bitmask(mask, binary));
            }
        }

        return convertBinaryResult(mem);
    }

    public static String bitmask(String mask, String binary) {
        String result = "";
        int maskIndex = mask.length() - 1;
        for (int i = binary.length() - 1; i >= 0; i--) {
            if (mask.charAt(maskIndex) != 'X') {
                result = mask.charAt(maskIndex) + result;
            } else {
                result = binary.charAt(i) + result;
            }

            maskIndex--;
        }

        result = mask.substring(0, maskIndex + 1) + result;
        result = result.replace('X' , '0');

        return result;
    }

    // gets mask from input string value
    public static String getMask(String current) {
        String[] mask = current.split(" = ");

        return mask[1];
    }

    // gets memory address index from input string value
    public static int getIndex(String current) {
        String ind = current.substring(current.indexOf("[") + 1, current.indexOf("]"));

        return Integer.parseInt(ind);
    }

    // Converts the mem results into a sum for part 1
    public static long convertBinaryResult(Map<Integer, String> mem) {
        long result  = 0;
        for (String value : mem.values()) {
            result += new BigInteger(value, 2 ).longValue();
        }

        return result;
    }

    // Solution for part 2
    public static long partTwo(List<String> list) {
        Map<Long, Long> mem = new HashMap<>();
        String mask = "";

        for (int i = 0; i < list.size(); i++) {
            String current = list.get(i);
            if (current.contains("mask")) {
                mask = getMask(current);
            } else {
                String[] line = current.split(" = "); // ex mem[123] = 456, 0 - mem[123], 1 - 456
                int address = getIndex(line[0]);
                long value = Long.parseLong(line[1]);

                String addressBinary = Integer.toBinaryString(address);
                String addressMask = getAddressMask(mask, addressBinary);

                floatBit(addressMask, mem, value);

            }
        }


        return convertBinaryResult2(mem);
    }

    // Converts the mem results into the sum for part 2
    public static long convertBinaryResult2(Map<Long, Long> mem) {
        long result  = 0;
        for (Long value : mem.values()) {
            result += value;
        }

        return result;
    }

    // Recurses over float bits until all of them are gone for part 2
    public static void floatBit(String addressMask, Map<Long, Long> mem, long value) {
        if (!addressMask.contains("X")) {
            // add to mem
            mem.put(new BigInteger(addressMask, 2 ).longValue(), value);
            return;
        }

        String zero = addressMask.substring(0, addressMask.indexOf("X")) + "0" + addressMask.substring(addressMask.indexOf("X") + 1);
        floatBit(zero, mem, value);


        String one = addressMask.substring(0, addressMask.indexOf("X")) + "1" + addressMask.substring(addressMask.indexOf("X") + 1);
        floatBit(one, mem, value);
    }


    // Mask the address for part 2
    public static String getAddressMask(String mask, String binary) {
        String result = "";
        int maskIndex = mask.length() - 1;
        for (int i = binary.length() - 1; i >= 0; i--) {
            if (mask.charAt(maskIndex) != '0') {
                result = mask.charAt(maskIndex) + result;
            } else {
                result = binary.charAt(i) + result;
            }

            maskIndex--;
        }

        result = mask.substring(0, maskIndex + 1) + result;

        return result;
    }

    // Helper method for input
    public static void input(List<String> list) throws FileNotFoundException {
        File file = new File("src/day 14/input.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            list.add(scan.nextLine());
        }

        scan.close();
    }
}