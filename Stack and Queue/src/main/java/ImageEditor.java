/**
 * Name: Darren Wang
 * Email: d5wang@ucsd.edu
 * Userid: cs12sp19kj
 * Sources: writeup, Piazza, StackOverflow
 */
import java.util.*;

/**
 * Implements basic functionalities of an image editor for a gray-scale image
 * using Stack class
 */
public class ImageEditor {

    int[][] mat;
    Stack<int[][]> undoStack;
    Stack<int[][]> redoStack;

    /**
     * Initializes an ImageEditor with initial image data
     *
     * @param mat image represented as a 2d array, matrix of numbers, pixels
     */
    public ImageEditor(int[][] mat) {
        this.mat = mat;
        // initialize two stacks to store history of states of the image
        this.undoStack = new Stack<int[][]>(10);
        this.redoStack = new Stack<int[][]>(10);
    }

    /**
     * Deletes value at location i,j, set equal to 0
     *
     * @param i row number
     * @param j column number
     */
    public void delete(int i, int j) throws IndexOutOfBoundsException {
        // given location does not exist
        if (i > mat.length || j > mat[0].length || i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
	int[][] tmp = this.deepCopy(mat);
	// store state before delete action
	undoStack.push(tmp);
	redoStack.clear();
        // set value at location i,j to 0
        mat[i][j] = 0;
    }

    /**
     * Blurs value at location i,j, multiply current pixel value with
     * blurFactor, between 0 and 1, exclusive
     *
     * @param i row number
     * @param j column number
     * @param blurFactor decimal value to multiply by
     */
    public void blur(int i, int j, float blurFactor) throws
    IndexOutOfBoundsException, IllegalArgumentException {
        // given location does not exist
        if (i > mat.length || j > mat[0].length || i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
	// blurFactor not in range
	if (blurFactor >= 1 || blurFactor <= 0) {
	    throw new IllegalArgumentException();
	}
	int[][] tmp = this.deepCopy(mat);
	// store state before blur action
	undoStack.push(tmp);
	redoStack.clear();
	// set value at location i,j to new value
	mat [i][j] = (int) (mat[i][j] * blurFactor);
    }

    /**
     * Sharpens value at location i,j, multiply current pixel value with
     * sharpenFactor, greater than or equal to 1
     *
     * @param i row number
     * @param j column number
     * @param sharpenFactor decimal value to multiply by
     */
    public void sharpen(int i, int j, float sharpenFactor) throws 
    IndexOutOfBoundsException, IllegalArgumentException {
        // given location does not exist
        if (i > mat.length || j > mat[0].length || i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
	// sharpenFactor not in range
	if (sharpenFactor < 1) {
	    throw new IllegalArgumentException();
	}
	int[][] tmp = this.deepCopy(mat);
	// store state before sharpen action
	undoStack.push(tmp);
	redoStack.clear();
	// set value at location i,j to new value
	int newValue = (int) (mat[i][j] * sharpenFactor);
	if (newValue > 255) {
	    mat[i][j] = 255;
	} else {
	    mat[i][j] = newValue;
	}
    }

    /**
     * Reverse effect of last executed edit function: delete, blue, sharpen, or
     * redo
     *
     * @return undo action successful or not
     */
    public boolean undo() {
        // no edit function to be undone
	if (undoStack.isEmpty()) {
	    return false;
	}
	int[][] tmp = this.deepCopy(mat);
        // store current state to revert back to
        redoStack.push(tmp);
        // reverse effect of last executed edit function
	mat = undoStack.pop();
	return true;
    }

    /**
     * Reverse effect of last undo function
     *
     * @return redo action successful or not
     */
    public boolean redo() {
        // no undo command to revert back to
	if (redoStack.isEmpty()) {
	    return false;
	}
	int[][] tmp = this.deepCopy(mat);
	// store current state to revert back to
	undoStack.push(tmp);
	// reverse effect of last executed undo function
	mat = redoStack.pop();
	return true;
    }

    /**
     * Returns the current 2d image array
     *
     * @return 2D image array
     */
    public int[][] getImage() {
        return this.mat;
    }

    /**
     * Helper method to help with pushing to undo and/or redo stacks, push a
     * copy of 2D image array with different address than original 2D array
     *
     * @param array 2D array to implement deep copy on
     * @return new copied array with different address than param array
     */
    private int[][] deepCopy(int[][] array) {
        // avoid null pointers
        if (array == null) {
            return null;
        }
	// initialize to a new address and copy over param array's elements
        int[][] result = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            result[i] = Arrays.copyOf(array[i], array[i].length);
        }
        return result;
    }

}
