package leetcode.java;

// 489. Robot Room Cleaner
// https://leetcode.com/problems/robot-room-cleaner/

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
public class LC489_Robot_Room_Cleaner {
}


//public class LC489_Robot_Room_Cleaner {
//    private static final int[][] directions = {
//        {-1, 0}, {0, 1}, {1, 0}, {0, -1}
//    };
//
//    public void cleanRoom(Robot robot) {
//        moveFrom(0, 0, 0, robot, new HashSet<>());
//    }
//
//    private void moveFrom(int x, int y, int orientation, Robot robot, Set<String> visited){
//        /* Clean current cell */
//        robot.clean();
//        visited.add(x + "," + y);
//
//        /* Try moving forward in 4 directions */
//        for(int i = 0; i < 4; i++){
//            int currOrientation = (i + orientation) % 4;
//            int nx = x + directions[currOrientation][0];
//            int ny = y + directions[currOrientation][1];
//
//            if(!visited.contains(nx + "," + ny) && robot.move()){
//                moveFrom(nx, ny, currOrientation, robot, visited);
//                robot.turnRight();
//                robot.turnRight();
//                robot.move();
//                robot.turnLeft();
//                robot.turnLeft();
//            }
//
//            robot.turnRight();
//        }
//    }
//}

