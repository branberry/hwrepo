import java.util.Random;

/**
 * Created by Brandon Ly on 1/18/2018.
 */
public class InsertionSort {
    public static int[] createRandomIntArray( int size) {
        int[] answer = new int[size];
        Random generator = new Random();
        for( int i = 0; i < size; i++) {
            answer[i] = generator.nextInt(100) + 1;
        }
        return answer;
    }

    public static int sort(int[] A) {
        int n = 1;
        for(int i = 1; i < A.length; i++) {
            int key = A[i];
            int j = i - 1;
            n+=2;
            while(j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j--;
                n+=2;
            }
            n+=2;
            A[j + 1] = key;
            n++;
        }
        return n;
    }

    public static void main(String[] args) {
        int[] A = new int[10];
        int[] B = new int[100];
        int[] C = new int[1000];
        int[] D = new int[10000];
        int[] E = new int[100000];
        int[] F = new int[1000000];

        float avgA = 0;
        float avgB = 0;
        float avgC = 0;
        float avgD = 0;
        float avgE = 0;
        float avgF = 0;

        for(int i = 0; i < 10; i++) {
            A = createRandomIntArray(10);
            B = createRandomIntArray(100);
            C = createRandomIntArray(1000);
            D = createRandomIntArray(10000);
            E = createRandomIntArray(100000);

            avgA += sort(A);
            avgB += sort(B);
            avgC += sort(C);
            avgD += sort(D);
            avgE += sort(E);

        }
        avgA = avgA/10;
        avgB = avgB/10;
        avgC = avgC/10;
        avgD = avgD/10;
        avgE = avgE/10;

        System.out.println("Average number of operations when n = 10: " + avgA + " Average when n = 100: " + avgB + " Average number of operations when n = 1000: " + avgC + " Average when n = 10000: " + avgD + " Average when n = 100000: " + avgE);
    }
}
