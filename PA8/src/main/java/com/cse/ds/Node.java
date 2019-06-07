/**
 * Name: Darren Wang
 * Userid: cs12sp19kj
 * Email: d5wang@ucsd.edu
 * Sources: writeup, Piazza
 */
package com.cse.ds;

/**
 *
 */
public class Node {
    String city;
    int population;
    
    Node left;
    Node right;
    
    /**
     *
     */
    public Node() {
        this.city = "";
	this.population = 0;
	this.left = null;
	this.right = null;
    }
    
    /**
     *
     *
     * @param city
     * @param population
     */
    public Node(String city, int population) {
        this.city = city;
	this.population = population;
	this.left = null;
	this.right = null;
    }
}
