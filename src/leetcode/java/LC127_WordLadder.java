package leetcode.java;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC127_WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(beginWord == null || endWord == null || wordList == null){
            return -1;
        }

        HashSet<String> wordSet = new HashSet<>();
        for(String word : wordList){
            wordSet.add(word);
        }

        if(!wordSet.contains(endWord)){
            return 0;
        }

        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        int minDis = 1;

        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                String cur = queue.poll();
                char cc[] = cur.toCharArray();
                for(int i = 0; i < cc.length; i++){
                    char temp = cc[i];
                    for(char c = 'a'; c <= 'z'; c++){
                        cc[i] = c;
                        String str = String.valueOf(cc);
                        if(c != temp && wordSet.contains(str)){
                            if(str.equals(endWord)) return minDis + 1;

                            queue.offer(str);
                            wordSet.remove(str);
                        }
                    }
                    cc[i] = temp;
                }
            }
            minDis++;
        }

        return 0;
    }
}

// Two directions solution
//public class Solution127 {
//    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//        if(beginWord == null || endWord == null || wordList == null){
//            return -1;
//        }
//
//        HashSet<String> wordSet = new HashSet<>();
//        for(String word : wordList){
//            wordSet.add(word);
//        }
//
//        if(!wordSet.contains(endWord)){
//            return 0;
//        }
//
//        HashSet<String> beginSet = new HashSet<>();
//        HashSet<String> endSet = new HashSet<>();
//        beginSet.add(beginWord);
//        endSet.add(endWord);
//
//        int minDis = 1;
//
//        while(!beginSet.isEmpty() && !endSet.isEmpty()){
//            if(beginSet.size() > endSet.size()){
//                HashSet<String> tmpQue = beginSet;
//                beginSet = endSet;
//                endSet = tmpQue;
//            }
//
//            HashSet<String> nextLevel = new HashSet<String>();
//            for(String cur : beginSet){
//                char cc[] = cur.toCharArray();
//                for(int i = 0; i < cc.length; i++){
//                    char temp = cc[i];
//                    for(char c = 'a'; c <= 'z'; c++){
//                        cc[i] = c;
//                        String str = String.valueOf(cc);
//                        if(endSet.contains(str)) return minDis + 1;
//
//                        if(c != temp && wordSet.contains(str)){
//                            nextLevel.add(str);
//                            wordSet.remove(str);
//                        }
//                    }
//                    cc[i] = temp;
//                }
//            }
//            beginSet = nextLevel;
//            minDis++;
//        }
//
//        return 0;
//    }
//}
