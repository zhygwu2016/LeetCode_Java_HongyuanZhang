package leetcode.java;

// 381. Insert Delete GetRandom O(1) - Duplicates allowed
// https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class LC381_Insert_Delete_GetRandom_O1_Duplicates_allowed {

}

class RandomizedCollection {
    ArrayList<Integer> list;
    HashMap<Integer, Set<Integer>> idx;
    java.util.Random rand = new java.util.Random();
    /** Initialize your data structure here. */

    public RandomizedCollection() {
        list = new ArrayList<Integer>();
        idx = new HashMap<Integer, Set<Integer>>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if (!idx.containsKey(val)) idx.put(val, new LinkedHashSet<Integer>());
        idx.get(val).add(list.size());
        list.add(val);
        return idx.get(val).size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!idx.containsKey(val) || idx.get(val).size() == 0) return false;
        int remove_idx = idx.get(val).iterator().next();
        idx.get(val).remove(remove_idx);
        int last = list.get(list.size() - 1);
        list.set(remove_idx, last);
        idx.get(last).add(remove_idx);
        idx.get(last).remove(list.size() - 1);

        list.remove(list.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
