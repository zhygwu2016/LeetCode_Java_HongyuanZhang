package Online_Assessment;

import java.util.*;

public class PopularNFeatures {
    public ArrayList<String> popularfeatures(int numFeatures,
                                             int topFeatures,
                                             List<String> possibleFeatures,
                                             int numFeatureRequests,
                                             List<String> featureRequests)
    {
        // WRITE YOUR CODE HERE
        ArrayList<String> res = new ArrayList<>();
        if(numFeatures == 0 || numFeatureRequests == 0) return res;
        HashMap<String,Integer> map = new HashMap<>();
        //build a map to count number of appearence of features.
        for(int i = 0; i<numFeatures; i++){
            if(possibleFeatures.get(i) == " ") continue;
            map.put(possibleFeatures.get(i), 0);
        }

        //scan all feature requests, update number of feature apprearence in the map
        for(String featureRequest : featureRequests){
            String[] words = featureRequest.replaceAll("\\pP\\p{Punct}","").split(" ");
            Set<String> set = new HashSet<>();
            for(String word: words){
                if(!set.contains(word) && map.containsKey(word)){
                    map.put(word, map.get(word)+1);
                    set.add(word);
                }
            }
        }

        PriorityQueue<String> maxHeap = new PriorityQueue<>((a,b)->{
            if(map.get(a) == map.get(b)){
                return a.compareToIgnoreCase(b);
            }
            return map.get(b) - map.get(a);
        });

        for(int i = 0; i < numFeatures; i++){
            maxHeap.offer(possibleFeatures.get(i));
        }

        for(int i = 0; i< topFeatures; i++){
            if(maxHeap.isEmpty()) break;
            String cur = maxHeap.poll();
            if(map.get(cur) == 0) break;
            res.add(cur);
        }

        return res;
    }
}
