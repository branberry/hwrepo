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
     * 
     */
    public static int[] createRandomIntArray(int size) {
        int[] answer = new int[size+1];
        Random generator = new Random();
        answer[0] = -9999999;
        for( int i = 1; i < size + 1; i++) {
            answer[i] = generator.nextInt(100) + 1;
        }
        return answer;
    }

    public static int parent(int i) {
        return (int) Math.floor(i/2);
    }

    public static int left(int i) {
        return i * 2;
    }

    public static int right(int i) {
        return i * 2 + 1;
    }

    /**
     * swaps two values in the array
     * @param A is the array that will have its value swapped
     * @param i is the first index
     * @param j is the second index
     */
    public static void swap(int[] A, int i, int j) {
        int temp;
        temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void maxHeapify(int[] A, int i) {
        int l = left(i);
        int r = right(i);
        int largest;

        if(l < A.length && A[l] > A[i]) {
            largest = l;
        } else {
            largest = i;
        }

        if(r < A.length && A[r] > A[largest]) {
            largest = r;
        }

        if(largest != i) {
            swap(A,i,largest);
            maxHeapify(A,largest);
        }
    }

    public static void maxHeapify(int[] A, int i, int n) {
        int l = left(i);
        int r = right(i);
        int largest;

        if(l <= n && A[l] > A[i]) {
            largest = l;
        } else {
            largest = i;
        }

        if(r <= n && A[r] > A[largest]) {
            largest = r;
        }

        if(largest != i) {
            swap(A,i,largest);
            maxHeapify(A,largest,n);
        }
    }

    public static void buildMaxHeap(int[] A) {
        for(int i = (int) Math.floor(A.length/2); i >= 1; i--) {
            maxHeapify(A,i);
        }
    }

    public static void sort(int[] A) {
        buildMaxHeap(A);
        int n = A.length - 1;
        for(int i = n; i >= 1; i--) {
            n--;
            swap(A,1,i);
            maxHeapify(A,1,n);
        }
    }

    public static void main(String[] args) {
        int[] test = new int[11];
        test = createRandomIntArray(10);
        sort(test);

        for(int i = 1; i < 11; i++) {
            System.out.print(" " + test[i] + " ");
        }
    }
}


