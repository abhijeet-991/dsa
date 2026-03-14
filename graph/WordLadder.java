package graph;

import java.util.*;

class WordLadder {
    private class WordMetadata {
        String word;
        Integer level;
        public WordMetadata(String word, Integer level) {
            this.word = word;
            this.level = level;
        }
    }
    public int wordLadderLength(String startWord, String targetWord, List<String> wordList) {

        Set<String> dictionary = new HashSet<>(wordList);
        if (!dictionary.contains(targetWord)) {
            return 0;
        }

        Queue<WordMetadata> queue = new LinkedList<>();
        queue.offer(new WordMetadata(startWord, 0));

        while (!queue.isEmpty()) {
            WordMetadata word = queue.poll();
            char[] currentWord = word.word.toCharArray();
            Integer level = word.level;
            for (int i = 0 ; i < currentWord.length; i++) {
                char currentlyTransformedIndexChar = currentWord[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    currentWord[i] = c;
                    String newWord = new String(currentWord);
                    if (dictionary.contains(newWord)) {
                        if (newWord.equals(targetWord)) {
                            return level+2;
                        }
                        queue.offer(new WordMetadata(newWord, level+1));
                        dictionary.remove(newWord);
                    }
                }
                currentWord[i] = currentlyTransformedIndexChar;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        int ans = wordLadder.wordLadderLength("der", "dfs", List.of("des","der","dfr","dgt","dfs"));
        System.out.println(ans);
    }
}