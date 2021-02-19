import java.util.*;


public class Day15 {
    public static void main(String[] args) {
        Map<Long, Long> map = new HashMap<>();
        input(map, new long[]{Long.valueOf(20),
                              Long.valueOf(0),
                              Long.valueOf(1),
                              Long.valueOf(11),
                              Long.valueOf(6),
                              Long.valueOf(3)});

        System.out.println(solution(map));

    }

    public static long solution(Map<Long, Long> map) {
        long stop = 30000000;
        long last = 0;
        long lastIndex = map.size() + 1;

        for (long i = lastIndex + 1; i <= stop; i++) {
            if (map.keySet().contains(last)) {
                long tempLast = lastIndex - map.get(last);
                map.put(last, lastIndex);

                last = tempLast;
                lastIndex = i;
            } else {
                map.put(last, lastIndex);

                last = 0;
                lastIndex = i;
            }
        }

        return last;
    }

    public static void input(Map<Long, Long> map, long[] arr) {
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], Long.valueOf(i + 1));
        }
    }
}