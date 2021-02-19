import java.util.*;


public class Day15 {
    public static void main(String[] args) {
        Map<Long, Long> map = new HashMap<>();
        input(map, new long[]{20L, 0L, 1L, 11L, 6L, 3L});

        System.out.println(solution(map));

    }

    public static long solution(Map<Long, Long> map) {
        long stop = 30000000;
        long last = 0;
        long lastIndex = map.size() + 1;

        for (long i = lastIndex + 1; i <= stop; i++) {
            if (map.containsKey(last)) {
                long tempLast = lastIndex - map.get(last);
                map.put(last, lastIndex);
                last = tempLast;
            } else {
                map.put(last, lastIndex);
                last = 0;
            }

            lastIndex = i;
        }

        return last;
    }

    public static void input(Map<Long, Long> map, long[] arr) {
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], (long) (i + 1));
        }
    }
}