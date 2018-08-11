/**
 *
 * Sorter.java, 2017—01-23.
 *
 *
 */
package training.normal.sort;

import java.util.Random;

/**
 * @author zhaowei
 */
public class Sorter {
    private static Random r = new Random();

    /**
     * time cost
     * @param a
     * @return
     */
    public long sort(Comparable[] a) {return 0l;}

    public static Comparable[] mock(int len) {
        Comparable[] a = new Comparable[len];
        for (int i = 0 ; i < len; i++) {
            a[i] = new Integer(r.nextInt());
        }
        return a;
    }

    public static void show(Comparable[] a, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(a[i] + "\t");
        }
        System.out.println();
    }

    public static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
