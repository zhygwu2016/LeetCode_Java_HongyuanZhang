package Online_Assessment;
/*
The current selected programming language is Java. We emphasize the
submission of a fully working code over partially correct but efficient code.
Once submitted, you cannot review this problem again. You can
use System.out.print/n() to debug your code. The System.out.print/n() may not
work in case of syntax/runtime error. The version of 'DK being used is 1.8.
Amazon Fresh is a grocery delivery service that offers consumers the option of
purchasing their groceries online and having them delivered on schedule. The
Amazon Fresh team is planning a route for a delivery truck to deliver customer orders
in the city of Techlandia. The planner will create a delivery area for each order to
effectively plan the route. The area is abstracted as a grid. Not all locations are
accessible by road. The truck only needs to make a single delivery.
Write an algorithm to determine the minimum distance required for the truck to
deliver the order.

Assumptions:
Some places in the delivery area cannot be accessed by the driver, as there are no
roads in those locations.
The delivery area can be represented as a two-dimensional grid of integers, where each
integer represents one cell.
The truck must start from the top-left corner of the area, which is always accessible
and can move one cell up, down, left, or right at a time.
The truck must navigate around the areas without roads and cannot leave the area.
The accessible areas are represented as 1, areas without roads are represented by O and
the order destination is represented by 9.

Input
The input to the function/method consists of three arguments:
numRows, an integer representing the number of rows;
numColumns, an integer representing the number of columns;
area, representing the two-dimensional grid of integers.

Output
Return an integer representing the total distance traversed to deliver the order else
return-1.

Constraints
1 <= numRows, numColumns <= 10^3

Example
Input:
numRows = 3
numColumns = 3

area =
[[1, 0, 0],
 [1, 0, 0],
 [1, 9, 1]]

Output:
3

Explanation:
Starting from the top-left corner, the truck traversed the cells (0,0) -> (1,0) -> (2,0)->(2,1).
The truck traversed the total distance
to deliver the order.
So, the output is 3.

 */

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
