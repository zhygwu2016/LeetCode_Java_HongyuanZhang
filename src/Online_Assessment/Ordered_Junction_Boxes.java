package Online_Assessment;

// 类似：
// LC937. Reorder Data in Log Files
// https://leetcode.com/problems/reorder-data-in-log-files/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// import java.util.*;
// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Ordered_Junction_Boxes {

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<String> orderedJunctionBoxes(int numberOfBoxes, List<String> boxList)
    {
        // WRITE YOUR CODE HERE
        List<String> res = new ArrayList<>();

        if (boxList == null || boxList.size() == 0) return res;

        List<String> wordList = new ArrayList<>();
        List<String> numberList = new ArrayList<>();

        // traverse the boxList, put the old junction boxes into wordList,
        // put the uprated boxes into numberList.
        // we need sort the old box list
        for (String s : boxList) {
            if (isNumber(s)) {
                numberList.add(s);
            } else {
                wordList.add(s);
            }
        }

        // build a customized Comparator function
        // so that we can sort the old junction boxes based on the requirements
        Collections.sort(wordList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] words1 = o1.split(" ");
                String[] words2 = o2.split(" ");

                // initial indexes of two String array is 1
                // we sort two String by comparing the second (or more) word in the String
                int i = 1, j = 1;

                while (i < words1.length && j < words2.length) {
                    String w1 = words1[i];
                    String w2 = words2[j];

                    if (w1.compareTo(w2) == 0) {
                        // if w1 equals w2, move to the next index for further comparation
                        i++;
                        j++;
                    } else {
                        //
                        return w1.compareTo(w2);
                    }
                }

                // if two junction box have exact same words except identifier,
                // we compare them based on the identifier
                if (i == words1.length && j == words2.length) {
                    return words1[0].compareTo(words2[0]);
                } else if (i == words1.length) { // when the first box has less words
                    return -1;
                } else { // when the second box has less words
                    return 1;
                }
            }
        });

        // first, add sorted old box list into the result
        // then add the new box list, whose order doesn't change
        res.addAll(wordList);
        res.addAll(numberList);

        return res;
    }

    private boolean isNumber(String s) {
        String[] words = s.split(" ");
        return words[1].charAt(0) >= '0' && words[1].charAt(0) <= '9';
    }
    // METHOD SIGNATURE ENDS
}
