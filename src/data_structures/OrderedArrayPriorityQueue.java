/*  Author: Jason Kramer
    Class Acc: cssc0914
  
 	The PriorityQueue ADT may store objects in any order.  However,
    removal of objects from the PQ must follow specific criteria.
    The object of highest priority that has been in the PQ longest
    must be the object returned by the remove() method.  FIFO return
    order must be preserved for objects of identical priority.
   
    Ranking of objects by priority is determined by the Comparable<E>
    interface.  All objects inserted into the PQ must implement this
    interface.
*/

package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedArrayPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
	private E[] storage;
	private int maxSize, currentSize, indexPosition, modificationCounter;

	public OrderedArrayPriorityQueue(int max) {
		maxSize = max;
		currentSize = 0;
		storage = (E[]) new Comparable[maxSize];
	}

	public OrderedArrayPriorityQueue() {
		this(DEFAULT_MAX_CAPACITY);
	}

	// Inserts a new object into the priority queue. Returns true if
	// the insertion is successful. If the PQ is full, the insertion
	// is aborted, and the method returns false.
	public boolean insert(E object) {
		modificationCounter++;
		if (isFull())
			return false;
		indexPosition = binarySearchFirst(object);
		shift(indexPosition);
		currentSize++;
		storage[indexPosition] = object;
		return true;
	}

	// Removes the object of highest priority that has been in the
	// PQ the longest, and returns it. Returns null if the PQ is empty.
	public E remove() {
		if (isEmpty())
			return null;
		modificationCounter++;
		currentSize--;
		return storage[currentSize];
	}

	// Deletes all instances of the parameter obj from the PQ if found, and
	// returns true. Returns false if no match to the parameter obj is found.
	public boolean delete(E obj) {
		int low = binarySearchFirst(obj);
		int high = binarySearchLast(obj);
		if (storage[low].compareTo(obj) != 0)
			return false;
		int amount = 0;
		for (int i = low; i <= high; i++) {
			amount++;
			storage[i] = storage[high + amount];
		}
		modificationCounter++;
		return true;
	}

	// Returns the object of highest priority that has been in the
	// PQ the longest, but does NOT remove it.
	// Returns null if the PQ is empty.
	public E peek() {
		if (isEmpty())
			return null;
		return storage[currentSize - 1];
	}

	// Returns true if the priority queue contains the specified element
	// false otherwise.
	public boolean contains(E obj) {
		return binarySearchFind(obj);
	}

	// Returns the number of objects currently in the PQ.
	public int size() {
		return currentSize;
	}

	// Returns the PQ to an empty state.
	public void clear() {
		currentSize = 0;
	}

	// Returns true if the PQ is empty, otherwise false
	public boolean isEmpty() {
		return currentSize == 0;
	}

	// Returns true if the PQ is full, otherwise false. List based
	// implementations should always return false.
	public boolean isFull() {
		return currentSize == maxSize;
	}

	// Returns an iterator of the objects in the PQ, in no particular
	// order.
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}

	class IteratorHelper<E> implements Iterator<E> {
		int iterIndex;
		long stateCheck;

		public IteratorHelper() {
			iterIndex = 0;
			stateCheck = modificationCounter;
		}

		public boolean hasNext() {
			if (stateCheck != modificationCounter)
				throw new ConcurrentModificationException();
			return iterIndex < currentSize;
		}

		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return (E) storage[iterIndex++];
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	// Returns first index with the priority inputted
	private int binarySearchFirst(E value) {
		int first = 0;
		int last = currentSize - 1;
		int middle = (first + last) / 2;
		while (first <= last) {
			if (storage[middle].compareTo(value) <= 0)
				last = middle - 1;
			else
				first = middle + 1;
			middle = (first + last) / 2;
		}
		return first;
	}

	// Returns last index with the priority inputted
	private int binarySearchLast(E value) {
		int first = 0;
		int last = currentSize;
		int middle = (first + last) / 2;
		while (first <= last) {
			if (storage[middle].compareTo(value) >= 0)
				last = middle - 1;
			else
				first = middle + 1;
			middle = (first + last) / 2;
		}
		return first;
	}
	
	private boolean binarySearchFind(E value) {
		int first = 0;
		int last = currentSize;
		int middle = (first + last) / 2;
		while (first < last) {
			if (storage[middle].compareTo(value) == 0)
				return true;
			else if (storage[middle].compareTo(value) > 0)
				last = middle - 1;
			else
				first = middle + 1;
			middle = (last - first) / 2;
		}
		return false;
	}

	private void shift(int position) {
		for (int i = currentSize; i > position; i--)
			storage[i] = storage[i - 1];
	}

}