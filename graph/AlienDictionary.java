package graph;

import java.util.*;

public class AlienDictionary {
    public String findOrder(String[] dict) {

        Map<Character, Set<Character>> dag = new HashMap<>();
        /*
        Assumptions
        N = length of dictionary
        K = length of largest individual word
        L = number of unique words in the dictionary
        */


        // runs for O(N)
        for (String word: dict) {
            for (int i = 0; i < word.length(); i++) {
                dag.put(word.charAt(i), new HashSet<>());
            }
        }

        // runs for O(N * K)
        for (int i = 0; i < dict.length-1; i++) {
            String word_1 = dict[i];
            String word_2 = dict[i+1];
            int len = Math.min(word_1.length(), word_2.length());
            for (int j = 0; j < len; j++ ) {
                char c = word_1.charAt(j);
                char d = word_2.charAt(j);
                if (c != d) {
                    dag.get(c).add(d);
                    break;
                }
            }
        }

        // runs for O(L)
        Map<Character, Integer> indegree = new HashMap<>();
        for (Character c : dag.keySet()) {
            indegree.put(c, 0);
        }

        // runs for O(N * L)
        for (Map.Entry<Character, Set<Character>> entry: dag.entrySet()) {
            Set<Character> characterSet = entry.getValue();
            for (Character c : characterSet) {
                indegree.put(c, indegree.getOrDefault(c, 0)+1);
            }
        }

        Queue<Character> queue = new ArrayDeque<>();

        // runs for O(L)
        indegree.forEach((k, v) -> {
            if (v == 0) {
                queue.offer(k);
            }
        });

        StringBuilder sb = new StringBuilder();

        // runs for each O(L + L)
        while (!queue.isEmpty()) {
            Character node = queue.poll();
            sb.append(node);
            Set<Character> neighbors = dag.get(node);
            for (Character neighbour : neighbors) {
                int currentIndegree = indegree.get(neighbour);
                currentIndegree--;
                indegree.put(neighbour, currentIndegree);
                if (currentIndegree == 0) {
                    queue.offer(neighbour);
                }
            }

        }

        if (sb.toString().length() != indegree.size() || sb.toString().isEmpty()) {
            return "";
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        AlienDictionary solver = new AlienDictionary();
        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
        System.out.println(solver.findOrder(dict));
    }
}