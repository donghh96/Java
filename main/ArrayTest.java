package main;

/**
 * Created by huidong on 6/24/14.
 */
public class ArrayTest {
    public static void minAndMax(int A[]) {
        int i, min, max;
        min = max = A[0];
        for (i=0; i<A.length; i++) {
            if(A[i] < min) {
                min = A[i];
            }
            if(A[i] > max) {
                max = A[i];
            }
        }
        System.out.println("Min is " + min);
        System.out.println("Max is " + max);
    }

    public static void main(String args[]) {
        int a[] = {11, 24, 88, 45, 36, 25, 77, 9};
        minAndMax(a);

    }
}
