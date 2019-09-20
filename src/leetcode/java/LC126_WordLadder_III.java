package leetcode.java;

import java.util.*;

// Word Ladder III (only output any one of shortest path)

public class LC126_WordLadder_III {
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList){
        // O(n)
        List<String> res = new LinkedList<>();

        HashSet<String> wordSet = new HashSet<>();
        for(String word: wordList){
            wordSet.add(word);
        }
        if(!wordSet.contains(endWord)){
            return res;
        }
        wordSet.remove(beginWord);

        Queue<String> queue = new LinkedList<String>();
        HashMap<String, String> graph= new HashMap<>();
        queue.offer(beginWord);
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                String cur = queue.poll();
                char cc[] = cur.toCharArray();
                for(int i = 0; i < cc.length; i++){
                    char temp = cc[i];
                    for(char c = 'a'; c <= 'z'; c++){
                        cc[i] = c;
                        String next = String.valueOf(cc);
                        if(c != temp && wordSet.contains(next)){
                            graph.put(next, cur);

                            if(next.equals(endWord)){
                                String curWord = endWord;
                                while(curWord != null){
                                    //while cur is not next of beginWord, do...
                                    res.add(0, curWord);
                                    curWord = graph.get(curWord);
                                }
                                return res;
                            }
                            queue.offer(next);
                            wordSet.remove(next);
                        }
                    }
                    cc[i] = temp;
                }

            }
        }
        return res;
    }

    public static void main(String[] args){
        LC126_WordLadder_III so = new LC126_WordLadder_III();
        String beginWord = "hot";
        String endWord = "dog";
        List<String> wordList =
                Arrays.asList("hot","dot","dog","lot","log","cog","hop","tot","hog");
        List<String> res = so.findLadders(beginWord, endWord, wordList);
        System.out.println(res);

        beginWord = "hit";
        endWord = "cog";
        wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        res = so.findLadders(beginWord, endWord, wordList);
        System.out.println(res);
    }
}
