import java.util.Random;

/**
 * Created by Brandon Ly on 1/18/2018.
 */
public class InsertionSort {
    public static int[] createRandomIntArray( int size) {
        int[] answer = new int[ size];
        Random generator = new Random();
        for( int i = 0; i < size;  i++) {
            answer[i] = generator.nextInt(100) + 1;
        }
        return answer;
    }

    public static void sort(int[] A) {
        for(int i = 1; i < A.length; i++) {
            int key = A[i];
            int j = i - 1;
            while(j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] A = new int[10];
        A = createRandomIntArray(10);
        sort(A);
        for(int element : A) {
            System.out.println(element);
        }
    }
}
