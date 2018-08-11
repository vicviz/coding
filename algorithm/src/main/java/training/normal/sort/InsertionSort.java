/**
 *
 * ChooseSort.java, 2017—01-23.
 *
 *
 */
package training.normal.sort;

/**
 * 插入排序代码简单易实现并且有良好的效果,是手边没有别的排序方法时的干写的首选
 * 注意一下与选择排序的区别:就是之前的比较为后面的比较所使用来降低复杂度
 * @author zhaowei
 */
public class InsertionSort extends Sorter {

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
        Sorter sorter = new InsertionSort();
        Comparable[] a = mock(len);
        show(a, len);
        System.out.println("time cost:" + sorter.sort(a));
        show(a, len);
    }
}
