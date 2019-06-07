#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "stack.h"

// Edit only after this line //

/**
 * current size of stack
 *
 * @param s Stack pointer
 * @return size of Stack
 */
int currentSize(Stack* s) {
  return s->top + 1;
}

/**
 * Helper function to shrink capacity of stack according to shrinkFactor
 *
 * @param s Stack pointer
 */
void shrinkCapacity(Stack* s) {
  Element** newArray = (Element**) malloc(s->capacity * sizeof(Element*));
  for (int i = 0; i < currentSize(s); i++) {
    newArray[i] = s->elements[i];
  }
  free(s->elements);
  s->elements = newArray;
}

/**
 * pop function for stack
 *
 * @param s Stack pointer
 * @return popped top element
 */
Element* pop(Stack* s) {
  // stack empty
  if (isEmpty(s)) {
    return NULL;
  }
  Element* popped = s->elements[s->top];
  s->top--;
  // shrinkFactor triggered
  if (s->shrinkFactor != 0 && currentSize(s) < (int) (getCapacity(s)
      * s->shrinkFactor)) {
    s->capacity = s->capacity * .5;
    shrinkCapacity(s);
  }
  return popped;
}

/**
 * pop multiple elements from stack
 *
 * @param s Stack pointer
 * @param k number of elements to pop
 * @return popped elements as an array
 */
Element** multiPop(Stack* s, int k) {
  if (isEmpty(s)) {
    return NULL;
  }
  // pop all
  Element** arr;
  if (k > currentSize(s)) {
    arr = (Element**) malloc(currentSize(s) *
      sizeof(Element*));
    for (int i = 0; i < currentSize(s) - 1; i++) {
      arr[i] = pop(s);
    }
    return s->elements;
  } else {
    arr = (Element**) malloc(k * sizeof(Element*));
    for (int i = 0; i < k; i++) {
      arr[i] = pop(s);
    }
    return arr;
  }
}

/**
 * Utility function: expandCapacity for push
 *
 * @param s Stack pointer
 */
void expandCapacity(Stack* s) {
  Element** newArray = (Element**) malloc(s->capacity * sizeof(Element*));
  for (int i = 0; i < currentSize(s); i++) {
    newArray[i] = s->elements[i];
  }
  free(s->elements);
  s->elements = newArray;
}

/**
 * push function for stack
 *
 * @param s Stack pointer
 * @param k int key
 * @param v string value
 * @return push successful or not
 */
bool push(Stack* s, int k, char* v) {
  // stack full
  if (isFull(s)) {
    return false;
  }
  Element* item = malloc(sizeof(struct Element));
  item->key = k;
  item->value = v;
  s->elements[++s->top] = item;
  // growFactor triggered
  if (s->growthFactor != 0 && currentSize(s) > (int) (getCapacity(s)
      * s->growthFactor)) {
    s->capacity += s->capacity;
    expandCapacity(s);
  }
  return true;
}

/**
 * push unique function for stack, push only if top element not equal to
 * params: key and value
 *
 * @param s Stack pointer
 * @param k int key
 * @param v string value
 * @return push successful or not
 */
bool pushUnique(Stack* s, int k, char* v) {
  // stack full
  if (isFull(s)) {
    return false;
  }
  if (s->elements[s->top]->key != k || s->elements[s->top]->value != v) {
    push(s,k,v);
    return true;
  }
  return false;
}

/**
 * reverse the elements in stack
 *
 * @param s Stack pointer
 */
void reverse(Stack* s){
  // reverse stack (array)
  for (int i = 0; i < currentSize(s) / 2; i++) {
    Element* tmp = s->elements[i];
    s->elements[i] = s->elements[currentSize(s) - 1 - i];
    s->elements[currentSize(s) - 1 - i] = tmp;
  }
}

/**
 * peek function for stack
 *
 * @param s Stack pointer
 * @return top element of Stack
 */
Element* peek(Stack* s) {
  // stack empty
  if (isEmpty(s)) {
    return NULL;
  }
  return s->elements[s->top];
}

/**
 * search for element from top of stack
 *
 * @param s Stack pointer
 * @param k int key
 * @param v string value
 * @return distance to searched element from top of Stack
 */
int search(Stack* s, int k, char* v) {
  // stack empty
  if (isEmpty(s)) {
    return -1;
  }
  int distance = 1;
  int i = s->top;
  while (i != -1) {
    // found
    if (s->elements[i]->key == k && s->elements[i]->value == v) {
      return distance;
    }
    distance++;
    i--;
  }
  return -1;
}

/**
 * get current capacity of stack
 *
 * @param s Stack pointer
 * @return capacity of Stack
 */
int getCapacity(Stack* s) {
  return s->capacity;
}

/**
 * check if stack is full
 *
 * @param s Stack pointer
 * @return Stack full or not
 */
bool isFull(Stack* s) {
  // stack is always growing
  if (s->growthFactor != 0) {
    return false;
  }
  return s->top == s-> capacity - 1;
}

/**
 * clear the stack
 *
 * @param s Stack pointer
 */
void clear(Stack *s) {
  s->top = -1;
}

/**
 * clean the stack
 *
 * @param s Stack pointer
 */
void cleanStack(Stack *s) {
  for(int i = 0; i < s->capacity; i++) {
    free(s->elements[i]);
  }
    free(s->elements);
    free(s);
}

/**
 * check if stack empty
 *
 * @param s Stack pointer
 * @return whether Stack empty
 */
bool isEmpty(Stack* s) {
  return s->top == -1;
}

/**
 * Stack with fixed capacity, cotr
 *
 * @param cap Stack capacity
 */
Stack* makeStack(int cap) {
  Stack* stack = (struct Stack*) malloc(sizeof(struct Stack));
  stack->top = -1;
  stack->capacity = cap;
  stack->growthFactor = 0;
  stack->shrinkFactor = 0;
  stack->dynamic = false;
  stack->elements = (Element**) malloc(stack->capacity * sizeof(Element*));
  return stack;
}

/**
 * Stack with fixed capacity that can grow, cotr
 *
 * @param cap Stack capacity
 * @param growF Stack growth factor
 */
Stack* makeStackG(int cap, float growF) {
  Stack* stack = (struct Stack*) malloc(sizeof(struct Stack));
  stack->top = -1;
  stack->capacity = cap;
  stack->growthFactor = growF;
  stack->shrinkFactor = 0;
  stack->dynamic = true;
  stack->elements = (Element**) malloc(stack->capacity * sizeof(Element*));
  return stack;
}

/**
 * Stack with fixed capacity that can grow and shrink, cotr
 *
 * @param cap Stack capacity
 * @param growF Stack growth factor
 * @param shrinkF Stack shrink factor
 */
Stack* makeStackGnS(int cap, float growF, float shrinkF) {
  Stack* stack = (struct Stack*) malloc(sizeof(struct Stack));
  stack->top = -1;
  stack->capacity = cap;
  stack->growthFactor = growF;
  stack->shrinkFactor = shrinkF;
  stack->dynamic = true;
  stack->elements = (Element**) malloc(stack->capacity * sizeof(Element*));
  return stack;
}
