package leetcode.java;

// 210. Course Schedule II
// https://leetcode.com/problems/course-schedule-ii/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// DFS
public class LC210_Course_Schedule_II {
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

    private int curLab = 0;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        V[] array = new V[numCourses];
        for (int i = 0; i < numCourses; i++) {
            array[i] = new V(i);
        }

        int row = prerequisites.length;
        for (int i = 0; i < row; i++) {
            int prev = prerequisites[i][1], next = prerequisites[i][0];
            array[prev].nexts.add(next);
        }

        int[] res = new int[numCourses];
        curLab = numCourses - 1;

        for (int i = 0; i < numCourses; i++) {
            if (isCycled(array[i], array, res)) {
                return new int[0];
            }
        }

        return res;
    }

    public boolean isCycled(V source, V[] array, int[] res){
        if (source.status == Status.DONE) return false; // pruning
        if (source.status == Status.PROCESSING) return true; // contains cycle

        source.status = Status.PROCESSING;
        for (int i : source.nexts) {
            if (isCycled(array[i], array, res)) {
                return true;
            }
        }

        source.status = Status.DONE;
        res[curLab--] = source.label;
        return false;
    }
}


// BFS
class LC210_BFS {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        Queue queue = new LinkedList<>();

        int[] res = new int[numCourses];
        int index = 0;

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < prerequisites.length; i++){
            degree[prerequisites[i][0]]++;
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        for (int i = 0; i < degree.length; i++){
            if (degree[i] == 0){
                res[index++] = i;
                queue.add(i);
            }
        }

        while (queue.size() != 0) {
            int course = (int)queue.poll();
            for (int i = 0; i < graph[course].size(); i++){
                int pointer = (int)graph[course].get(i);
                degree[pointer]--;
                if (degree[pointer] == 0) {
                    res[index++] = pointer;
                    queue.add(pointer);
                }
            }
        }

        return (index == numCourses) ? res : new int[0];
    }
}
