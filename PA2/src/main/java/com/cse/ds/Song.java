package com.cse.ds;
import java.util.*;

/**
  *
  */
public class Song<E> {

    E data;
    Song next;
    Song prev;

    /** Constructor to create singleton Song */
    public Song(E element)
    {
    	this.data = element;
    }

    /** Constructor to create singleton link it between previous and next
     *   @param element Element to add, can be null
     *   @param prevSong predecessor Song, can be null
     *   @param nextSong successor Song, can be null
     */
    public Song(E element, Song prevSong, Song nextSong)
    {
    	this.data = element;
	this.prev = prevSong;
	this.next = nextSong;
    }

    /** Remove this Song from the list. Update previous and next Songs */
    public void remove()
    {
    	// null Node
	if (this.data == null) {
		return;
	}

	// delete Node and its links
    	this.data = null;
	// replace links
	this.prev.next = this.next;
	this.next.prev = this.prev;
    }

    /** Set the previous Song in the list
     *  @param p new previous Song
     */
    public void setPrev(Song p)
    {
    	this.prev = p;
    }

    /** Set the next Song in the list
     *  @param n new next Song
     */
    public void setNext(Song n)
    {
    	this.next = n;
    }

    /** Set the element
     *  @param e new element
     */
    public void setElement(E e)
    {
    	this.data = e;
    }

    /** Accessor to get the next Song in the list
     *
     * @return next Song node
     */
    public Song getNext()
    {
        return this.next;
    }

    /** Accessor to get the prev Song in the list
     *
     * @return previous Song node
     */
    public Song getPrev()
    {
        return this.prev;
    }

    /** Accessor to get the Songs Element
     *
     * @return Song node's data of type E
     */
    public E getElement()
    {
    	return this.data;
    }
}
