/**
 * Name: Darren Wang
 * Userid: cs12sp19kj
 * Email: d5wang@ucsd.edu
 * Sources: writeup, Piazza
 */
package com.cse.ds;

import java.util.List;

import java.util.*;

/**
 *
 */
public class BinarySearchTree {
    
    private ArrayList<Node> nodes;
    private Node root;
    private ArrayList<String> traversal = new ArrayList<String>();

    //============== BALANCED TREE CREATION ====================//
    /**
     *
     *
     * @param cities
     * @param population
     */
    public BinarySearchTree(String[] cities, int[] population) {
        //TODO: Create a balanced BST by starting at mid node and creating tree recursively.
        //Use trim on cities names before adding
	
	// create the Node object
	nodes = new ArrayList<Node>();
	for (int i = 0; i < nodes.size(); i++) {
	    String trimmed = cities[i].trim();
	    nodes.set(i, new Node(trimmed, population[i]));
	}

        // find root node
	int mid = nodes.size() / 2;
	if (nodes.size() > 0) {
	    root = nodes.get(mid);
	    getMidLeft(nodes, 0, mid);
	    getMidRight(nodes, mid, nodes.size());
	}
    }
    
    /**
     *
     *
     * @param filename
     * @param num_lines
     */
    public BinarySearchTree(String filename, int num_lines) {
        //TODO: Create a balanced BST from the input.txt
        //Use trim on cities names before adding
    }
    
    //============== COMMON TREE OPERATIONS ====================//
    /**
     *
     *
     * @param city
     * @param population
     */
    public void addCity(String city, int population) {
        // empty
	if (nodes.size() == 0) {
	    root = new Node(city.trim(), population);
	    return;
	}
	
        Node node = new Node(city.trim(), population);
	int midpoint = (nodes.size() - 1) / 2;
	Node mid = nodes.get(midpoint);
        // compare with root
	if (population <= mid.population) {
	    if (mid.left != null) {
	        mid = mid.left;
		addCity(city, population);
	    } else {
	        mid.left = node;
	    }
	}
	else if (population > mid.population) {
	    if (mid.right != null) {
	        mid = mid.right;
		addCity(city, population);
	    } else {
	        mid.right = node;
	    }
	}
    }

    /**
     *
     *
     * @return
     */
    public int getMaxDepth() {
        // null case
	if (root == null) {
	    return 0;
	}
	// left path
	int leftPath = 1;
	int rightPath = 1;
	Node current = root;
	while (current.left != null) {
	    leftPath++;
	    current = current.left;
	}
	current = root;
	while (current.right != null) {
	    rightPath++;
	    current = current.right;
	}
	return Math.max(leftPath, rightPath);
    }

    /**
     *
     *
     * @return
     */
    public int getMaxWidth() {
        // null case
	if (root == null) {
	    return 0;
	}
	int leftCounter = 0;
	int rightCounter = 0;
	Node current = root;
        while (current.left != null) {
	    leftCounter++;
	    current = current.left;
	}
	current = root;
	while (current.right != null) {
	    rightCounter++;
	    current = current.right;
	}
	return leftCounter + rightCounter;
    }

    /**
     *
     *
     * @return
     */
    public String getSmallestCity() {
        Node current = root;
	// loop to find leftmost leaf
	while (current.left != null) {
	    current = current.left;
	}
	return current.city;
    }

    /**
     *
     *
     * @return
     */
    public String getLargestCity() {
        Node current = root;
	// loop to find rightmost leaf
	while (current.right != null) {
	    current = current.right;
	}
	return current.city;
    }
    
    //============== TREE TRAVERSALS ====================//
    /**
     *
     *
     * @return
     */
    public List<String> getPreOrderTraversal() {
        //TODO: Get preorder traversal.
        return null;
    }

    /**
     *
     *
     * @return
     */
    public List<String> getPostOrderTraversal() {
        //TODO: Get postorder traversal.
        return null;
    }

    public void printInOrder(Node node) {
        if (node == null) {
	    return;
	}
	// recursive call
	printInOrder(node.left);
	traversal.add(node.city);
	printInOrder(node.right);
    }
    
    /**
     *
     *
     * @return
     */
    public List<String> getSortedCities() {
        // reset instance variable
	traversal.clear();
        printInOrder(root);
	return traversal;
    }

    /**
     *
     *
     * @return
     */
    public List<List<String>> getLevelWiseCities() {
        //TODO: Get cities level wise
        return null;
    }

    /**
     *
     *
     * @return
     */
    public List<List<String>> getTwistyTraversal() {
        return null;
    }
    
    //============== TREE VIEWS ====================//
    /**
     *
     *
     * @return
     */
    public List<String> getRightView() {
        //TODO: Get right side view of BST
        return null;
    }

    /**
     *
     *
     * @return
     */
    public List<String> getLeftView() {        
        //TODO: Get left side view of BST
        return null;
    }

    /**
     *
     *
     * @return
     */
    public List<String> getTopView() {
        //TODO: Get top view of BST
        return null;
    }

    /**
     *
     *
     * @return
     */
    public List<String> getBottomView() {
        //TODO: Get bottom view of BST
        return null;
    }
    
    //============== SPECIAL TREE OPERATIONS ====================//
    /**
     *
     *
     * @return
     */
    public int getBSTilt() {
        //TODO: Get the tilt of BST
        return 0;
    }

    /**
     *
     *
     * @return
     */
    public List<String> getAllPaths() {
        //TODO: Get all the root to leaf paths in the tree
        //HINT: iterate left first and then iterate right
        return null;
    }

    /**
     *
     *
     * @return
     */
    public void flattenToLL() {
        //TODO: Flatten BST to a Linked List like structure
    }
    
    //============== TREE VISUALIZATION ====================//
    @Override
    public String toString() {
        // TODO: Generate tree representation as give in writeup
        return null;
    }

    public void getMidRight(ArrayList<Node> nodes, int left, int right) {
        int midpoint = (left + right) / 2;
        // base case
	if (left == right) {
	    return;
	} else {
	    nodes.get(left).right = nodes.get(midpoint);
	    getMidRight(nodes, midpoint, right);
	    getMidLeft(nodes, midpoint, (midpoint + left) / 2);
	}
    }

    public void getMidLeft(ArrayList<Node> nodes, int left, int right) {
        int midpoint = (left + right) / 2;
        // base case
	if (left == right) {
	    return;
	} else {
	    nodes.get(right).left = nodes.get(midpoint);
	    getMidLeft(nodes, left, midpoint);
	    getMidRight(nodes, midpoint, (midpoint + right) / 2);
	}
    }
    
}
