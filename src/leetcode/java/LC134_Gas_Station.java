package leetcode.java;

// 134. Gas Station
// https://leetcode.com/problems/gas-station/

public class LC134_Gas_Station {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;

        int sum = 0, remain = 0, pos = 0;

        for(int i = 0; i < len; i++){
            sum += gas[i] - cost[i];
            if(sum < 0){
                remain += sum;
                sum = 0;
                pos = i + 1;
            }
        }

        remain += sum;

        return remain >= 0 ? pos : -1;
    }
}

// 算法加强 Sliding Window

// 初始值 start = 0; end = 0;
// sum >= 0 ⇒ end++;
// sum < = ⇒ start--;
// 然而start初始值是0，减减之后想套圈回到len - 1，如何做到呢？
// Solution: start初始值设为len 这样减减之后就变成 len - 1
// 相应的，while条件也就变成了 while (end < start) {...}
class LC134_SlidingWindow {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int sum = 0; // 油量
        int start = len, end = 0;

        while (end < start) { // [start, len)
            if (sum >= 0) {
                sum += gas[end] - cost[end];
                end++;
            } else {
                start--;
                sum += gas[start] - cost[start];
            }
        }

        // 注意start == len的corner case
        return sum >= 0 ? ((start == len) ? 0 : start) : -1;
        // return sum >= 0 ? (start % len) : -1;
    }
}
