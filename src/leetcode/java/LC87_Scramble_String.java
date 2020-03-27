package leetcode.java;

// LC87. Scramble String
// https://leetcode.com/problems/scramble-string/

public class LC87_Scramble_String {
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (!isSame(s1, s2)) {
            return false;
        }
        return dfs(s1, s2);
    }

    private boolean dfs(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }

        if (s1.length() == 2 && s2.length() == 2
                && s1.charAt(0) == s2.charAt(1) && s1.charAt(1) == s2.charAt(0)) {
            return true;
        }

        for (int i = 0; i < s1.length() - 1; i++) {
            String s11 = s1.substring(0, i + 1);
            String s12 = s1.substring(i + 1, s1.length());
            String s21 = s2.substring(0, i + 1);
            String s22 = s2.substring(i + 1, s2.length());

            if (isSame(s11, s21) && isSame(s12, s22)
                    && dfs(s11, s21) && dfs(s12, s22)) {
                return true;
            }

            s21 = s2.substring(s2.length() - 1 - i, s2.length());
            s22 = s2.substring(0, s2.length() - 1 - i);

            if (isSame(s11, s21) && isSame(s12, s22)
                    && dfs(s11, s21) && dfs(s12, s22)) {
                return true;
            }
        }

        return false;
    }

    private boolean isSame(String s1, String s2) {
        int[] count = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            int index = s1.charAt(i) - 'a';
            count[index]++;

            index = s2.charAt(i) - 'a';
            count[index]--;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }
}

/*
https://leetcode.com/problems/scramble-string/discuss/29392/Share-my-4ms-c%2B%2B-recursive-solution
To the people who are unable to get why this code is exponential in run time have a look at this. I have written in layman terms

For string of size n take time to be T(n) for it
We are starting from i=1 and going till n-1
This line of code isScramble(s1.substr(0,i), s2.substr(0,i)) && isScramble(s1.substr(i), s2.substr(i)))
is T(i) + T(n-i)
and this line of code just below isScramble(s1.substr(0,i), s2.substr(len-i)) && isScramble(s1.substr(i), s2.substr(0,len-i))
is again T(i) + T(n-i)

So basically for T(n) we have:
T(n) = for i in 1 to n-1 : +2*(T(i) + T(n-i))
i.e.
T(n) = 2*(T(1) + T(n-1) + T(2) + T(n-2) + ... + T(n-1) + T(1)) ,
now we have two equal sequences T(1) to T(n-1) from left to right and T(n-1) to T(1) from right to left. So above equation can be written as
T(n) = 2 * 2(T(1) + T(2) + ...+ T(n-2) + T(n-1))
T(n) = 4 * (T(1) + T(2) + ...+ T(n-2) ) + 4 * T(n-1)

if we substitute n-1 in place of n above we get T(n-1) = 4 * (T(1) + T(2) + ...+ T(n-2) ) , using this in the above equation we get
T(n) = T(n-1) + 4 * T(n-1)
T(n) = 5 * T(n-1)

If we open this we get
T(n) = 5 * 5 * T(n-2) = 5 * 5 * 5 * T(n-3) = 5 * 5 * ... * 5 (n times)
so T(n) = O(5^n)
 */
