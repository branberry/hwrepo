import java.lang.*;
import java.util.Random;

/**
 *  @author Brandon Ly 
 *  CSC 320A
 * 
 *  This class is an implementation of Heap Sort from CLRS
 *  This implementation will sort arrays of integers
 * 
 *  HeapSort sorts an array by first calling buildMaxHeap and then swaps the max 
 *  to the end of the array.  Then maxHeapify is called on the subarray from 1 to n-1 until 
 *  every elemented is swapped to its proper position.
 * 
 *  The methods in the class are:
 *  - int[] createRandomIntArray(int size)
 *  - int parent(int i)
 *  - int left(int i)
 *  - int right(int i)
 *  - int swap(int[] A, int i, int j)
 *  - int maxHeapify(int[] A, int i)
 *  - int maxHeapify(int[] A, int i, int n)
 *  - int buildMaxHeap(int[] A)
 *  - int sort(int[] A)
 */
public class HeapSort {
    /**
     * fills the array with random numbers 
     * 
     * @param size is the size of the random array
     * @return returns an array filled with random values
     */
    public static int[] createRandomIntArray(int size) {
        int[] answer = new int[size + 1];
        Random generator = new Random();

        for(int i = 1; i < size + 1; i++) {
            answer[i] = generator.nextInt(100) + 1;
        }
        return answer;
    }

    /**
     * @param i is the index that we want to find the parent of
     * @return the index of the parent
     */
    public static int parent(int i) {
        return (int) Math.floor(i/2);
    }
    /**
     * @param i is the index that we want to find the left child of
     * @return the index of the left child
     */
    public static int left(int i) {
        return i * 2;
    }
    /**
     * @param i is the index we want to find the right child of
     * @return the index of the right child
     */
    public static int right(int i) {
        return i * 2 + 1;
    }

    /**
     * swaps two values in the array
     * @param A is the array that will have its value swapped
     * @param i is the first index
     * @param j is the second index
     * @return the count of the operations that were required to complete the method
     */
    public static int swap(int[] A, int i, int j) {
        int temp;
        int count = 0;

        temp = A[i];
        count++;
        A[i] = A[j];
        count++;
        A[j] = temp;
        count++;

        return count;
        
    }

    /**
     * swims value at index i to the correct position if it is not already.  If not, it swaps the index of either the left or right child that contains a value larger than A[i]
     * the function then recursively calls itself to make sure the rest of the heap is max-heapified.
     * @param A the array containing the heap
     * @param i the index of the heap that we are max-heapifying
     * @return the count of operations were required to complete the method
     */
    public static int maxHeapify(int[] A, int i) {
        int count = 0;

        int l = left(i);
        count++;
        int r = right(i);
        count++;
        int largest;

        if(l < A.length && A[l] > A[i]) {
            largest = l;
            count++;
        } else {
            largest = i;
            count++;
        }

        if(r < A.length && A[r] > A[largest]) {
            largest = r;
            count++;
        }

        if(largest != i) {
            swap(A,i,largest);
            count += maxHeapify(A,largest);
        }
        return count;
    }

    /**
     * swims value at index i to the correct position if it is not already.  If not, it swaps the index of either the left or right child that contains a value larger than A[i]
     * the function then recursively calls itself to make sure the rest of the heap is max-heapified. The additional argument allows for only a sub-portion of the heap to be used in the HeapSort method
     * Since heapsort puts the larget value at the end of the array, we do not want to use reheapify the array with the largest value, rather, the subbarray from A[1] to n-1 and so forth.
     * @param A the arry containing the heap
     * @param i the index of the heap we are max-heapifying
     * @param n the index of the heap that we want to heapify up to
     * @return returns the count of operations that were required to complete the method
     */
    public static int maxHeapify(int[] A, int i, int n) {
        int count = 0;

        int l = left(i);
        count++;
        int r = right(i);
        count++;
        int largest;

        if(l <= n && A[l] > A[i]) {
            largest = l;
            count++;
        } else {
            largest = i;
            count++;
        }

        if(r <= n && A[r] > A[largest]) {
            largest = r;
            count++;
        }

        if(largest != i) {
            count += swap(A,i,largest);
            count += maxHeapify(A,largest,n);
        }

        return count;
    }
    /**
     * builds a max heap by calling maxHeapify on the heap n/2 times as it would be unnecessary to call maxHeapify on leaf nodes.
     * @param A the array we are turning into a max heap
     * @return returns the count of operations that are required to complete the method
     */
    public static int buildMaxHeap(int[] A) {
        int count = 0;
        for(int i = (int) Math.floor(A.length/2); i >= 1; i--) {
            count += maxHeapify(A,i);
        }
        return count;
    }
    /**
     * The heapsort function
     * @param A the array we are sorting 
     * @return the count of operations for heapsort
     */
    public static int sort(int[] A) {
        int count = 0;

        count += buildMaxHeap(A);

        for(int i = A.length - 1; i >= 1; i--) {
            count += swap(A,1,i);
            count += maxHeapify(A,1,i-1);
        }

        return count;
    }

    public static void main(String[] args) {
        /*
            Initializing test arrays of varying sizes to sort with insertion sort from n = 10 up to n = 100000
            The array average holds the values the number of operations on average each array from n = 10 to n = 100000
        */ 
        int[][] Arrays = new int[5][];
        float[] avg = new float[5];

        /* 
            Fills the arrays with varying sizes of n.
            the variable, j, holds the values for the different sizes of n ranging from 10 to 100000
        */
        int j = 1;
        for(int i = 0; i < Arrays.length; i++) {
            j *= 10;
            Arrays[i] = new int[j];

            /*
                call insertion sort 10 times on each array of size n. 
                Creates a new random array for each iteration.
            */
            for(int k = 0; k < 10; k++){
                Arrays[i] = createRandomIntArray(j);
                avg[i] += sort(Arrays[i]);
            }
        }
        /* 
            this loop divides the average number of operations by 10 because each 
            array is sorted 10 times and each time it is sorted, the function returns the number of operations it took.
            The results are then displayed in the print statement
        */
        System.out.println(Arrays[0].toString());
        for(int i = 0; i < avg.length; i++) {
            avg[i] = avg[i] / 10;
            System.out.println("Size of the array: " + (Arrays[i].length-1) + " Estimated number of operations: " + avg[i]);
        }
    }
}


