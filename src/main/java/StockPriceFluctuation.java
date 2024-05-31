import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
2034. Stock Price Fluctuation
Medium
Topics
Companies
Hint
You are given a stream of records about a particular stock. Each record contains a timestamp and the corresponding price of the stock at that timestamp.

Unfortunately due to the volatile nature of the stock market, the records do not come in order. Even worse, some records may be incorrect. Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.

Design an algorithm that:

Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
Finds the maximum price the stock has been based on the current records.
Finds the minimum price the stock has been based on the current records.
Implement the StockPrice class:

StockPrice() Initializes the object with no price records.
void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
int current() Returns the latest price of the stock.
int maximum() Returns the maximum price of the stock.
int minimum() Returns the minimum price of the stock.


Example 1:

Input
["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
[[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
Output
[null, null, null, 5, 10, null, 5, null, 2]

Explanation
StockPrice stockPrice = new StockPrice();
stockPrice.update(1, 10); // Timestamps are [1] with corresponding prices [10].
stockPrice.update(2, 5);  // Timestamps are [1,2] with corresponding prices [10,5].
stockPrice.current();     // return 5, the latest timestamp is 2 with the price being 5.
stockPrice.maximum();     // return 10, the maximum price is 10 at timestamp 1.
stockPrice.update(1, 3);  // The previous timestamp 1 had the wrong price, so it is updated to 3.
                          // Timestamps are [1,2] with corresponding prices [3,5].
stockPrice.maximum();     // return 5, the maximum price is 5 after the correction.
stockPrice.update(4, 2);  // Timestamps are [1,2,4] with corresponding prices [3,5,2].
stockPrice.minimum();     // return 2, the minimum price is 2 at timestamp 4.


Constraints:

1 <= timestamp, price <= 109
At most 105 calls will be made in total to update, current, maximum, and minimum.
current, maximum, and minimum will be called only after update has been called at least once.
 */
public class StockPriceFluctuation {

  /*
   * Hashed and Sorted Map
   * We will use a hashmap (timestampPriceMap) to store the price of the stocks. A hashmap stores the elements
   * as key-value pairs. Here, our key is the timestamp and the value is the price of the stock at the
   * respective timestamp, i.e. the hashmap maps timestamp to price.
   *
   * Now, we will use a sorted map (priceFrequency) to store the stock prices in increasing order.
   *
   * Time complexity: O(NlogN)
   * In the update function, we add and remove a record in both hashmap and sorted map.
   * In the worst-case scenario, all N calls will be to the update function, which will require a total of O(NlogN) time.
   * Getting first or last element of tree map takes log(N) time. Thus, here maximum, minimum
   * functions will take O(log(N)) time for each function call.
   *
   */
  class StockPrice {
    int latestTime;
    // Store price of each stock at each timestamp.
    Map<Integer, Integer> timestampPriceMap;
    // Store stock prices in increasing order to get min and max price.
    TreeMap<Integer, Integer> priceFrequency;

    public StockPrice() {
      latestTime = 0;
      timestampPriceMap = new HashMap<>();
      priceFrequency = new TreeMap<>();
    }

    public void update(int timestamp, int price) {
      // Update latestTime to latest timestamp.
      latestTime = Math.max(latestTime, timestamp);

      // If same timestamp occurs again, previous price was wrong.
      if (timestampPriceMap.containsKey(timestamp)) {
        // Remove previous price.
        int oldPrice = timestampPriceMap.get(timestamp);
        priceFrequency.put(oldPrice, priceFrequency.get(oldPrice) - 1);

        // Remove the entry from the map.
        if (priceFrequency.get(oldPrice) == 0) {
          priceFrequency.remove(oldPrice);
        }
      }

      // Add latest price for timestamp.
      timestampPriceMap.put(timestamp, price);
      priceFrequency.put(price, priceFrequency.getOrDefault(price, 0) + 1);
    }

    public int current() {
      // Return latest price of the stock.
      return timestampPriceMap.get(latestTime);
    }

    public int maximum() {
      // Return the maximum price stored at the end of sorted-map.
      return priceFrequency.lastKey();
    }

    public int minimum() {
      // Return the maximum price stored at the front of sorted-map.
      return priceFrequency.firstKey();
    }
  }

}
