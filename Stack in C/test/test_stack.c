#include "cutest/CuTest.h"
#include "stack.h"
#include <stdio.h>
#include <stdlib.h>

/*
 * Add your tests here
 * A few sample tests
 * are provided
 */

// Tests for integer equals
void TestOne(CuTest *tc) {
  int two = 2;
  int four = 4;
  CuAssertIntEquals(tc, four, two+two);
}

// Test for string equals
void TestTwo(CuTest *tc) {
  char* lol = "lol";
  char* alsoLol = "lol";
  CuAssertStrEquals(tc, lol, alsoLol);
}

/*
Tests for True and False
pay attention to cleanStack function call as
you will be using it in your every test case
*/
void TestTrueFalse(CuTest *tc) {
  Stack* s = makeStack(10);
  CuAssertTrue(tc, isEmpty(s));
  push(s, 1, "black");
  CuAssertFalse(tc, isEmpty(s));
  cleanStack(s);
}

// Tests for Pointer equals
void TestPtrEquals(CuTest *tc) {
  Element* e = NULL;
  CuAssertPtrEquals(tc, NULL, e);
}

// added tests
// clear
void TestClear(CuTest *tc) {
  Stack* s = makeStack(2);
  push(s, 1, "black");
  push(s, 2, "grey");
  CuAssertFalse(tc, isEmpty(s));
  clear(s);
  CuAssertTrue(tc, isEmpty(s));
  cleanStack(s);
}

// push/pop
void TestPushPop(CuTest *tc) {
  Stack* s = makeStack(1);
  push(s, 1, "black");
  CuAssertFalse(tc, isEmpty(s));
  struct Element* popped = pop(s);
  CuAssertIntEquals(tc, popped->key, 1);
  CuAssertStrEquals(tc, popped->value, "black");
  CuAssertTrue(tc, isEmpty(s));
  cleanStack(s);
}

// isFull
void TestIsFull(CuTest *tc) {
  Stack* s = makeStack(1);
  push(s, 1, "black");
  CuAssertTrue(tc, isFull(s));
  cleanStack(s);
}

// peek
void TestPeek(CuTest *tc) {
  Stack* s = makeStack(1);
  push(s, 1, "black");
  CuAssertIntEquals(tc, peek(s)->key, 1);
  CuAssertStrEquals(tc, peek(s)->value, "black");
  cleanStack(s);
}

// currentSize
void TestCurrentSize(CuTest *tc) {
  Stack* s = makeStack(1);
  push(s, 1, "black");
  CuAssertIntEquals(tc, 1, currentSize(s));
  cleanStack(s);
}

// reverse
void TestReverse(CuTest *tc) {
  Stack* s = makeStack(3);
  push(s, 1, "black");
  push(s, 2, "grey");
  reverse(s);
  struct Element* top = peek(s);
  CuAssertIntEquals(tc, top->key, 1);
  CuAssertStrEquals(tc, top->value, "black");
  cleanStack(s);
}

// pushUnique
void TestPushUnique(CuTest *tc) {
  Stack* s = makeStack(3);
  push(s, 1, "black");
  CuAssertFalse(tc, pushUnique(s, 1, "black"));
  CuAssertTrue(tc, pushUnique(s, 2, "grey"));
  cleanStack(s);
}

// search
void TestSearch(CuTest *tc) {
  Stack* s = makeStack(3);
  push(s, 1, "black");
  push(s, 2, "grey");
  push(s, 3, "off-white");
  CuAssertIntEquals(tc, search(s, 1, "black"), 3);
  cleanStack(s);
}

// getCapacity
void TestGetCapacity(CuTest *tc) {
  Stack* s = makeStack(1);
  CuAssertIntEquals(tc, getCapacity(s), 1);
  cleanStack(s);
}

// multiPop
void TestMultiPop(CuTest *tc) {
  Stack* s = makeStack(5);
  push(s, 1, "black");
  push(s, 2, "grey");
  push(s, 3, "off-white");
  CuAssertIntEquals(tc, currentSize(s), 3);
  struct Element** popped = multiPop(s, 3);
  CuAssertIntEquals(tc, currentSize(s), 0);
  cleanStack(s);
  free(popped);
}

// TestResizable
// test growthFactor
void TestGrowPush(CuTest *tc) {
  Stack* s = makeStackG(4, (float) .5);
  CuAssertIntEquals(tc, getCapacity(s), 4);
  push(s, 1, "black");
  push(s, 2, "grey");
  CuAssertIntEquals(tc, getCapacity(s), 4);
  push(s, 3, "off-white");
  CuAssertIntEquals(tc, getCapacity(s), 8);
  CuAssertIntEquals(tc, currentSize(s), 3);
  cleanStack(s);
}

// test shrinkFactor
void TestShrinkPop(CuTest *tc) {
  Stack* s = makeStackGnS(4, .5, .5);
  CuAssertIntEquals(tc, getCapacity(s), 4);
  push(s, 1, "black");
  push(s, 2, "grey");
  // double stack capacity
  push(s, 3, "off-white");
  CuAssertIntEquals(tc, getCapacity(s), 8);
  push(s, 4, "white");
  CuAssertIntEquals(tc, currentSize(s), 4);
  // halve stack capacity
  struct Element* popped = pop(s);
  CuAssertIntEquals(tc, currentSize(s), 3);
  CuAssertIntEquals(tc, popped->key, 4);
  CuAssertStrEquals(tc, popped->value, "white");
  CuAssertIntEquals(tc, getCapacity(s), 4);
  cleanStack(s);
}

CuSuite* StrUtilGetSuite() {
  CuSuite* suite = CuSuiteNew();

  /** ADD YOUR TESTS HERE **/
  SUITE_ADD_TEST(suite, TestOne);
  SUITE_ADD_TEST(suite, TestTwo);
  SUITE_ADD_TEST(suite, TestTrueFalse);
  SUITE_ADD_TEST(suite, TestPtrEquals);

  // added tests
  SUITE_ADD_TEST(suite, TestClear);
  SUITE_ADD_TEST(suite, TestPushPop);
  SUITE_ADD_TEST(suite, TestIsFull);
  SUITE_ADD_TEST(suite, TestPeek);
  SUITE_ADD_TEST(suite, TestCurrentSize);
  SUITE_ADD_TEST(suite, TestReverse);
  SUITE_ADD_TEST(suite, TestPushUnique);
  SUITE_ADD_TEST(suite, TestSearch);
  SUITE_ADD_TEST(suite, TestGetCapacity);
  SUITE_ADD_TEST(suite, TestMultiPop);
  // resizable tests
  SUITE_ADD_TEST(suite, TestGrowPush);
  SUITE_ADD_TEST(suite, TestShrinkPop);

  return suite;
}

// Don't edit anything below this line

void RunAllTests(void) {
  CuString *output = CuStringNew();
  CuSuite* suite = CuSuiteNew();
  CuSuite* ourSuite = StrUtilGetSuite();

  CuSuiteAddSuite(suite, ourSuite);

  CuSuiteRun(suite);
  CuSuiteSummary(suite, output);
  CuSuiteDetails(suite, output);
  printf("%s\n", output->buffer);

  CuStringDelete(output);
  CuSuiteDelete(suite);
  free(ourSuite);
}

int main(void) {
  RunAllTests();
  return 0;
}
