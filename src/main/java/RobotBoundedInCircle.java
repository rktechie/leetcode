/*
1041. Robot Bounded In Circle
Medium
Topics
Companies
Hint
On an infinite plane, a robot initially stands at (0, 0) and faces north. Note that:

The north direction is the positive direction of the y-axis.
The south direction is the negative direction of the y-axis.
The east direction is the positive direction of the x-axis.
The west direction is the negative direction of the x-axis.
The robot can receive one of three instructions:

"G": go straight 1 unit.
"L": turn 90 degrees to the left (i.e., anti-clockwise direction).
"R": turn 90 degrees to the right (i.e., clockwise direction).
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.



Example 1:

Input: instructions = "GGLLGG"
Output: true
Explanation: The robot is initially at (0, 0) facing the north direction.
"G": move one step. Position: (0, 1). Direction: North.
"G": move one step. Position: (0, 2). Direction: North.
"L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: West.
"L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: South.
"G": move one step. Position: (0, 1). Direction: South.
"G": move one step. Position: (0, 0). Direction: South.
Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (0, 2) --> (0, 1) --> (0, 0).
Based on that, we return true.
Example 2:

Input: instructions = "GG"
Output: false
Explanation: The robot is initially at (0, 0) facing the north direction.
"G": move one step. Position: (0, 1). Direction: North.
"G": move one step. Position: (0, 2). Direction: North.
Repeating the instructions, keeps advancing in the north direction and does not go into cycles.
Based on that, we return false.
Example 3:

Input: instructions = "GL"
Output: true
Explanation: The robot is initially at (0, 0) facing the north direction.
"G": move one step. Position: (0, 1). Direction: North.
"L": turn 90 degrees anti-clockwise. Position: (0, 1). Direction: West.
"G": move one step. Position: (-1, 1). Direction: West.
"L": turn 90 degrees anti-clockwise. Position: (-1, 1). Direction: South.
"G": move one step. Position: (-1, 0). Direction: South.
"L": turn 90 degrees anti-clockwise. Position: (-1, 0). Direction: East.
"G": move one step. Position: (0, 0). Direction: East.
"L": turn 90 degrees anti-clockwise. Position: (0, 0). Direction: North.
Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (-1, 1) --> (-1, 0) --> (0, 0).
Based on that, we return true.


Constraints:

1 <= instructions.length <= 100
instructions[i] is 'G', 'L' or, 'R'.
 */
public class RobotBoundedInCircle {

  /*
   * Solution is based on two facts about the limit cycle trajectory.
   * 1. After at most 4 cycles, the limit cycle trajectory returns to the initial point x = 0, y = 0.
   *    That is related to the fact that 4 directions (north, east, south, west) define the repeated cycles' plane symmetry
   *
   * 2. We do not need to run 4 cycles to identify the limit cycle trajectory. One cycle is enough.
   *    There could be two situations here:
   *    - First, if the robot returns to the initial point after one cycle, that's the limit cycle trajectory.
   *    - Second, if the robot doesn't face north at the end of the first cycle, that's the limit cycle trajectory.
   *      Once again, that's the consequence of the plane symmetry for the repeated cycles
   *
   *
   * Let's use numbers from 0 to 3 to mark the directions: north = 0, east = 1, south = 2, west = 3
   *
   */
  public boolean isRobotBounded(String instructions) {
    // north = 0, east = 1, south = 2, west = 3
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    // Initial position is in the center
    int x = 0, y = 0;
    // facing north
    int idx = 0;

    for (char i : instructions.toCharArray()) {
      if (i == 'L')
        idx = (idx + 3) % 4; // to turn on the left - can be written in a symmetric way idx = (idx - 1) % 4. That means we have to deal with negative indices. A more simple way is to notice that 1 turn to the left = 3 turns to the right: idx = (idx + 3) % 4.
      else if (i == 'R')
        idx = (idx + 1) % 4; // to turn on the right, the next direction is idx = (idx + 1) % 4. Modulo here is needed to deal with the situation - facing west, idx = 3, turn to the right to face north, idx = 0
      else {
        x += directions[idx][0];
        y += directions[idx][1];
      }
    }

    // After one cycle we have everything to decide. It's a limit cycle trajectory:
    // if the robot is back to the center: x = y = 0
    // OR if the robot doesn't face north: idx != 0. ( if the robot doesn't face north at the end of the first cycle, then after 4 cycles faces north)
    return (x == 0 && y == 0) || (idx != 0);
  }
}
