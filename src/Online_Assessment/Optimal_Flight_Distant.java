package Online_Assessment;

// Optimal Flight Distant
// http://theleetcode.blogspot.com/2019/04/amazon-coding-approach-optimal-flight.html

import java.util.ArrayList;
import java.util.List;

public class Optimal_Flight_Distant {
    class PairInt {
        private int first;
        private int second;

        PairInt(){}

        PairInt(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public List<PairInt> optimalUtilization(int maxTravelDist, List<PairInt> forwardRouteLit, List<PairInt>returnRouteList) {

        int maxMiles = 0;
        List<PairInt> pairs = new ArrayList<PairInt>();

        for (int i = 0; i < forwardRouteLit.size(); i++) {

            PairInt base = forwardRouteLit.get(i);

            for (int j = 0; j < returnRouteList.size(); j++) {

                PairInt value = returnRouteList.get(j);

                int total = base.second + value.second;

                if (total <= maxTravelDist) {
                    if (total > maxMiles) {
                        maxMiles = total;
                        pairs = new ArrayList<>();
                        pairs.add(new PairInt(base.first, value.first));
                    } else if (total == maxMiles) {
                        pairs.add(new PairInt(base.first, value.first));
                    }
                }

            }
        }

        return pairs;
    }
}

