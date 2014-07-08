package main;

/**
 * Created by huidong on 6/18/14.
 */
public class YangHuiV2 {
    private int layer;
    private int yhArray[][];

    public YangHuiV2(int layer) {
        if( layer > 0) {
            this.layer = layer;
            this.yhArray = new int[layer][layer];
            setYhArray();
        } else {
            System.out.println("Layers should be larger then 0");
        }
    }

//    public boolean checkLayer(int layer) {
////        if ( layer <= 0 ) {
////            throw new IllegalArgumentException();
////        }
//        return layer > 0;
//    }
    public void setYhArray() {
        yhArray[0][0] = 1;
        if (layer > 1 ) {
            yhArray[1][0] = 1;
            yhArray[1][1] = 1;
            int i,j;
            for (i = 2; i < this.layer; i++) {
                yhArray[i][0] = 1;
                yhArray[i][i] = 1;
                for (j = 1; j < i; j++) {
                    yhArray[i][j] = yhArray[i-1][j-1] + yhArray[i-1][j];
                }
            }
        }
    }

    public void arrayPrint() {
        int i,j,k;
        for (i = 0; i < layer; i++) {
            /* print space */
            for (k = layer - 1; k > i; k-- ) {
                System.out.print("   ");
            }
            for (j = 0; j <= i; j++) {
                System.out.printf("%6d", yhArray[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {
        YangHuiV2 yh = new YangHuiV2(18);
        yh.arrayPrint();
    }

}
