package graph;

import java.util.*;
import java.util.stream.Collectors;

public class WordLadderII {

    public List<List<String>> findSequences(String beginWord, String endWord, List<String> wordList) {
        Set<String> dictionary = new HashSet<>(wordList); // O(N*L) where N is the length of wordList and L is the length of an individual string inside that list

        if (!dictionary.contains(endWord)) { // O
            return new ArrayList<>();
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Map<String, List<String>> parentMap = new HashMap<>();
        boolean isShortestPathFound = false;

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<String> wordsToRemove = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                char[] wordArray = word.toCharArray();
                for (int j = 0; j < wordArray.length; j++) {
                    char backUpWordCurrentlyBeingModded = wordArray[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordArray[j] = c;
                        String newString = String.valueOf(wordArray);
                        if (dictionary.contains(newString)) {
                            if (!parentMap.containsKey(newString)) {
                                queue.offer(newString);
                            }
                            List<String> alreadyAddedParents = parentMap.getOrDefault(newString, new ArrayList<>());
                            alreadyAddedParents.add(word);
                            parentMap.put(newString, alreadyAddedParents);
                            wordsToRemove.add(newString);
                            if (newString.equals(endWord)) {
                                isShortestPathFound = true;
                            }
                        }
                    }
                    wordArray[j] = backUpWordCurrentlyBeingModded;
                }
            }
            if (isShortestPathFound) {
                break;
            }
            for (String word : wordsToRemove) {
                dictionary.remove(word);
            }
            wordsToRemove.clear();
        }

        List<List<String>> ans = new ArrayList<>();

        List<String> shortestPaths = new ArrayList<>();
        dfs(parentMap, ans, beginWord, endWord, shortestPaths);

        return ans;
    }

    private void dfs(Map<String, List<String>> parentMap, List<List<String>> ans, String startWord, String endWord, List<String> shortestPaths) {
        if (endWord.equals(startWord)) {
            shortestPaths.add(startWord);
            List<String> ansList = new ArrayList<>(shortestPaths.stream().toList());
            Collections.reverse(ansList);
            ans.add(ansList);
            shortestPaths.remove(startWord);
            return;
        }

        shortestPaths.add(endWord);
        List<String> parents = parentMap.get(endWord);

        if (parents != null && !parents.isEmpty()) {
            for (String parent : parents) {
                dfs(parentMap, ans, startWord, parent, shortestPaths);
            }
        }

        shortestPaths.remove(endWord);
    }

    public static void main(String[] args) {
        WordLadderII solver = new WordLadderII();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        List<List<String>> paths = solver.findSequences(beginWord, endWord, wordList);
        System.out.println(paths);
        /* * EXPECTED OUTPUT:
         * [
         * ["hit", "hot", "dot", "dog", "cog"],
         * ["hit", "hot", "lot", "log", "cog"]
         * ]
         * * Note: The order of the lists or the words inside doesn't usually matter
         * as long as all unique shortest paths of length 5 are present.
         */
    }
}