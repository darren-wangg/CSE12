package com.cse.ds;
import java.util.*;

/** ListIterator implementation */
public class MyPlayListIterator<E> implements MyListIterator<E> {

    private Song<E> dummy;
    private Song<E> prevSong;
    private Song<E> nextSong;
    private int index;
    private boolean traverseForward;
    private boolean traverseBackward;
    private MyPlayList<E> playlist;

    /**
      *
      */
    public MyPlayListIterator(Song<E> dummy) {
        playlist = new MyPlayList<E>();
	this.dummy = dummy;
    }

    @Override
    public boolean hasNext() {
        return nextSong != null;
    }

    @Override
    public E next() {
        if (!this.hasNext()) {
	    throw new NoSuchElementException();
	}
	E tmp = nextSong.getElement();
	nextSong = nextSong.getNext();

	traverseForward = true;
	traverseBackward = false;
	index++;
	return tmp;
    }

    @Override
    public boolean hasPrevious() {
        return prevSong != null;
    }

    @Override
    public E previous() {
        if (!this.hasPrevious()) {
	    throw new NoSuchElementException();
	}
	E tmp = prevSong.getElement();
	prevSong = prevSong.getPrev();

	traverseBackward = true;
	traverseForward = false;
	index--;
	return tmp;
    }

    @Override
    public int nextIndex() {
        return 0;
    }

    @Override
    public int previousIndex() {
        return 0; // XXX-CHANGE-XXX
    }

    @Override
    public void set(E o) {
        // cannot set to null
        if (o == null) {
	    throw new NullPointerException();
	}
	// must be traversing
	if (!traverseForward && !traverseBackward) {
	    throw new IllegalStateException();
	}

	
    }

}
