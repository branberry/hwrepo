import java.lang.*;
import java.util.Random;

/**
 *  @author Brandon Ly - CSC 320A
 * 
 *  This class is an implementation of Heap Sort from CLRS
 *  This implementation will sort arrays of integers
 */
public class HeapSort {
    /**
     * @param size is the size of the random array
     * fills the array with random numbers 
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
     * Since heapsort puts the larget value at the end of the array, we do not want to use reheapify the array with the largest value, rather, the subbarray from A[1] to n-1.
     * @param A the arry containing the heap
     * @param i the index of the heap we are max-heapifying
     * @param n the index of the heap that we want to heapify up to
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
        int[] test = new int[11];
        int count = 0;
        test = createRandomIntArray(10);
        count = sort(test);
        System.out.println("count: " + count);
        for(int i = 1; i < 11; i++) {
            System.out.print(" " + test[i] + " ");
        }
    }
}


