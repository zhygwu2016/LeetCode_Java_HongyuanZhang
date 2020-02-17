package leetcode.java;

// LC207. Course Schedule
// https://leetcode.com/problems/course-schedule/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC207_Course_Schedule {
    private enum Status{
        INITIAL,
        PROCESSING,
        DONE;
    }

    private static class V{
        public int label;
        public List<Integer> nexts;
        public Status status;
        public V(int label) {
            this.label = label;
            this.nexts = new ArrayList<Integer>();
            status = Status.INITIAL;
        }

    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        V[] array = new V[numCourses];
        for (int i = 0; i < numCourses; i++) {
            array[i] = new V(i);
        }

        int row = prerequisites.length;
        for (int i = 0; i < row; i++) {
            int prev = prerequisites[i][1], next = prerequisites[i][0];
            array[prev].nexts.add(next);
        }
        for (int i = 0; i < numCourses; i++) {
            if (isCycled(array[i], array)) {
                return false;
            }
        }

        return true;
    }

    public boolean isCycled(V source, V[] array){
        if (source.status == Status.DONE) return false; // pruning
        if (source.status == Status.PROCESSING) return true; // contains cycle

        source.status = Status.PROCESSING;
        for (int i : source.nexts) {
            if (isCycled(array[i], array)) {
                return true;
            }
        }

        source.status = Status.DONE;
        return false;
    }
}

class LC207_BFS {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        Queue queue = new LinkedList();
        int count=0;

        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();

        for(int i=0; i<prerequisites.length;i++){
            degree[prerequisites[i][1]]++;
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        for(int i=0; i<degree.length;i++){
            if(degree[i] == 0){
                queue.add(i);
                count++;
            }
        }

        while(queue.size() != 0){
            int course = (int)queue.poll();
            for(int i=0; i<graph[course].size();i++){
                int pointer = (int)graph[course].get(i);
                degree[pointer]--;
                if(degree[pointer] == 0){
                    queue.add(pointer);
                    count++;
                }
            }
        }
        if(count == numCourses)
            return true;
        else
            return false;
    }
}
