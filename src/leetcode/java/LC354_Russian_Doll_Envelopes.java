package leetcode.java;

import java.util.Arrays;
import java.util.Comparator;

// 354. Russian Doll Envelopes
// https://leetcode.com/problems/russian-doll-envelopes/

// https://leetcode.com/problems/russian-doll-envelopes/discuss/82763/Java-NLogN-Solution-with-Explanation

public class LC354_Russian_Doll_Envelopes {
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length == 0){
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>(){
            @Override
            public int compare(int[] a1, int[] a2){
                if(a1[0] == a2[0]){
                    return a2[1] - a1[1];
                }
                return a1[0] - a2[0];
            }

        });

        int[] dp = new int[envelopes.length];
        int len = 0;

        for (int[] env : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, env[1]);

            /* Arrays.binarySearch will return the negative result
             * when the target is not finding.
             * And the negative result stands for the index it is supposed to be,
             * but with a negative number.
             * More details, refer to the first answer in the discussion.
             */

            if(index < 0) index = -(index + 1);

            dp[index] = env[1];

            if(index == len) {
                len++;
            }
        }
        return len;
    }
}
