/**
 * Name: Darren Wang
 * Email: d5wang@ucsd.edu
 * Userid: cs12sp19kj
 * Sources: writeup, Piazza, discussion
 */
package com.cse.ds;

/**
 * Extends abstract class Deliverable, represents a single Package object
 */
public class MyPackage<E> extends Deliverable {

  private E content;

  /**
   * Initializes a MyPackage object
   *
   * @param id ID of package
   * @param fromAddress Sender address
   * @param toAddress Receiver address
   * @param content Package content
   * @param weight Package weight
   */
  public MyPackage(int id, String fromAddress, String toAddress, E content,
                   int weight) {
    this.id = id;
    this.fromAddress = fromAddress;
    this.toAddress = toAddress;
    this.weight = weight;
    this.content = content;
    this.timestamp = -1;
  }
  
  /**
   * Returns content within package
   *
   * @return content
   */
  public E getContent() {
    return this.content;
  }

  /**
   * Returns zip code of receiver address
   *
   * @return receiver address zip code
   */
  @Override
  public String getZipCode() {
        // first 5 chars: zipcode
	String zip = this.toAddress.substring(0,5);
        return zip;
  }

}
