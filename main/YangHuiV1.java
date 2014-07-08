package main;

/**
 * Created by huidong on 6/17/14.
 */
public class YangHuiV1 {
    public static void arrayPrint() {
        int yhArray[][] = new int[10][10];
        int i, j;
        yhArray[0][0] = 1;
        yhArray[1][0] = 1;
        yhArray[1][1] = 1;

        for (i = 2; i < 10; i++) {
            yhArray[i][0] = 1;
            yhArray[i][i] = 1;
            for (j = 1; j < i; j++) {
                yhArray[i][j] = yhArray[i-1][j-1] + yhArray[i-1][j];
            }

        }

        for (i = 0; i < 10; i++) {
            //print space
            for (int k = 10; k > i; k-- ) {
                System.out.print("  ");
            }
            for (j = 0; j <= i; j++) {
                System.out.printf("%4d", yhArray[i][j]);
            }
            System.out.println();
        }

    }

    public static void main(String args[]) {
        arrayPrint();
    }
}
