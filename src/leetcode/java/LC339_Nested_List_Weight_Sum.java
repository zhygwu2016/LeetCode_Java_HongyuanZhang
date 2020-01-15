package leetcode.java;

import java.util.LinkedList;

public class LC339_Nested_List_Weight_Sum {
}

/*
public class LC339_Nested_List_Weight_Sum {
    public int depthSum(List<NestedInteger> nestedList){
        int sum = 0, level = 1;
        Queue<NestedInteger> queue = new LinkedList<>();
        for(NestedInteger ni : nestedList){
            queue.offer(ni);
        }

        while(!queue.isEmpty()){
            int levelSum = 0, size = queue.size();
            while(size-- > 0){
                NestedInteger cur = queue.poll();
                if(cur.isInteger()){
                    levelSum += cur.getInteger();
                }else{
                    for (NestedInteger ni : cur.getList()){
                        queue.offer(ni);
                    }
                }
            }
            sum += levelSum * level++;
        }
        return sum;
    }
}

 */
