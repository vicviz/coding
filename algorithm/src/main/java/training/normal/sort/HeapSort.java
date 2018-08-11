/**
 *
 * ChooseSort.java, 2017—01-23.
 *
 *
 */
package training.normal.sort;

/**
 * @author zhaowei
 */
public class HeapSort extends Sorter {



    @Override
    public long sort(Comparable[] a) {
        long startTime = System.currentTimeMillis();
        for (int i = 0;i < a.length;i++) {
            for (int j = i; j > 0 && (a[j].compareTo(a[j -1]) < 0); j--) {
                exchange(a, j, j - 1);
            }
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        int len = 1000;
        Sorter sorter = new HeapSort();
        Comparable[] a = Sorter.mock(len);
        show(a, len);
        System.out.println("time cost:" + sorter.sort(a));
        show(a, len);
    }
}
