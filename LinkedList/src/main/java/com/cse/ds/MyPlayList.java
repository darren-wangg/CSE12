package com.cse.ds;
import java.io.*;
import java.util.Random;

/**
  *
  */
import java.util.*;

public class MyPlayList<E> extends AbstractList<E> {

    private int numSongs;
    Song<E> dummy;

    //  Implementation of the MyPlayList Class

    /** Only 0-argument constructor is define */
    public MyPlayList()
    {
    	dummy = new Song<E>(null);
	dummy.setPrev(null);
	dummy.setNext(null);
	numSongs = 0;
    }

    /**
      * Returns the size of the playlist
      *
      * @return numSongs
      */
    @Override
    public int size()
    {
        return numSongs;
    }

    /**
      * Returns Song node at given index
      *
      * @param index position index
      * @return Song node
      */
    public Song<E> getNth(int index) {
	// index out of bounds
	if (index < 0 || index >= numSongs) {
	    throw new IndexOutOfBoundsException();
	}

	Song<E> curr = dummy.getNext();
	for (int i = 0; i <= index; i++) {
	    curr = curr.getNext();
	}
	return curr;
    }

    /**
      * Gets Song data at position index in the playlist
      *
      * @param index index of Song in the list
      * @return Song data at position index
      */
    @Override
    public E get(int index)
    {
        // index out of bounds
	if (index < 0 || index >= numSongs) {
	    throw new IndexOutOfBoundsException();
	}
	return getNth(index).getElement();
    }

    /**
      * Add Song node with data element into list at given index
      *
      * @param index position index
      * @param element Song data
      */
    @Override
    public void add(int index, E element)
    {
	// cannot add song with null data
	if (element == null) {
	    throw new NullPointerException();
	}
	// index out of bounds
	if (index > numSongs || index < 0) {
	    throw new IndexOutOfBoundsException();
	}

	// add Song at index in list, reset links
	Song<E> newSong = new Song<E>(element);
	Song<E> oldSong;
	// add to end of list
	if (index == numSongs) {
	    this.add(element);
	}
	else {
	    oldSong = getNth(index);
	    oldSong.getPrev().setNext(newSong);
	    newSong.setPrev(oldSong.getPrev());
	    newSong.setNext(oldSong);
	    oldSong.setPrev(newSong);
	}
	numSongs++;
    }

    /**
      * Add new song to end of list
      *
      * @param element Song data
      * @return true
      */
    @Override
    public boolean add(E element)
    {
    	// cannot add song with null data
	if (element == null) {
	    throw new NullPointerException();
	}
	// add song to end of list, set links
    	Song<E> newSong = new Song<E>(element);
	Song<E> currSong = dummy;
	while (currSong.next != null) {
	    currSong = currSong.next;
	}
	// now at tail, set new links
	currSong.setNext(newSong);
	newSong.setPrev(currSong);
	newSong.setNext(null);

	numSongs++;
	return true;
    }

    /**
      * Set data value for Song node at position index
      *
      * @param index position index
      * @param element Song data
      * @return old Song data
      */
    @Override
    public E set(int index, E element)
    {
	// if data is null
	if (element == null) {
	    throw new NullPointerException();
	}
	// if index out of bounds
	if (index >= numSongs || index < 0) {
	    throw new IndexOutOfBoundsException();
	}

	Song<E> curr = getNth(index);
	E data = curr.getElement();
	curr.setElement(element);
	return data;
    }

    /**
      * Removes Song node from position index
      *
      * @param index position index
      * @return removed Song node data
      */
    @Override
    public E remove(int index)
    {
	// if index out of bounds
	if (index >= numSongs || index < 0) {
	    throw new IndexOutOfBoundsException();
	}

	Song<E> curr = getNth(index);
	E data = curr.getElement();
	curr.remove();
	numSongs--;
	return data;
    }

    /**
      * Removes all Song nodes from playlist
      */
    @Override
    public void clear()
    {
        // empty playlist
	if (numSongs == 0) {
	    return;
	}
	// remove first Song node
	Song<E> firstNode = dummy.getNext();
	firstNode.setNext(null);
	numSongs = 0;
    }

    /**
      * Returns whether playlist is empty or not
      *
      * @return whether numSongs is zero
      */
    @Override
    public boolean isEmpty()
    {
        return numSongs == 0;
    }

    /**
      * Swaps data for two Song nodes in Random number generated position
      * indices
      */
    public void shuffle()
    {
	// create Random object and two pseudorandom numbers
	Random rand = new Random();
	rand.setSeed(1234);
	int rand1 = rand.nextInt(numSongs);
	int rand2 = rand.nextInt(numSongs);

	// swap two Song nodes at rand1 and rand2
	// change pointers rand1
	getNth(rand1).setNext(getNth(rand2 + 1));
	getNth(rand1).setPrev(getNth(rand2 - 1));
	// change pointers rand2
	getNth(rand2).setNext(getNth(rand1 + 1));
	getNth(rand2).setPrev(getNth(rand1 - 1));
	// change pointers around rand1
	getNth(rand1 + 1).setPrev(getNth(rand2));
	getNth(rand1 - 1).setNext(getNth(rand2));
	// change pointers around rand2
	getNth(rand2 + 1).setPrev(getNth(rand1));
	getNth(rand2 - 1).setNext(getNth(rand2));
    }

    /**
      * Reverses the playlist
      */
    public void reverse()
    {
        // i, traverse forwards
	for (int i = 0; i < numSongs/2; i++) {
	    // j, traverse backwards
	    for (int j = numSongs-1; j > numSongs/2; j--) {
	        Song<E> tmp = getNth(i);
	        getNth(i).setElement(getNth(j).getElement());
		getNth(j).setElement(tmp.getElement());
	    }
	}
    }

    /**
      * Returns MyPlayListIterator
      *
      * @return MyPlayListIterator
      */
    public MyListIterator<E> myListIterator() {
        return new MyPlayListIterator<E>(dummy);
    }

}
