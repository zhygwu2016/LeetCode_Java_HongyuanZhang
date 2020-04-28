package leetcode.java;

// LC149. Max Points on a Line
// https://leetcode.com/problems/max-points-on-a-line/

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/max-points-on-a-line/discuss/47113/A-java-solution-with-notes
// 对于每个店，求其余点与之的斜率。斜率一样，在一条直线上。
// 为了让斜率进行比较：
// deltaX = x2 - x1, deltaY = y2 - y1
// 求deltaX deltaY最大公约数，除后是化简后，这样就能用做HashMap的key
// e.g. 斜率24/18, gcd是6，化简后 4/3，HashMap中的key: "4,3"
public class LC149_Max_Points_on_a_Line {
    public int maxPoints(int[][] points) {
        if (points == null) return 0;

        int solution = 0;

        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> map = new HashMap<>();
            int duplicate = 0;
            int max = 0;
            for (int j = i + 1; j < points.length; j++) {
                int deltaX = points[j][0] - points[i][0];
                int deltaY = points[j][1] - points[i][1];

                if (deltaX == 0 && deltaY == 0)
                {
                    duplicate++;
                    continue;
                }

                int gcd = gcd(deltaX, deltaY);
                int dX = deltaX / gcd;
                int dY = deltaY / gcd;

                map.put(dX + "," + dY, map.getOrDefault(dX + "," + dY, 0) + 1);
                max = Math.max(max, map.get(dX + "," + dY));
            }

            solution = Math.max(solution, max + duplicate + 1);
        }

        return solution;
    }

    public int gcd(int a, int b)
    {
        if (b == 0)
            return a;
        return gcd(b, a%b);
    }
}
