// import com.cse.ds.Stack.java;
import java.util.*;

public class ImageEditor {

    int[][] mat;
    Stack<Integer[][]> undoStack;
    Stack<Integer[][]> redoStack;

    public ImageEditor(int[][] mat) {
        this.mat = mat;
        // initialize two stacks to store edit history
        this.undoStack = new Stack<Integer[][]>(0);
        this.redoStack = new Stack<Integer[][]>(0);
    }

    void delete(int i, int j) throws IndexOutOfBoundsException{
        // given location does not exist
        if (i > mat.length || j > mat[0].length || i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
        // set value at location i,j to 0
        mat[i][j] = 0;
    }

    void blur(int i, int j, float blurFactor) throws IndexOutOfBoundsException, IllegalArgumentException{
        //To be implemented
    }

    void sharpen(int i, int j, float sharpenFactor) throws IndexOutOfBoundsException, IllegalArgumentException{
        //To be implemented
    }

    boolean undo(){
        //To be implemented
            return true; // to be Changed
    }

    boolean redo(){
        //To be implemented
            return true; // to be Changed
    }

    int[][] getImage(){
        return this.mat;
    }

}
