package Online_Assessment;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Minimum_Distance {

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public int minimumDistance(int numRows, int numColumns, List<List<Integer>> area) {
        // WRITE YOUR CODE HERE
        // corner cases
        if (area == null || area.size() == 0 || area.get(0) == null || area.get(0).size() == 0
                || numRows <= 0 || numColumns <= 0 ) {
            return -1;
        }
        // if the left-up corner doesn't have a road, the truck cannot start the delivery
        if (area.get(0).get(0) == 0) {
            return -1;
        }

        // use breadth-first search to traverse the area
        // boolean[] visited to avoid the duplicate visiting
        boolean[] visited = new boolean[numRows * numColumns];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(0);
        visited[0] = true;

        // the initial distance for delivery is 1
        int res = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int position = queue.poll();
                int row = position / numColumns;
                int col = position % numColumns;

                for (int[] direction : DIRECTIONS) {
                    int i = row + direction[0];
                    int j = col + direction[1];
                    int newPosition = i * numColumns + j;

                    if (i >= 0 && i < numRows && j >= 0 && j < numColumns
                            && area.get(i).get(j) != 0 && !visited[newPosition]) {
                        // when we visit the destinagtion, just return the distance result
                        if (area.get(i).get(j) == 9) {
                            return res;
                        }
                        visited[newPosition] = true;
                        // add the current position to the queue
                        queue.offer(newPosition);
                    }
                }
            }
            res++;
        }

        // after the breadth-first search, the truck cannot still find the destination
        return -1;

    }

    // helper function of four directions, be used in breadth-first search
    private static final int[][] DIRECTIONS = {
            {-1, 0}, // up
            {1, 0},  // down
            {0, -1}, // left
            {0, 1}   // right
    };
    // METHOD SIGNATURE ENDS
}
