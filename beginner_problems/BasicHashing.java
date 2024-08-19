package beginner_problems;

import java.util.*;
import java.util.stream.Collectors;

public class BasicHashing {
    public int mostFrequentElement(int[] nums) {
         Map<Integer, Long> freqMap = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        return freqMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
    }

    public int secondMostFrequentElement(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(freqMap.entrySet());

        entryList.sort((e1, e2) -> {
            int freqCompare = e2.getValue().compareTo(e1.getValue());
            if (freqCompare != 0) {
                return freqCompare;
            }
            return e1.getKey().compareTo(e2.getKey());
        });

        if (entryList.size() < 2) {
            return -1;
        }

        int firstMostFreq = entryList.get(0).getValue();
        for (int i = 1; i < entryList.size(); i++) {
            if (entryList.get(i).getValue() < firstMostFreq) {
                return entryList.get(i).getKey();
            }
        }
        return -1;
    }

    public int sumHighestAndLowestFrequency(int[] nums) {
        Map<Integer, Long> freqMap = Arrays.stream(nums).boxed()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        Long highestFreq = freqMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getValue();

        Long smallestFreq = freqMap.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .get().getValue();

        return (int) (highestFreq + smallestFreq);
    }
}
