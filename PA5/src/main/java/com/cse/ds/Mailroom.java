/**
 * Name: Darren Wang
 * Email: d5wang@ucsd.edu
 * Userid: cs12sp19kj
 * Sources: writeup, Piazza, discussion, StackOverflow
 */
package com.cse.ds;

import java.util.*;

/**
 * Coordinates processing and delivering of Deliverable objects using a HashMap
 */
public class Mailroom {

    private HashMap<Deliverable,MyQueue<Deliverable>> deliveryBins;
    private int currentTimestamp;

    private static final int PREFIX_LENGTH = 5;

    /**
     * Initializes a Mailroom object with a HashMap
     * (key = Deliverable zip code, value = MyQueue of Deliverables)
     */
  public Mailroom() {
    this.deliveryBins = new HashMap<Deliverable, MyQueue<Deliverable>>();
    this.currentTimestamp = 0;
  }

  /**
   * Add key Deliverable d into appropriate MyQueue value
   *
   * @param d Deliverable object
   */
  public void registerItem(Deliverable d) throws NullPointerException {
    // null exception
    if (d == null) {
        throw new NullPointerException();
    }

    // set timestamp
    d.setTimestamp(currentTimestamp);
    // key already exists
    if (deliveryBins.containsKey(d)) {
        deliveryBins.get(d).enqueue(d);
    } else {
        deliveryBins.put(d, new MyQueue<Deliverable>());
	deliveryBins.get(d).enqueue(d);
    }
    currentTimestamp++;
  }

  /**
   * Removes and returns Deliverable object with earliest timestamp
   *
   * @return removed Deliverable object
   */
  public Deliverable deliverEarliest() {
    // no Deliverable objects in mailroom
    if (deliveryBins.size() == 0) {
        return null;
    }
    
    // find earliest timestamp
    Deliverable min = (Deliverable) deliveryBins.keySet().toArray()[0];
    for (Deliverable d : deliveryBins.keySet()) {
      MyQueue<Deliverable> q = deliveryBins.get(d);
	if (deliveryBins.get(min).peek().getTimestamp() > q.peek().getTimestamp()) {
	  min = d;
	}
    }
    Deliverable tmp = deliveryBins.get(min).peek();
    deliveryBins.get(min).dequeue();
    // if queue becomes empty
    if (deliveryBins.get(min).size() == 0) {
      deliveryBins.remove(min);
    }
    return tmp;
  }

  /**
   * Removes and returns Deliverable object of key: zip with earliest timestamp
   *
   * @param zip Receiver zip code
   * @return removed Deliverable object
   */
  public Deliverable deliverEarliest(String zip) {
    Deliverable tmp = null;
    // dummy node used to iterate
    Deliverable dummy = new MyMail(1,zip,zip,"");
    // if hashmap has the key
    if (deliveryBins.containsKey(dummy) && deliveryBins.get(dummy).size() > 0) {
      tmp = deliveryBins.get(dummy).peek();
      deliveryBins.get(dummy).dequeue();
      // if queue becomes empty
      if (deliveryBins.get(dummy).isEmpty()) {
	deliveryBins.remove(dummy);
      }
    }
    return tmp;
  }

  /**
   * Returns Deliverable object with earliest timestamp
   *
   * @return earliest timestamp Deliverable object
   */
  public Deliverable checkEarliest() {
    // no Deliverable objects in mailroom
    if (deliveryBins.size() == 0) {
        return null;
    }
    
    // find earliest timestamp
    Deliverable min = (Deliverable) deliveryBins.keySet().toArray()[0];
    for (Deliverable d : deliveryBins.keySet()) {
      MyQueue<Deliverable> q = deliveryBins.get(d);
	if (deliveryBins.get(min).peek().getTimestamp() > q.peek().getTimestamp()) {
	  min = d;
	}
    }
    return deliveryBins.get(min).peek();
  }

  /**
   * Returns earliest timestamp Deliverable with key: zip
   *
   * @param zip Receiver zip code
   * @return earliest timestamp Deliverable with zip
   */
  public Deliverable checkEarliest(String zip) {
    // dummy node used to iterate
    Deliverable dummy = new MyMail(1,zip,zip,"");
    // if hashmap has the key
    if (deliveryBins.containsKey(dummy)) {
      Deliverable tmp = deliveryBins.get(dummy).peek();
      return tmp;
    }
    return null;
  }

  /**
   * Removes and returns all Deliverable objects in order of earliest timestamp
   *
   * @return removed ArrayList of Deliverable objects
   */
  public ArrayList<Deliverable> deliverAll() {
    ArrayList<Deliverable> removed = new ArrayList<Deliverable>();
    while (deliveryBins.size() > 0) {
      removed.add(this.deliverEarliest());
    }
    return removed;
  }

  /**
   * Removes and returns all Deliverable objects in order of earliest timestamp
   * with key: zip
   *
   * @param zip Receiver zip code
   * @return removed ArrayList of Deliverable objects
   */
  public ArrayList<Deliverable> deliverAll(String zip) {
    // dummy node used to iterate
    Deliverable dummy = new MyMail(1,zip,zip,"");
    // store removed Deliverables
    ArrayList<Deliverable> removed = new ArrayList<Deliverable>();
    
    while (deliveryBins.containsKey(dummy) && deliveryBins.get(dummy).size() > 0) {
      Deliverable item = this.deliverEarliest(zip);
      removed.add(item);
    }
    return removed;
  }

  /**
   * Removes and returns all Deliverable objects in order of earliest timestamp
   * within a weight capacity
   *
   * @param capacity weight capacity
   * @return removed ArrayList of Deliverable objects
   */
  public ArrayList<Deliverable> deliverByWeight(int capacity) {
    // store removed Deliverables
    ArrayList<Deliverable> removed = new ArrayList<Deliverable>();
    int weight = 0;

    while (weight < capacity && deliveryBins.size() > 0) {
      Deliverable item = this.deliverEarliest();
      removed.add(item);
      weight+=item.getWeight();
    }
    return removed;
  }

  /**
   * Removes and returns all Deliverable objects with key: zip in order of
   * earliest timestamp and within a weight capacity
   *
   * @param capacity weight capacity
   * @param zip Receiver zip code
   * @return removed ArrayList of Deliverable objects
   */
  public ArrayList<Deliverable> deliverByWeight(int capacity, String zip) {
    // dummy node to iterate
    Deliverable dummy = new MyMail(1,zip,zip,"");
    // store removed Deliverables
    ArrayList<Deliverable> removed = new ArrayList<Deliverable>();
    int weight = 0;

    while (deliveryBins.containsKey(dummy) && deliveryBins.get(dummy).size() > 0
           && weight < capacity) {
      Deliverable item = this.deliverEarliest(zip);
      removed.add(item);
      weight+=item.getWeight();
    }
    return removed;
  }

  /**
   * Merges all queues stored by key with param prefix: zip code
   *
   * @param prefix zip code prefix key to merge
   */
  public void mergeBins(String prefix) {
    ArrayList<Deliverable> removedDeliverables = new ArrayList<Deliverable>();
    ArrayList<Deliverable> removedKeys = new ArrayList<Deliverable>();
    // create five digit new zip code
    String newZip = prefix;
    for (int i = prefix.length(); i < PREFIX_LENGTH; i++) {
      newZip+="-";
    }
    // extract matching Deliverables
    for (Deliverable d : deliveryBins.keySet()) {
      if (d.getZipCode().startsWith(prefix)) {
        MyQueue<Deliverable> q = deliveryBins.get(d);
	// empty the matching Deliverable's Queue
	while (!q.isEmpty()) {
	  removedDeliverables.add(q.dequeue());
	}
	removedKeys.add(d);
      }
    }
    // remove empty keys
    for (int i = 0; i < removedKeys.size(); i++) {
      deliveryBins.remove(removedKeys.get(i));
    }

    // sort the Deliverable objects by timestamp
    this.bubbleSort(removedDeliverables);

    // add sorted Deliverables into new queue
    MyQueue<Deliverable> newQueue = new MyQueue<Deliverable>();
    for (int i = 0; i < removedDeliverables.size(); i++) {
      newQueue.enqueue(removedDeliverables.get(i));
    }
    
    // put new key-value queue pair into hashmap
    Deliverable dummy = new MyMail(0,newZip,newZip,"");
    deliveryBins.put(dummy,newQueue);
  }

  /**
   * Helper method to sort new Deliverables in mergeBins, implements BubbleSort
   * for an array list
   *
   * @param arrList array list of Deliverables to sort
   */
  public void bubbleSort(ArrayList<Deliverable> arrList) {
    int n = arrList.size(); 
      for (int i = 0; i < n-1; i++) {
        for (int j = 0; j < n-i-1; j++) {
          if (arrList.get(j).getTimestamp() > arrList.get(j+1).getTimestamp()) {
            // swap j+1 and i 
            Deliverable temp = arrList.get(j); 
            arrList.set(j,arrList.get(j+1)); 
            arrList.set(j+1,temp); 
	  }
	}
      }
  }

}
