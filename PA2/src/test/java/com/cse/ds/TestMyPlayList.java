package com.cse.ds;
import org.junit.*;
import org.junit.runners.MethodSorters;



@FixMethodOrder(MethodSorters.JVM)
public class TestMyPlayList {
    static MyPlayList obj = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        obj = new MyPlayList();
    }
    @Before
    public void populatePlayList(){
        obj.clear();
        for(int i=0;i<10;i++){
            obj.add(i);
        }
    }
    @Test
    public void testNumberOfSongs() {
        Assert.assertEquals(obj.size(), 10);
    }

    @Test
    public void testAddSongs() {
        obj.add(10);
        Assert.assertEquals(obj.get(10), 10);
    }


    /** @Test
    public void testIterator() {
        MyListIterator<Song> iter = obj.myListIterator();
        for(int i=0;i<obj.size()/2;i++){
            iter.next();
        }
        Assert.assertEquals(iter.next(), 5);
    } **/

    @Test(expected = IndexOutOfBoundsException.class)
    public void testException() {
        obj.set(100,1);
    }

    @Test
    public void testClear() {
        obj.clear();
	Assert.assertEquals(obj.size(), 0);
    }

    @Test
    public void testGet() {
        obj.clear();
	obj.add(0);
	Assert.assertEquals(obj.get(0), 0);
    }
    
    @Test (expected = NullPointerException.class)
    public void testAddNull() {
        obj.add(null);
    }

    @Test
    public void testAddAtIndex() {
        obj.clear();
	obj.add(0);
	obj.add(1);
	obj.add(2);
	obj.add(1,10);
	obj.add(1,20);
	Assert.assertEquals(obj.get(1),20);
	Assert.assertEquals(obj.size(),5);
    }
    
    @Test
    public void testAddAtIndexTail() {
        obj.clear();
	obj.add(0,10);
	Assert.assertEquals(obj.get(0),10);
    }
    
}
