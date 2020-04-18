package leetcode.java;

// LC937. Reorder Data in Log Files
// https://leetcode.com/problems/reorder-data-in-log-files/

import java.util.Arrays;
import java.util.Comparator;

public class LC937_Reorder_Data_in_Log_Files {
    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> myComp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String[] split1 = s1.split(" ", 2);
                String[] split2 = s2.split(" ", 2);
                boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
                boolean isDigit2 = Character.isDigit(split2[1].charAt(0));

                if(!isDigit1 && !isDigit2) {
                    int comp = split1[1].compareTo(split2[1]);
                    if(comp != 0) {
                        return comp;
                    }
                    return split1[0].compareTo(split2[0]);
                }
                return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
            }
        };

        Arrays.sort(logs, myComp);
        return logs;
    }
}
