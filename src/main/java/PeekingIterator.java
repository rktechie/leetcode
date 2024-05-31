import java.util.Iterator;

/*
 * Problem : Peeking Iterator
 * 
Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

Example:

Assume that the iterator is initialized to the beginning of the list: [1,2,3].

Call next() gets you 1, the first element in the list.
Now you call peek() and it returns 2, the next element. Calling next() after that still return 2. 
You call next() the final time and it returns 3, the last element. 
Calling hasNext() after that should return false.
Follow up: How would you extend your design to be generic and work with all types, not just integer?
 */

/*
 * Solution:
 * 
 * Using null to check if we have reached the end of iterator is not 100% right. 
 * In theory, null could be a valid element.
 * An slightly alternative approach to use boolean indicating end of iterator:
 */
public class PeekingIterator implements Iterator<Integer> {
	Integer next;
    Iterator<Integer> itr;
    boolean done = false;
    
	public PeekingIterator(Iterator<Integer> iterator) {
	    itr = iterator;
	    if (iterator.hasNext())
	        next = itr.next();
	    else
	        done = true;
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
	    return next;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    Integer result = next;
	    if (itr.hasNext()) {
	        next = itr.next();
	    } else {
	        next = null;
	        done = true;
	    }
	    return result;
	}

	@Override
	public boolean hasNext() {
	    return !done;
	}
}
