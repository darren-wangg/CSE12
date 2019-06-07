/**
 * Name: Darren Wang
 * Email: d5wang@ucsd.edu
 * Userid: cs12sp19kj
 * Sources: writeup, Piazza, discussion
 */
package com.cse.ds;

/**
 * Extends abstract class Deliverable, represents a single Mail object
 */
public class MyMail extends Deliverable {

    private String message;

    /**
     * Initializes a MyMail object
     *
     * @param id ID of mail
     * @param fromAddress Sender address
     * @param toAddress Receiver address
     * @param message Mail message
     */
    public MyMail(int id, String fromAddress, String toAddress,
                  String message) {
        this.id = id;
	this.weight = 1;
	this.fromAddress = fromAddress;
	this.toAddress = toAddress;
        this.message = message;
	this.timestamp = -1;
    }

    /**
     * Returns message within mail
     *
     * @return message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns zip code of receiver address
     *
     * @return receiver address zip code
     */
    @Override
    public String getZipCode() {
        // last 5 chars: zipcode
	int length = this.toAddress.length();
	String zip = this.toAddress.substring(length - 5, length);
        return zip;
    }

}
