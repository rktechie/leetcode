import java.util.List;
import java.util.Map;
import java.util.Set;

/*
65. Valid Number
Hard
Topics
Companies
A valid number can be split up into these components (in order):

A decimal number or an integer.
(Optional) An 'e' or 'E', followed by an integer.
A decimal number can be split up into these components (in order):

(Optional) A sign character (either '+' or '-').
One of the following formats:
One or more digits, followed by a dot '.'.
One or more digits, followed by a dot '.', followed by one or more digits.
A dot '.', followed by one or more digits.
An integer can be split up into these components (in order):

(Optional) A sign character (either '+' or '-').
One or more digits.
For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"], while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].

Given a string s, return true if s is a valid number.



Example 1:

Input: s = "0"
Output: true
Example 2:

Input: s = "e"
Output: false
Example 3:

Input: s = "."
Output: false


Constraints:

1 <= s.length <= 20
s consists of only English letters (both uppercase and lowercase), digits (0-9), plus '+', minus '-', or dot '.'.
 */
public class ValidNumber {

  /*
   * Approach:  Deterministic Finite Automaton (DFA)
   * A DFA is a finite number of states with transition rules to move between them.
   *
   * DFAs share a lot of similarities with the trie data structure. We start at a "root" node, and then
   * check each character one by one, checking whether or not there is a valid transition we can make.
   * There are some differences too:
   * 1. While a trie can only represent a finite number of strings (the given dictionary), a DFA can represent
   * an infinite number of different strings.
   * 2. While a trie can only move down the implicit tree, a DFA can essentially "loopback" to a higher level,
   * or stay on the same level, or even the same node.
   * 3. A trie is a type of tree, and a DFA is a type of directed graph
   *
   * Algorithm: (The first step is to design our DFA.)
   * - Identify all valid combinations that the aforementioned boolean variables can be in. Each combination
   * is a state. Draw a circle for each state, and label what it means.
   * - For each state, consider what a character from each group would mean in the context of that state.
   *  Each group will either cause a transition into another state, or it will signify that the string is invalid.
   * For each valid transition, draw a directed arrow between the two states and write the group next to the arrow.
   *
   * With our constructed DFA, our algorithm will be:
   * 1. Initialize the DFA as an array of hash tables. Each hash table's keys will be a character group,
   * and the values will be the state it should transition to. We can use the indexes of the array to
   * handle state transitions. Set the currentState = 0.
   * 2. Iterate through the input. For each character, first determine what group it belongs to. Then, check if
   * that group exists in the current state's hash table. If it does, transition to the next state. Otherwise, return false.
   * 3. At the end, check if we are currently in a valid end state: 1, 4, or 7.
   *
   * Time complexity: O(N), where N is the length of s
   * Space complexity: O(1).
   */

  // This is the DFA we have designed above
  private static final List<Map<String, Integer>> dfa = List.of(
      Map.of("digit", 1, "sign", 2, "dot", 3),
      Map.of("digit", 1, "dot", 4, "exponent", 5),
      Map.of("digit", 1, "dot", 3),
      Map.of("digit", 4),
      Map.of("digit", 4, "exponent", 5),
      Map.of("sign", 6, "digit", 7),
      Map.of("digit", 7),
      Map.of("digit", 7)
  );

  // These are all of the valid finishing states for our DFA.
  private static final Set<Integer> validFinalStates = Set.of(1, 4, 7);

  public boolean isNumber(String s) {
    int currentState = 0;
    String group = "";

    for (int i = 0; i < s.length(); i++) {
      char curr = s.charAt(i);
      if (Character.isDigit(curr)) {
        group = "digit";
      } else if (curr == '+' || curr == '-') {
        group = "sign";
      } else if (curr == 'e' || curr == 'E') {
        group = "exponent";
      } else if (curr == '.') {
        group = "dot";
      } else {
        return false;
      }

      if (!dfa.get(currentState).containsKey(group)) {
        return false;
      }

      currentState = dfa.get(currentState).get(group);
    }

    return validFinalStates.contains(currentState);
  }
}

class Solution2 {

  /*
   * Approach: Follow The Rules!
   *
   * Let's put all possible characters into groups, and then create a set of rules for each group. Then,
   * we can iterate through the input and evaluate if the characters are following the rules or not.
   *
   * What are all of the possible character groups:
   * 1. Digits (one of ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"])
   * 2. A sign ("+" or "-")
   * 3. An exponent ("e" or "E")
   * 4. A dot (".")
   * 5. Anything else
   *
   * Time complexity: O(N), where N is the length of s.
   * Space complexity: O(1)
   */
  public boolean isNumber(String s) {
    boolean seenDigit = false;
    boolean seenExponent = false;
    boolean seenDot = false;

    for (int i = 0; i < s.length(); i++) {
      char curr = s.charAt(i);
      if (Character.isDigit(curr)) {
        seenDigit = true;
      } else if (curr == '+' || curr == '-') {
        if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
          return false;
        }
      } else if (curr == 'e' || curr == 'E') {
        if (seenExponent || !seenDigit) {
          return false;
        }
        seenExponent = true;
        seenDigit = false;
      } else if (curr == '.') {
        if (seenDot || seenExponent) {
          return false;
        }
        seenDot = true;
      } else {
        return false;
      }
    }

    return seenDigit;
  }
}
