import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/*
1472. Design Browser History

You have a browser of one tab where you start on the homepage and you can visit another url, get back in the history number of steps or move forward in the history number of steps.

Implement the BrowserHistory class:

BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
void visit(string url) Visits url from the current page. It clears up all the forward history.
string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x, you will return only x steps. Return the current url after moving back in history at most steps.
string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.


Example:

Input:
["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
[["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
Output:
[null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]

Explanation:
BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
browserHistory.back(1);                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
browserHistory.back(1);                   // You are in "facebook.com", move back to "google.com" return "google.com"
browserHistory.forward(1);                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
browserHistory.forward(2);                // You are in "linkedin.com", you cannot move forward any steps.
browserHistory.back(2);                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
browserHistory.back(7);                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"


Constraints:

1 <= homepage.length <= 20
1 <= url.length <= 20
1 <= steps <= 100
homepage and url consist of  '.' or lower case English letters.
At most 5000 calls will be made to visit, back, and forward.
 */

// 1st approach: Double stack
public class BrowserHistory {
  private Stack<String> history, future;
  private String current;

  /*
   * Approach: Double stack
   *
   * Let's assume here, n visit calls are made, m is the maximum number of steps to go forward or back,
   * and l is the maximum length of the URL string.
   *
   * Time complexity:
   * in the worst case each call to the visit(url) method will take O(1) time
   * in the worst case, each call to back(steps) and forward(steps) methods will take O(min(m,n)) time
   * We do these two operations unless we are done with m steps or all elements are removed from the stack
   * which might have n elements in it.
   *
   * Space complexity:
   * We might visit n URL strings and they will be stored in our stacks.
   * Thus, in the worse case, we use O(l⋅n) space.
   */
  public BrowserHistory(String homepage) {
    history = new Stack<String>();
    future = new Stack<String>();
    // 'homepage' is the first visited URL.
    current = homepage;
  }

  public void visit(String url) {
    // Push 'current' in 'history' stack and mark 'url' as 'current'.
    history.push(current);
    current = url;
    // We need to delete all entries from 'future' stack.
    future = new Stack<String>();
  }

  public String back(int steps) {
    // Pop elements from 'history' stack, and push elements in 'future' stack.
    while(steps > 0 && !history.empty()) {
      future.push(current);
      current = history.pop();
      steps--;
    }
    return current;
  }

  public String forward(int steps) {
    // Pop elements from 'future' stack, and push elements in 'history' stack.
    while(steps > 0 && !future.empty()) {
      history.push(current);
      current = future.pop();
      steps--;
    }
    return current;
  }
}

// 2nd approach: Dynamic Array, faster than the 1st approach but 1st approach is much easier to understand
// a dynamic array can also be used to simulate a stack-like behavior, and just like previous approach
// we can append elements at end and use pointer to traverse back and forth.
//
// Time complexity: each call to these methods will take O(1) time
// Space complexity: n the worse case, we use O(l⋅n) space.
class BrowserHistory2 {
  ArrayList<String> visitedURLs;
  int currURL, lastURL;

  public BrowserHistory2(String homepage) {
    // 'homepage' is the first visited URL.
    visitedURLs = new ArrayList<String>(Arrays.asList(homepage));
    currURL = 0;
    lastURL = 0;
  }

  public void visit(String url) {
    currURL += 1;
    if (visitedURLs.size() > currURL) {
      // We have enough space in our array to overwrite an old 'url' entry with new one.
      visitedURLs.set(currURL, url);
    } else {
      // We have to insert a new 'url' entry at the end.
      visitedURLs.add(url);
    }
    // This 'url' will be last URL if we try to go forward.
    lastURL = currURL;
  }

  public String back(int steps) {
    // Move 'currURL' pointer in left direction.
    currURL = Math.max(0, currURL - steps);
    return visitedURLs.get(currURL);
  }

  public String forward(int steps) {
    // Move 'currURL' pointer in right direction.
    currURL = Math.min(lastURL, currURL + steps);
    return visitedURLs.get(currURL);
  }
}
