package Online_Assessment;

// https://leetcode.com/discuss/interview-question/373202

import java.util.*;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Optimal_Utilization {

    class PairInt {
        int first, second;
        public PairInt(){}
        public PairInt(int first, int second){
            this.first = first;
            this.second = second;
        }
    }

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<PairInt> optimalUtilization(int deviceCapacity,
                                            List<PairInt> foregroundAppList,
                                            List<PairInt> backgroundAppList)
    {
        // WRITE YOUR CODE HERE

        // build a HashMap, key is the same value of sum of two PairInt
        // (foregroundAppList and backgroundAppList),
        // value is list of IDs from foregroundAppList and backgroundAppList
        Map<Integer, List<PairInt>> map = new HashMap<>();

        // traverse the foregroundAppList and backgroundAppList, get the sum of two, and put the info we need into HashMap
        for (int i = 0; i < foregroundAppList.size(); i++) {
            for (int j = 0; j < backgroundAppList.size(); j++) {
                List<PairInt> sums = map.getOrDefault(foregroundAppList.get(i).second + backgroundAppList.get(j).second, new ArrayList<PairInt>());
                sums.add(new PairInt(foregroundAppList.get(i).first, backgroundAppList.get(j).first));
                map.put(foregroundAppList.get(i).second + backgroundAppList.get(j).second, sums);
            }
        }

        List<Integer> allSums = new ArrayList<>();
        for (Integer i : map.keySet()) {
            if (i < deviceCapacity) {
                allSums.add(i);
            } else if (i == deviceCapacity) {
                // if the key equals the deviceCapacity, return the value of the key
                return map.get(deviceCapacity);
            }
        }

        if (allSums.size() == 0) {
            // we cannot find any qualified results whose sum is less than the deviceCapacity
            return new ArrayList<>();
        }
        return map.get(Collections.max(allSums));
    }
    // METHOD SIGNATURE ENDS
}
