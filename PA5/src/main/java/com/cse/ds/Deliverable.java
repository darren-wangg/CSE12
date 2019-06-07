/**
 * Name: Darren Wan
 * Email: d5wang@ucsd.edu
 * Userid: cs12sp19kj
 * Sources: writeup, Piazza, discussion
 */
package com.cse.ds;

/**
 * Abstract class, acts as data type for populating implemented queue object
 */
public abstract class Deliverable {

    protected int id;
    protected int weight;
    protected int timestamp;
    protected String fromAddress;
    protected String toAddress;

    /**
     * Returns id number of deliverable
     *
     * @return id number
     */
    public int getId() { return id; }

    /**
     * Returns weight of deliverable
     *
     * @return weight
     */
    public int getWeight() { return weight; }

    /**
     * Returns timestamp of deliverable
     *
     * @return timestamp
     */
    public int getTimestamp() { return timestamp; }

    /**
     * Sets timestamp of deliverable
     *
     * @param timestamp timestamp of deliverable to set to
     */
    public void setTimestamp(int timestamp) { this.timestamp = timestamp; }

    /**
     * Returns sender address of deliverable
     *
     * @return sender address
     */
    public String getFromAddress() { return fromAddress; }

    /**
     * Returns receiver address of deliverable
     *
     * @return receiver address
     */
    public String getToAddress() { return toAddress; }

    /**
     * Returns receiver zip code of deliverable, to be implemented in
     * subclasses
     *
     * @return receiver zip code
     */
    public abstract String getZipCode();

    /**
     * Returns hashcode of deliverable, using zip code as a string
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return this.getZipCode().hashCode();
    }

    /**
     * Returns whether this' and param obj's receiver zip code are equal
     *
     * @param obj obj to compare to
     * @return whether two object's receiver zip codes are equal or not
     */
    @Override
    public boolean equals(Object obj) {
        Deliverable comp = (Deliverable) obj;
        return this.getZipCode().equals(comp.getZipCode());
    }

}
