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
public class SelectionSort extends Sorter {

    @Override
    public long sort(Comparable[] a) {
        long startTime = System.currentTimeMillis();
        for (int i = 0;i < a.length;i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[minIndex].compareTo(a[j]) > 0) {
                    minIndex = j;
                }
            }
            exchange(a, i, minIndex);
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        int len = 1000;
        Sorter sorter = new SelectionSort();
        Comparable[] a = mock(len);
        show(a, len);
        System.out.println("time cost:" + sorter.sort(a));
        show(a, len);
    }
}
