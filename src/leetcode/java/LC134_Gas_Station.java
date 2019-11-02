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
