import java.util.EmptyStackException;
import java.util.*;

class Stack<E>
{
    float loadFactor;
    float shrinkFactor;
    int top; // Index of the top element
    E arr[];
    int capacity;

    Stack(int capacity)
    {
        this.capacity = capacity;
        this.loadFactor = 0;
        this.shrinkFactor = 0;
        this.arr = (E[]) new Object[this.capacity];
        this.top = -1;
    }

    Stack(int capacity, float loadFactor)
    {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.shrinkFactor = 0;
        this.arr = (E[]) new Object[this.capacity];
        this.top = -1;
    }

    Stack(int capacity, float loadFactor, float shrinkFactor)
    {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.shrinkFactor = shrinkFactor;
        this.arr = (E[]) new Object[this.capacity];
        this.top = -1;
    }

    boolean isEmpty()
    {
        // return this.top == 0;
        return this.currentSize() == 0;
    }

    int getCapacity(){
        return this.capacity;
    }

    void push(E x) throws StackOverflowError, NullPointerException
    {
        // Stack can't grow (full)
        if (this.isFull()) {
            throw StackOverflowError();
        }
        // push null
        if (this.x == null) {
            throw NullPointerException();
        }
        // add element to top of Stack
        this.arr[this.top + 1] = x;
        this.top++;
        // handles load factor
        if (this.loadFactor != 0 && this.currentSize() > this.capacity * this.loadFactor) {
            this.capacity *= 1 + this.loadFactor;
        }
    }

    E pop() throws EmptyStackException
    {
        // stack empty
        if (this.isEmpty()) {
            throw EmptyStackException();
        }
        E tmp = this.arr[this.top];
        this.arr[this.top] = null;
        this.top--;
        // handles shrink factor
        if (this.shrinkFactor != 0 && this.currentSize() < this.capacity * this.shrinkFactor) {
          this.capacity *= this.shrinkFactor;
        }
        return tmp;
    }

    E peek() throws EmptyStackException{
        // stack empty
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.arr[this.top]
    }

    void clear(){
        // empty Stack
        if (this.isEmpty()) {
            return;
        }
        // loop through array, set to null (remove), decrement top
        for (int i = 0; i < this.arr.length; i++) {
            this.arr[i] = null;
            this.top--;
        }
    }

    boolean isFull(){
        return this.currentSize() == this.capacity;
    }

    int currentSize(){
        int counter = 0;
        while (this.arr[counter] != null) {
            counter++;
        }
        return counter;
    }

    E[] multiPop(int k) throws EmptyStackException{
        // stack empty
        if (this.isEmpty()) {
            throw EmptyStackException();
        }
        // k > size
        if (k > this.currentSize()) {
            return this.arr[];
        }
        E[] popArray = (E[]) new Object[k];
        // pop k elements
        for (int i = 0; i < k; i++) {
            popArray[i] = this.arr[this.top].pop();
            this.top--;
        }
        return popArray;
    }

    void multiPush(E[] arr) throws StackOverflowError, NullPointerException{
        // Stack can't grow (full)
        if (this.isFull()) {
            throw StackOverflowError();
        }
        // loop through param arr
        for (int i = 0; i < arr.length; i++) {
            this.push(arr[i]);
        }
    }

    void reverse() throws EmptyStackException{
        // stack empty
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        // reverse stack (array)
        for (int i = 0; i < arr.length / 2; i++) {
            E tmp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = tmp;
        }
    }

    boolean pushUnique(E x) throws StackOverflowError, NullPointerException{
        // Stack can't grow (full)
        if (this.isFull()) {
            throw StackOverflowError();
        }
        // push null
        if (this.x == null) {
            throw NullPointerException();
        }
        // param x is unique
        if (x != this.arr[this.top]) {
            this.push(x);
            return true;
        }
        // not a success
        return false;
    }

    int search(E x) throws EmptyStackException{
        // stack empty
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        int distance = 1;
        int i = top;
        while (i != 0) {
            // found
            if (arr[i] == x) {
                return distance;
            }
            distance++;
            i--;
        }
        return -1;
    }

}
