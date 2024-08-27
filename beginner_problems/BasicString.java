package beginner_problems;

import java.util.*;
import java.util.stream.Collectors;

public class BasicString {
    public Vector<Character> reverseString(Vector<Character> s) {
        int startIndex = 0;
        int endIndex = s.size()-1;
        while(startIndex <= endIndex) {
            Character temp = s.get(startIndex);
            s.set(startIndex, s.get(endIndex));
            s.set(endIndex, temp);
            startIndex++;
            endIndex--;

        }
        return s;
    }

    public boolean palindromeCheck(String s) {
        return helper(s, 0, s.length()-1);
    }
    public boolean helper(String s, int start, int end) {
        if (start >= end) {
            return true;
        }
        char c1 = s.charAt(start);
        char c2 = s.charAt(end);
        if (c1 != c2) {
            return false;
        }
        return helper(s, start + 1, end - 1);
    }

    public String largeOddNum(String s) {
        int rightEleIndex = -1;
        for (int i = s.length()-1; i >= 0; i--) {
            int ele = s.charAt(i) - '0';
            if ((ele & 1) == 1) {
                rightEleIndex = i;
                break;
            }
        }

        if (rightEleIndex == -1) {
            return "";
        }

        int i = 0;
        while(i <= rightEleIndex && s.charAt(i) == '0') i++;

        return s.substring(i, rightEleIndex+1);
    }

    public String longestCommonPrefix(String[] str) {
        int minLengthOfElement = Arrays.stream(str)
                .mapToInt(String::length)
                .min()
                .orElse(0);

        String s = str[0];
        StringBuilder ans = new StringBuilder();
        for (int charIdx = 0; charIdx < minLengthOfElement; charIdx++) {
            char c = s.charAt(charIdx);
            for (String string : str) {
                char toCompare = string.charAt(charIdx);
                if (toCompare != c) {
                    return ans.toString();
                }
            }
            ans.append(c);
        }
        return ans.toString();
    }

    public boolean isomorphicString(String s, String t) {
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Boolean> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character cs = s.charAt(i); //take out both characters from map
            Character ct = t.charAt(i);

            if (map1.containsKey(cs)) { //check if a key is being inserted first time, or it was already there
                if (map1.get(cs) != ct) { // if it was already there check if it is with the char at same position of 2nd string
                    return false; // if the char is different that means it's not ordered
                }
                // if a key is getting inserted for the first time
            } else {
                // first check if the char that we are trying to map it with is not already used
                if (map2.containsKey(t.charAt(i))) {
                    return false;
                } else {
                    // if it is not used put both mappings inside 1st map and mark the character in t as true since it is used
                    map2.put(t.charAt(i), true);
                    map1.put(s.charAt(i), t.charAt(i));
                }
            }
        }
        return true;
    }


    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        String combined = s + s;

        for (int i = 0; i < combined.length() - goal.length() + 1; i++) {
            int j;
            for (j = 0; j < goal.length(); j++) {
                if (combined.charAt(i + j) != goal.charAt(j)) {
                    break;
                }
            }
            if (j == goal.length()) {
                return true;
            }
        }

        return false;
    }

    public boolean anagramStrings(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i< s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            map.put(c, map.get(c) - 1);
            if (map.get(c) < 0) {
                return false;
            }
        }
        return true;
    }

    public List<Character> frequencySort(String s) {
        Map<Character, Integer> map = new TreeMap<>();
        for (Character c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        List<Integer> integers = map.values().stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        List<Character> ans = new ArrayList<>();
        for (Integer i : integers)
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue().equals(i) && !entry.getValue().equals(-1)) {
                    ans.add(entry.getKey());
                    map.put(entry.getKey(), -1);
                }
            }
        return ans;
    }
}
