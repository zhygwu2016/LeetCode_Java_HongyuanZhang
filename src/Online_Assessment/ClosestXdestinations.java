package Online_Assessment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// import java.util.*;
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class ClosestXdestinations {

    class PairInt {
        int first, second;
        public PairInt(){}
        public PairInt(int first, int second){
            this.first = first;
            this.second = second;
        }
    }

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<PairInt> ClosestXdestinations(int numDestinations,
                                              List<PairInt> allLocations,
                                              int numDeliveries) {
        // WRITE YOUR CODE HERE
        ArrayList<PairInt> res = new ArrayList<PairInt>();

        // corner case
        if (numDestinations <= 0 || allLocations == null || numDeliveries <= 0) return res;
        if (numDeliveries >= numDestinations) return allLocations;

        PriorityQueue<PairInt> heap = new PriorityQueue<PairInt>(new Comparator<PairInt>() {
            @Override
            public int compare(PairInt left, PairInt right) {
                return getDistance(right) - getDistance(left);
            }
        });

        for (PairInt point : allLocations) {
            heap.add(point);
            if (heap.size() > numDeliveries) {
                heap.poll();
            }
        }

        while (numDeliveries-- > 0) {
            res.add(heap.poll());
        }

        return res;
    }

    // a helper function,  to get the square of distance of [0, 0] and the point
    private int getDistance(PairInt point) {
        return point.first * point.first + point.second * point.second;
    }
    // METHOD SIGNATURE ENDS
}
