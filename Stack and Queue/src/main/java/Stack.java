/**
 * Name: Darren Wang
 * Email: d5wang@ucsd.edu
 * Userid: cs12sp19kj
 * Sources: writeup, Piazza, StackOverflow
 */
import java.util.EmptyStackException;
import java.util.*;

/**
 * Implementation of a Stack data structure using an array
 */
public class Stack<E>
{
    float loadFactor;
    float shrinkFactor;
    int top; // Index of the top element
    E arr[];
    int capacity;

    /**
     * Initializes a stack with fixed capacity
     *
     * @param capacity fixed capacity
     */
    public Stack(int capacity) {
        this.capacity = capacity;
        this.loadFactor = 0;
        this.shrinkFactor = 0;
        this.arr = (E[]) new Object[this.capacity];
        this.top = -1;
    }

    /**
     * Initializes a stack with initial capacity, can grow
     *
     * @param capacity fixed capacity
     * @param loadFactor determines when to grow
     */
    public Stack(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.shrinkFactor = 0;
        this.arr = (E[]) new Object[this.capacity];
        this.top = -1;
    }

    /**
     * Initializes a stack with initial capacity, can grow and shrink
     *
     * @param capacity fixed capacity
     * @param loadFactor determines when to grow
     * @param shrinkFactor determines when to shrink
     */
    public Stack(int capacity, float loadFactor, float shrinkFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.shrinkFactor = shrinkFactor;
        this.arr = (E[]) new Object[this.capacity];
        this.top = -1;
    }

    /**
     * Determines if stack if currently empty
     *
     * @return if no elements in stack
     */
    public boolean isEmpty() {
        // return this.top == -1;
        return this.currentSize() == 0;
    }

    /**
     * Returns total capacity of stack (maximum number of elements stack can
     * store)
     *
     * @return stack capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Adds an element to top of stack, check if needs to grow
     *
     * @param x element to be added
     */
    public void push(E x) throws StackOverflowError, NullPointerException {
        // Stack can't grow (full)
        if (this.isFull()) {
            throw new StackOverflowError();
        }
        // push null
        if (x == null) {
            throw new NullPointerException();
        }
        // add element to top of Stack
        this.arr[this.top + 1] = x;
        this.top++;
        // load factor triggered
        if (this.loadFactor != 0 && this.currentSize() > this.capacity
	    * this.loadFactor) {
            this.capacity += this.capacity;
	    this.resizeArray((E[]) new Object[this.capacity]);
        }
    }

    /**
     * Removes and returns element at top of the stack, check if needs to
     * shrink
     *
     * @return removed top element
     */
    public E pop() throws EmptyStackException {
        // stack empty
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        E tmp = this.arr[this.top];
        this.top--;
        // shrink factor triggered
        if (this.shrinkFactor != 0 && this.currentSize() < this.capacity
	    * this.shrinkFactor) {
          this.capacity *= .5;
	  this.resizeArray((E[]) new Object[this.capacity]);
        }
        return tmp;
    }

    /**
     * Returns top element of stack without removing it
     *
     * @return top element
     */
    public E peek() throws EmptyStackException {
        // stack empty
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.arr[this.top];
    }

    /**
     * Remove all elements from stack
     */
    public void clear() {
        // empty Stack
        if (this.isEmpty()) {
            return;
        }
	this.top = -1;
    }

    /**
     * Return if stack is full
     *
     * @return whether stack full
     */
    public boolean isFull() {
        // always growing
	if (loadFactor != 0) {
	    return false;
	}
        return this.currentSize() == this.capacity;
    }

    /**
     * Returns total number of current elements in stack
     *
     * @return size of stack
     */
    public int currentSize() {
        return this.top + 1;
    }

    /**
     * Pop k elements from stack, if k > size then return all elements as an
     * array
     *
     * @param k number of elements to pop
     * @return popped elements as an array
     */
    public E[] multiPop(int k) throws EmptyStackException {
        // stack empty
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
	E[] popArray;
        // k > size
        if (k > this.currentSize()) {
	    popArray = (E[]) new Object[this.currentSize()];
            for (int i = 0; i < popArray.length; i++) {
	        popArray[i] = this.pop();
	    }
        }
	else {
	    popArray = (E[]) new Object[k];
            // pop k elements
            for (int i = 0; i < k; i++) {
                popArray[i] = this.pop();
            }
	}
	return popArray;
    }

    /**
     * Push all elements in param array, one by one
     *
     * @param arr array to push
     */
    public void multiPush(E[] arr) throws StackOverflowError, 
    NullPointerException {
        // Stack can't grow (full)
        if (this.isFull()) {
            throw new StackOverflowError();
        }
        // loop through param arr
        for (int i = 0; i < arr.length; i++) {
            this.push(arr[i]);
        }
    }

    /**
     * Reverse all elements in the stack
     */
    public void reverse() throws EmptyStackException {
        // stack empty
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        // reverse stack (array)
        for (int i = 0; i < this.currentSize() / 2; i++) {
            E tmp = arr[i];
            arr[i] = arr[this.currentSize() - 1 - i];
            arr[this.currentSize() - 1 - i] = tmp;
        }
    }

    /**
     * Pushes param element if not same as current top element
     *
     * @param x element to push
     * @return whether push was successful or not
     */
    public boolean pushUnique(E x) throws StackOverflowError, 
    NullPointerException {
        // Stack can't grow (full)
        if (this.isFull()) {
            throw new StackOverflowError();
        }
        // push null
        if (x == null) {
            throw new NullPointerException();
        }
        // param x is unique
        if (!x.equals(this.arr[this.top])) {
            this.push(x);
            return true;
        }
        // not a success
        return false;
    }

    /**
     * Returns distance to param element from top element
     *
     * @param x element to search for
     * @return distance between search and top element, -1 if not found
     */
    public int search(E x) throws EmptyStackException {
        // stack empty
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        int distance = 1;
        int i = top;
        while (i != -1) {
            // found
            if (arr[i].equals(x)) {
                return distance;
            }
            distance++;
            i--;
        }
        return -1;
    }
    
    /**
     * Helper method to resize instance variable arr according to shrink and
     * load factor
     *
     * @param array array to resize to
     */
    private void resizeArray(E[] array) {
        E[] newArray = array;
	for (int i = 0; i < this.currentSize(); i++) {
	    newArray[i] = this.arr[i];
	}
	this.arr = newArray;
    }

}
